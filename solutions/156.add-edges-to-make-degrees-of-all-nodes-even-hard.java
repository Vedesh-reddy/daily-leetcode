/*
 * Add Edges to Make Degrees of All Nodes Even (Hard)
 * https://leetcode.com/problems/add-edges-to-make-degrees-of-all-nodes-even/
 *
 * Since every edge contributes 2 to the total degree sum, the number of odd-degree nodes is always even (0, 2, or 4 given the 2-edge budget); if more than 4 nodes have odd degree it's impossible. For 0 odd nodes the answer is already true. For 2 odd nodes, either connect them directly if that edge doesn't exist, or find a third node not yet linked to either and add two edges through it (keeping that node's degree parity unchanged). For 4 odd nodes, try all three ways to pair them up and check if both edges in a pairing are absent. Edge existence is checked via a HashSet of encoded (min,max) pairs for O(1) lookups. Time complexity is O(n + E) for building structures plus O(n) for the two-odd-node search, overall O(n + E); space is O(n + E) for the edge set and degree array.
 */

import java.util.*;

class Solution {
    public boolean isPossible(int n, List<List<Integer>> edges) {
        int[] degree = new int[n + 1];
        Set<Long> edgeSet = new HashSet<>();
        for (List<Integer> e : edges) {
            int a = e.get(0), b = e.get(1);
            degree[a]++;
            degree[b]++;
            edgeSet.add(encode(a, b));
        }

        List<Integer> odd = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (degree[i] % 2 == 1) odd.add(i);
        }

        // sum of degrees is even, so odd count is always even
        if (odd.size() == 0) return true;

        if (odd.size() == 2) {
            int a = odd.get(0), b = odd.get(1);
            if (!edgeSet.contains(encode(a, b))) return true;
            // need a third node connected to neither a nor b
            for (int x = 1; x <= n; x++) {
                if (x != a && x != b &&
                    !edgeSet.contains(encode(a, x)) &&
                    !edgeSet.contains(encode(b, x))) {
                    return true;
                }
            }
            return false;
        }

        if (odd.size() == 4) {
            int a = odd.get(0), b = odd.get(1), c = odd.get(2), d = odd.get(3);
            int[][] pairings = {
                {a, b, c, d},
                {a, c, b, d},
                {a, d, b, c}
            };
            for (int[] p : pairings) {
                if (!edgeSet.contains(encode(p[0], p[1])) &&
                    !edgeSet.contains(encode(p[2], p[3]))) {
                    return true;
                }
            }
            return false;
        }

        // more than 4 odd-degree nodes can't be fixed with only 2 edges
        return false;
    }

    private long encode(int a, int b) {
        int lo = Math.min(a, b);
        int hi = Math.max(a, b);
        return (long) lo * 100001L + hi;
    }
}
