/*
 * Maximize Sum of Weights after Edge Removals (Hard)
 * https://leetcode.com/problems/maximize-sum-of-weights-after-edge-removals/
 *
 * We root the tree at node 0 and run a DFS computing two values per node: dp0 (best sum in its subtree when the node still has a free slot, i.e. it's not forced to connect upward) and dp1 (best sum when one slot is reserved for a potential edge to its parent). For a node, every child's dp0 is a guaranteed baseline, and choosing to additionally keep the edge to that child swaps its contribution to w + dp1[child], giving a "gain" over the baseline — we just greedily pick the top positive gains up to the available capacity (k for dp0, k-1 for dp1). The answer is dp0[root] since the root has no parent edge to worry about. Sorting gains per node costs O(deg log deg), so overall time is O(n log n) and space is O(n); DFS is run on a separate thread with a larger stack to avoid recursion depth issues on skewed trees.
 */

import java.util.*;

class Solution {
    List<int[]>[] adj;
    long[] dp0, dp1;
    int k;

    public long maximizeSumOfWeights(int[][] edges, int k) {
        int n = edges.length + 1;
        adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            adj[e[0]].add(new int[]{e[1], e[2]});
            adj[e[1]].add(new int[]{e[0], e[2]});
        }
        dp0 = new long[n];
        dp1 = new long[n];
        this.k = k;

        // avoid stack overflow on deep trees
        final long[] result = new long[1];
        Thread t = new Thread(null, () -> {
            dfs(0, -1);
            result[0] = dp0[0];
        }, "dfs", 1 << 26);
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return result[0];
    }

    void dfs(int node, int parent) {
        long base = 0;
        List<Long> gains = new ArrayList<>();
        for (int[] edge : adj[node]) {
            int child = edge[0], w = edge[1];
            if (child == parent) continue;
            dfs(child, node);
            base += dp0[child];
            // gain from "upgrading" this child edge to be included
            long gain = w + dp1[child] - dp0[child];
            gains.add(gain);
        }
        gains.sort(Collections.reverseOrder());

        int cap0 = k;
        int cap1 = Math.max(k - 1, 0);

        long sum0 = base;
        for (int i = 0; i < gains.size() && i < cap0; i++) {
            long g = gains.get(i);
            if (g > 0) sum0 += g; else break;
        }

        long sum1 = base;
        for (int i = 0; i < gains.size() && i < cap1; i++) {
            long g = gains.get(i);
            if (g > 0) sum1 += g; else break;
        }

        dp0[node] = sum0;
        dp1[node] = sum1;
    }
}
