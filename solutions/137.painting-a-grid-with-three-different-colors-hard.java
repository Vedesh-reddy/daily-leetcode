/*
 * Painting a Grid With Three Different Colors (Hard)
 * https://leetcode.com/problems/painting-a-grid-with-three-different-colors/
 *
 * Each column of height m must itself be a proper coloring (no two vertically adjacent cells share a color), so first generate all such valid column patterns via backtracking (at most 3·2^(m-1) of them). Two adjacent columns are compatible if they differ in every row, so precompute a compatibility list between all column patterns. Then run a DP across the n columns where dp[i] tracks the number of ways to reach column pattern i, transitioning only through compatible pairs, reducing modulo 1e9+7. Finally sum dp over all patterns for the answer. Time complexity is O(n·K²) where K is the number of valid column patterns (bounded by a small constant since m is small), and space is O(K) for the DP array plus O(K²) for compatibility storage.
 */

class Solution {
    public int colorTheGrid(int m, int n) {
        int MOD = 1_000_000_007;
        List<int[]> cols = new ArrayList<>();
        int[] cur = new int[m];
        genCols(cur, 0, m, cols);

        int size = cols.size();
        // precompute compatible pairs
        List<List<Integer>> compat = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                if (isCompatible(cols.get(i), cols.get(j))) list.add(j);
            }
            compat.add(list);
        }

        long[] dp = new long[size];
        Arrays.fill(dp, 1);
        for (int col = 1; col < n; col++) {
            long[] ndp = new long[size];
            for (int i = 0; i < size; i++) {
                if (dp[i] == 0) continue;
                for (int j : compat.get(i)) {
                    ndp[j] = (ndp[j] + dp[i]) % MOD;
                }
            }
            dp = ndp;
        }

        long ans = 0;
        for (long v : dp) ans = (ans + v) % MOD;
        return (int) ans;
    }

    private void genCols(int[] cur, int idx, int m, List<int[]> cols) {
        if (idx == m) {
            cols.add(cur.clone());
            return;
        }
        for (int c = 0; c < 3; c++) {
            if (idx > 0 && cur[idx - 1] == c) continue;
            cur[idx] = c;
            genCols(cur, idx + 1, m, cols);
        }
    }

    private boolean isCompatible(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == b[i]) return false;
        }
        return true;
    }
}
