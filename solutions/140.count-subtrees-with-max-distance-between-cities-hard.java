/*
 * Count Subtrees With Max Distance Between Cities (Hard)
 * https://leetcode.com/problems/count-subtrees-with-max-distance-between-cities/
 *
 * We need to count, for every possible diameter value d, how many connected vertex subsets of the tree have that exact diameter. Since n is small enough (≤15), I enumerate all 2^n subsets via bitmask, check each for connectivity by a BFS restricted to the masked nodes, and if connected, compute its diameter using precomputed all-pairs tree distances (since a connected subtree's internal path between any two of its nodes coincides with the original tree path, distances can be precomputed once via BFS from each node). The diameter is just the max pairwise distance among nodes in the connected subset. This gives O(2^n * n^2) time and O(n^2) extra space for the distance table, which is efficient for the given constraints.
 */

class Solution {
    public int[] countSubgraphsForEachDiameter(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) {
            int u = e[0] - 1, v = e[1] - 1;
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        // precompute pairwise distances via BFS from each node
        int[][] dist = new int[n][n];
        for (int s = 0; s < n; s++) {
            Arrays.fill(dist[s], -1);
            dist[s][s] = 0;
            Deque<Integer> q = new ArrayDeque<>();
            q.add(s);
            while (!q.isEmpty()) {
                int cur = q.poll();
                for (int nb : adj.get(cur)) {
                    if (dist[s][nb] == -1) {
                        dist[s][nb] = dist[s][cur] + 1;
                        q.add(nb);
                    }
                }
            }
        }

        int[] ans = new int[n - 1];

        for (int mask = 1; mask < (1 << n); mask++) {
            int popcount = Integer.bitCount(mask);
            if (popcount < 2) continue;

            // check connectivity via BFS restricted to mask
            int root = Integer.numberOfTrailingZeros(mask);
            boolean[] visited = new boolean[n];
            visited[root] = true;
            int count = 1;
            Deque<Integer> q = new ArrayDeque<>();
            q.add(root);
            while (!q.isEmpty()) {
                int cur = q.poll();
                for (int nb : adj.get(cur)) {
                    if (((mask >> nb) & 1) == 1 && !visited[nb]) {
                        visited[nb] = true;
                        count++;
                        q.add(nb);
                    }
                }
            }
            if (count != popcount) continue; // not connected subtree

            // compute diameter using precomputed distances
            int diameter = 0;
            List<Integer> nodes = new ArrayList<>();
            for (int i = 0; i < n; i++) if (((mask >> i) & 1) == 1) nodes.add(i);
            for (int i = 0; i < nodes.size(); i++) {
                for (int j = i + 1; j < nodes.size(); j++) {
                    int d = dist[nodes.get(i)][nodes.get(j)];
                    if (d > diameter) diameter = d;
                }
            }
            if (diameter >= 1 && diameter <= n - 1) {
                ans[diameter - 1]++;
            }
        }

        return ans;
    }
}
