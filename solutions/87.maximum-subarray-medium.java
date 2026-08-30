/*
 * Maximum Subarray (Medium)
 * https://leetcode.com/problems/maximum-subarray/
 *
 * Kadane's algorithm: at each index either extend the previous subarray or start fresh, whichever is larger, and track the best seen. Time O(n), space O(1).
 */

class Solution {
    public int maxSubArray(int[] nums) {
        int current = nums[0];
        int best = nums[0];
        for (int i = 1; i < nums.length; i++) {
            current = Math.max(nums[i], current + nums[i]);
            best = Math.max(best, current);
        }
        return best;
    }
}
