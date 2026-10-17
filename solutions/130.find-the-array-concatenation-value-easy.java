/*
 * Find the Array Concatenation Value (Easy)
 * https://leetcode.com/problems/find-the-array-concatenation-value/
 *
 * Use two pointers starting from both ends of the array, moving toward the middle. At each step, concatenate the values at the pointers (via string concatenation then parse to long) and add to the running total, then advance both pointers. If the pointers meet at the same index (odd length array), add that single remaining value. Time complexity is O(n) since each element is visited once, and space is O(1) aside from temporary string concatenation.
 */

class Solution {
    public long findTheArrayConcVal(int[] nums) {
        long result = 0;
        int i = 0, j = nums.length - 1;
        while (i < j) {
            result += Long.parseLong("" + nums[i] + nums[j]);
            i++;
            j--;
        }
        if (i == j) {
            result += nums[i];
        }
        return result;
    }
}
