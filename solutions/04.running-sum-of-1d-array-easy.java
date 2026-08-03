/*
 * Running Sum of 1d Array (Easy)
 * https://leetcode.com/problems/running-sum-of-1d-array/
 *
 * The task is to transform the array so each index holds the cumulative sum of all elements up to and including that index. The approach simply iterates through the array once, adding the previous element's value to the current one, building the running total in place as we go. This avoids allocating a separate output array, reusing the input array to store results directly. Time complexity is O(n) since we make a single pass, and space complexity is O(1) extra space (not counting the output itself).
 */

class Solution {
    public int[] runningSum(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }
        return nums;
    }
}
