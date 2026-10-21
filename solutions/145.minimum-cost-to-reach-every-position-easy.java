/*
 * Minimum Cost to Reach Every Position (Easy)
 * https://leetcode.com/problems/minimum-cost-to-reach-every-position/
 *
 * Since swapping with anyone behind you is free, once you pay to pass a cheap person you can always coast forward from there. So the min cost to reach position i is just the minimum cost among positions 0..i (pay the cheapest person up to that point, then ride free the rest of the way). Solved with a single pass tracking a running minimum. Time complexity O(n), space O(1) extra (excluding output array).
 */

class Solution {
    public int[] minCosts(int[] cost) {
        int n = cost.length;
        int[] answer = new int[n];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            min = Math.min(min, cost[i]);
            answer[i] = min;
        }
        return answer;
    }
}
