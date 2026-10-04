/*
 * Container With Most Water (Medium)
 * https://leetcode.com/problems/container-with-most-water/
 *
 * Start with the widest container and move the shorter wall inward, since moving the taller one can never increase the area. Time O(n), space O(1).
 */

class Solution {
    public int maxArea(int[] height) {
        int lo = 0;
        int hi = height.length - 1;
        int best = 0;
        while (lo < hi) {
            best = Math.max(best, (hi - lo) * Math.min(height[lo], height[hi]));
            if (height[lo] < height[hi]) lo++;
            else hi--;
        }
        return best;
    }
}
