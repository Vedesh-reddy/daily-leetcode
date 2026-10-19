/*
 * Minimum Cost to Move Chips to The Same Position (Easy)
 * https://leetcode.com/problems/minimum-cost-to-move-chips-to-the-same-position/
 *
 * Since moving by 2 is free, every chip can slide to either position 0 or 1 at no cost depending on its parity. So the problem reduces to counting chips at odd positions vs even positions, then paying cost 1 for each chip in the smaller group to shift it onto the other group's parity. The answer is simply the minimum of the odd count and even count. This runs in O(n) time and O(1) extra space.
 */

class Solution {
    public int minCostToMoveChips(int[] position) {
        int odd = 0, even = 0;
        for (int p : position) {
            if (p % 2 == 0) even++;
            else odd++;
        }
        return Math.min(odd, even);
    }
}
