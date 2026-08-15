/*
 * Find the Number of Subsequences With Equal GCD (Hard)
 * https://leetcode.com/problems/find-the-number-of-subsequences-with-equal-gcd/
 *
 * Each element of nums can go into seq1, seq2, or neither, so track a DP over pairs (g1, g2) where 0 marks "still empty" and positive values are the running GCD of whichever subsequence. For every number, three transitions update the state: leave unassigned (baseline copy), fold into seq1's GCD, or fold into seq2's GCD, all done from a snapshot of the previous state so contributions don't double-count within the same element. The answer is the sum over all g>0 of dp[g][g], since equal nonzero GCDs guarantee both subsequences are non-empty. With V = max value in nums, complexity is O(n·V²) time and O(V²) space, which is efficient for the given constraints (V ≤ 200).
 */

class Solution {
    public int subsequencePairCount(int[] nums) {
        final int MOD = 1_000_000_007;
        int maxV = 0;
        for (int x : nums) maxV = Math.max(maxV, x);

        long[][] dp = new long[maxV + 1][maxV + 1];
        dp[0][0] = 1; // both empty initially

        int[][] gcdTable = new int[maxV + 1][maxV + 1];
        for (int i = 0; i <= maxV; i++) {
            for (int j = 0; j <= maxV; j++) {
                gcdTable[i][j] = gcd(i, j);
            }
        }

        for (int x : nums) {
            long[][] newDp = new long[maxV + 1][maxV + 1];
            // baseline: skip x
            for (int g1 = 0; g1 <= maxV; g1++) {
                for (int g2 = 0; g2 <= maxV; g2++) {
                    newDp[g1][g2] = dp[g1][g2];
                }
            }
            // add x to seq1 or seq2
            for (int g1 = 0; g1 <= maxV; g1++) {
                for (int g2 = 0; g2 <= maxV; g2++) {
                    long cur = dp[g1][g2];
                    if (cur == 0) continue;

                    int ng1 = (g1 == 0) ? x : gcdTable[g1][x];
                    newDp[ng1][g2] = (newDp[ng1][g2] + cur) % MOD;

                    int ng2 = (g2 == 0) ? x : gcdTable[g2][x];
                    newDp[g1][ng2] = (newDp[g1][ng2] + cur) % MOD;
                }
            }
            dp = newDp;
        }

        long ans = 0;
        for (int g = 1; g <= maxV; g++) {
            ans = (ans + dp[g][g]) % MOD;
        }
        return (int) ans;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
}
