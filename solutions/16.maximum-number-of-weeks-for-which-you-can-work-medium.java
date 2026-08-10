/*
 * Maximum Number of Weeks for Which You Can Work (Medium)
 * https://leetcode.com/problems/maximum-number-of-weeks-for-which-you-can-work/
 *
 * The task is to schedule milestones week by week so no project is worked on twice in a row, maximizing total weeks used. The key insight is that the largest pile of milestones can be interleaved with all the others only if it's not more than one greater than the sum of the rest; otherwise the excess milestones can't be scheduled since there's nothing left to alternate with. So compute total sum and the max single value: if max <= rest + 1, all milestones can be used (answer = sum); otherwise the best achievable is rest*2 + 1 (alternating rest projects with the max one, using up rest+1 of the max pile). This runs in O(n) time and O(1) space.
 */

class Solution {
    public long numberOfWeeks(int[] milestones) {
        long sum = 0, max = 0;
        for (int m : milestones) {
            sum += m;
            max = Math.max(max, m);
        }
        long rest = sum - max;
        // if max project dominates, it can only fill rest+1 weeks alternating with others
        if (max > rest + 1) {
            return rest * 2 + 1;
        }
        return sum;
    }
}
