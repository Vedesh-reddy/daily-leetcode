/*
 * Concatenate Array With Reverse (Easy)
 * https://leetcode.com/problems/concatenate-array-with-reverse/
 *
 * The task is to build an array twice the length of nums, with nums copied normally in the first half and nums reversed in the second half. I simply loop once through nums, placing each element at its normal index and its mirrored counterpart at the corresponding index in the second half using n - i - 1. This avoids a separate reverse pass. Time complexity is O(n) and space complexity is O(n) for the output array.
 */

class Solution {
    public int[] concatWithReverse(int[] nums) {
        int n = nums.length;
        int[] ans = new int[2 * n];
        for (int i = 0; i < n; i++) {
            ans[i] = nums[i];
            ans[i + n] = nums[n - i - 1];
        }
        return ans;
    }
}
