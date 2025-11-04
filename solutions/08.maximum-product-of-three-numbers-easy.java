/*
 * Maximum Product of Three Numbers (Easy)
 * https://leetcode.com/problems/maximum-product-of-three-numbers/
 *
 * The task is to pick any three numbers from the array whose product is maximized. After sorting, the maximum product either comes from the three largest values, or from the two smallest values (which could be large negative numbers, giving a positive product) multiplied by the largest value. We compute both candidates and return the max. Sorting dominates the cost, so time complexity is O(n log n) with O(1) extra space (ignoring sort's internal space).
 */

class Solution {
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        // either three largest, or two smallest (most negative) times largest
        int a = nums[n-1] * nums[n-2] * nums[n-3];
        int b = nums[0] * nums[1] * nums[n-1];
        return Math.max(a, b);
    }
}
