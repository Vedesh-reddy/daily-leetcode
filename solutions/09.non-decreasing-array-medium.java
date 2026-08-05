/*
 * Non-decreasing Array (Medium)
 * https://leetcode.com/problems/non-decreasing-array/
 *
 * The task is to determine if the array can be made non-decreasing with at most one element change. I scan through the array looking for violations where nums[i-1] > nums[i]; if more than one such violation occurs, it's impossible. When a violation is found, I greedily decide which element to adjust: if lowering nums[i-1] to nums[i] keeps things consistent with nums[i-2] (or there is no i-2), do that; otherwise raise nums[i] to nums[i-1]. This greedy choice preserves the best chance of avoiding future violations. Time complexity is O(n) and space is O(1) since we modify in place.
 */

class Solution {
    public boolean checkPossibility(int[] nums) {
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > nums[i]) {
                count++;
                if (count > 1) return false;
                // decide whether to lower nums[i-1] or raise nums[i]
                if (i - 2 < 0 || nums[i - 2] <= nums[i]) {
                    nums[i - 1] = nums[i];
                } else {
                    nums[i] = nums[i - 1];
                }
            }
        }
        return true;
    }
}
