/*
 * Maximum Spending After Buying Items (Hard)
 * https://leetcode.com/problems/maximum-spending-after-buying-items/
 *
 * Since each shop's row is already sorted non-increasing, the only choice per shop is how many items from the right (smallest values) have been taken so far, so the order items get bought is entirely determined by picking, at each step, the globally smallest remaining "rightmost available" value across shops — this is equivalent to just sorting all values ascending and assigning day multipliers 1,2,...,m*n in that order, since cheaper items should be bought earlier (multiplied by smaller day numbers) to maximize total spend. I flatten the matrix into a single array, sort it ascending, then sum value*day for day = 1..m*n. Time complexity is O(mn log(mn)) for sorting, space is O(mn) for the flattened array.
 */

class Solution {
    public long maxSpending(int[][] values) {
        int m = values.length, n = values[0].length;
        // flatten all values into one array, sort ascending -> cheapest bought first
        int[] all = new int[m * n];
        int idx = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                all[idx++] = values[i][j];
            }
        }
        java.util.Arrays.sort(all);

        long total = 0;
        for (int d = 0; d < all.length; d++) {
            total += (long) all[d] * (d + 1);
        }
        return total;
    }
}
