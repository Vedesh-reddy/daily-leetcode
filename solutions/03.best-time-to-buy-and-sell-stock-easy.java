/*
 * Best Time to Buy and Sell Stock (Easy)
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 *
 * We need to pick one buy day and a later sell day to maximize profit. The approach tracks the minimum price seen so far while scanning left to right, and at each day checks what profit selling today would give against that minimum. Keep a running max of that profit. This works because the best sell day for any given buy day only matters relative to the lowest price before it, so we never need to look backward or recompute pairs. Time complexity is O(n), space is O(1).
 */

class Solution {
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price;
            } else if (price - minPrice > maxProfit) {
                maxProfit = price - minPrice;
            }
        }
        return maxProfit;
    }
}
