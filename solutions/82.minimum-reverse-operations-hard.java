/*
 * Minimum Reverse Operations (Hard)
 * https://leetcode.com/problems/minimum-reverse-operations/
 *
 * The single 1 at position i can move, in one reverse of size k, to any position in a contiguous-by-parity range determined by the valid starting offsets of the subarray covering i; specifically the new positions form an arithmetic sequence with step 2 between `2*lo+k-1-i` and `2*hi+k-1-i` where lo/hi bound the valid left endpoints of the window. I run BFS from p, and to avoid revisiting/rescanning all n positions each step, I keep two TreeSets (even and odd indexed, excluding banned and already-visited positions) so each reachable position can be found and removed in O(log n) via ceiling queries within the target range, guaranteeing each node is processed once overall. This gives O(n log n) time and O(n) space, avoiding the naive O(n*k) or O(n^2) blowup.
 */

class Solution {
    public int[] minReverseOperations(int n, int p, int[] banned, int k) {
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        boolean[] isBanned = new boolean[n];
        for (int b : banned) isBanned[b] = true;

        TreeSet<Integer> even = new TreeSet<>();
        TreeSet<Integer> odd = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            if (i == p || isBanned[i]) continue;
            if (i % 2 == 0) even.add(i);
            else odd.add(i);
        }

        ans[p] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(p);

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int lo = Math.max(cur - k + 1, 0);
            int hi = Math.min(n - k, cur);
            if (lo > hi) continue;
            int newLo = 2 * lo + k - 1 - cur;
            int newHi = 2 * hi + k - 1 - cur;

            TreeSet<Integer> set = (newLo % 2 == 0) ? even : odd;
            Integer x = set.ceiling(newLo);
            while (x != null && x <= newHi) {
                ans[x] = ans[cur] + 1;
                queue.add(x);
                set.remove(x);
                x = set.ceiling(newLo);
            }
        }

        return ans;
    }
}
