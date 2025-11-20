/*
 * House Robber (Medium)
 * https://leetcode.com/problems/house-robber/
 *
 * At each house choose the better of skipping it or robbing it plus the best from two houses back. Two rolling variables suffice. Time O(n), space O(1).
 */

class Solution {
    public int rob(int[] nums) {
        int skip = 0;
        int take = 0;
        for (int num : nums) {
            int next = Math.max(take, skip + num);
            skip = take;
            take = next;
        }
        return take;
    }
}
