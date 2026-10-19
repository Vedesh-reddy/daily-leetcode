/*
 * House Robber V (Medium)
 * https://leetcode.com/problems/house-robber-v/
 *
 * This is house robber with a twist: you can't rob two directly adjacent houses only if they share the same color code, otherwise adjacent robbing is fine. I track two running states per index: `take` (max money if this house is robbed) and `notake` (max money if it isn't). When the current and previous colors match, robbing the current house forces the previous one to be skipped, so `take` builds off `notake` only; when colors differ, `take` can build off either previous state since both adjacent houses can be robbed together. `notake` is always the max of the two previous states. The answer is the max of the final `take`/`notake` values. Runs in O(n) time with O(1) extra space.
 */

class Solution {
    public long rob(int[] nums, int[] colors) {
        int n = nums.length;
        long take = nums[0];
        long notake = 0;
        for (int i = 1; i < n; i++) {
            long newTake, newNotake;
            if (colors[i] == colors[i - 1]) {
                // can't rob both this and previous
                newTake = nums[i] + notake;
            } else {
                newTake = nums[i] + Math.max(take, notake);
            }
            newNotake = Math.max(take, notake);
            take = newTake;
            notake = newNotake;
        }
        return Math.max(take, notake);
    }
}
