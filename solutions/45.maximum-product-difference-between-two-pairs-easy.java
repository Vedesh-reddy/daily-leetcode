/*
 * Maximum Product Difference Between Two Pairs (Easy)
 * https://leetcode.com/problems/maximum-product-difference-between-two-pairs/
 *
 * The max product comes from multiplying the two largest values, and the min product comes from multiplying the two smallest values (works even with negatives constraint aside, but here all positive per constraints). Sorting the array puts these at the ends, so we just grab nums[n-1]*nums[n-2] for the top pair and nums[0]*nums[1] for the bottom pair and subtract. Time complexity is O(n log n) due to sorting, space is O(1) extra (ignoring sort's internal usage) or O(log n) depending on sort implementation.
 */

class Solution {
    public int maxProductDifference(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        // largest two vs smallest two
        return nums[n-1] * nums[n-2] - nums[0] * nums[1];
    }
}
