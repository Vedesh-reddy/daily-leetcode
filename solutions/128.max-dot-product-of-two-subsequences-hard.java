/*
 * Max Dot Product of Two Subsequences (Hard)
 * https://leetcode.com/problems/max-dot-product-of-two-subsequences/
 *
 * This is a classic 2D DP where dp[i][j] represents the max dot product achievable using the first i elements of nums1 and first j elements of nums2 as a non-empty pair of subsequences. At each cell we either skip an element from nums1 (dp[i-1][j]), skip one from nums2 (dp[i][j-1]), or pair nums1[i-1] with nums2[j-1] directly, optionally extending the best previous pairing dp[i-1][j-1] only if that prior value is positive (since adding a negative running total would hurt). Initializing with Integer.MIN_VALUE ensures invalid "empty" states don't get incorrectly chosen. Time complexity is O(m*n) and space is O(m*n), which could be reduced to O(n) with a rolling array if needed.
 */

class Solution {
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int[] row : dp) java.util.Arrays.fill(row, Integer.MIN_VALUE);

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int prod = nums1[i - 1] * nums2[j - 1];
                int best = prod;
                if (dp[i - 1][j - 1] > 0) best = Math.max(best, dp[i - 1][j - 1] + prod);
                best = Math.max(best, dp[i - 1][j]);
                best = Math.max(best, dp[i][j - 1]);
                dp[i][j] = best;
            }
        }
        return dp[m][n];
    }
}
