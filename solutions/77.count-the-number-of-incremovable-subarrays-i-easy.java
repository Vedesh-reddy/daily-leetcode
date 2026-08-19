/*
 * Count the Number of Incremovable Subarrays I (Easy)
 * https://leetcode.com/problems/count-the-number-of-incremovable-subarrays-i/
 *
 * The task asks how many contiguous subarrays, when removed, leave the remaining elements strictly increasing (empty array counts as increasing). Given the small constraint size typical of this Easy variant, a brute-force approach works fine: try every possible (start, end) pair for the subarray to remove, then scan the remaining elements to verify strict increase. This gives an O(n^3) worst-case time complexity (O(n^2) subarrays times O(n) verification), which is acceptable for small n, and O(1) extra space aside from the input array. The helper function skips indices within the removed range and checks that each kept element strictly exceeds the previous kept element.
 */

class Solution {
    public int incremovableSubarrayCount(int[] nums) {
        int n = nums.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (isIncreasingAfterRemoval(nums, i, j)) {
                    count++;
                }
            }
        }
        return count;
    }
    
    private boolean isIncreasingAfterRemoval(int[] nums, int start, int end) {
        int prev = Integer.MIN_VALUE;
        for (int k = 0; k < nums.length; k++) {
            if (k >= start && k <= end) continue;
            if (nums[k] <= prev) return false;
            prev = nums[k];
        }
        return true;
    }
}
