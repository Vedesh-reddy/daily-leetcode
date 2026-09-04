/*
 * Climbing Stairs (Easy)
 * https://leetcode.com/problems/climbing-stairs/
 *
 * The ways to reach step n are the ways to reach n-1 plus n-2, a Fibonacci recurrence. Keep only the last two values. Time O(n), space O(1).
 */

class Solution {
    public int climbStairs(int n) {
        int prev = 1;
        int curr = 1;
        for (int i = 2; i <= n; i++) {
            int next = prev + curr;
            prev = curr;
            curr = next;
        }
        return curr;
    }
}
