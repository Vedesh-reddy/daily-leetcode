/*
 * Coin Change (Medium)
 * https://leetcode.com/problems/coin-change/
 *
 * Bottom-up DP where dp[a] is the fewest coins for amount a, built from dp[a - coin] + 1. Unreachable amounts stay above the amount itself. Time O(amount * coins), space O(amount).
 */

import java.util.Arrays;

class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int a = 1; a <= amount; a++) {
            for (int coin : coins) {
                if (coin <= a) dp[a] = Math.min(dp[a], dp[a - coin] + 1);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
