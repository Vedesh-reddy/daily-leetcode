/*
 * Distinct Subsequences (Hard)
 * https://leetcode.com/problems/distinct-subsequences/
 *
 * We need to count how many ways t appears as a subsequence in s. Classic DP: dp[i][j] = number of ways t[0..j) is a subsequence of s[0..i), with transition dp[i][j] = dp[i-1][j] (skip s[i-1]) plus dp[i-1][j-1] if characters match (use s[i-1] to match t[j-1]). Base case dp[i][0] = 1 since empty t is always a subsequence. Compressed the 2D table into a 1D rolling array of size n+1, iterating j from high to low so we don't overwrite dp[j-1] before it's used for the current row. Time complexity O(m*n), space O(n).
 */

class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;

        for (int i = 1; i <= m; i++) {
            // iterate backwards so dp[j-1] refers to previous row's value
            for (int j = n; j >= 1; j--) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[j] += dp[j - 1];
                }
            }
        }

        return dp[n];
    }
}
