/*
 * Trapping Rain Water (Hard)
 * https://leetcode.com/problems/trapping-rain-water/
 *
 * Two pointers with running left and right maxima: the side with the smaller max is bounded by it, so water there can be settled and that pointer advanced. Time O(n), space O(1).
 */

class Solution {
    public int trap(int[] height) {
        int lo = 0;
        int hi = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int water = 0;
        while (lo < hi) {
            if (height[lo] < height[hi]) {
                leftMax = Math.max(leftMax, height[lo]);
                water += leftMax - height[lo];
                lo++;
            } else {
                rightMax = Math.max(rightMax, height[hi]);
                water += rightMax - height[hi];
                hi--;
            }
        }
        return water;
    }
}
