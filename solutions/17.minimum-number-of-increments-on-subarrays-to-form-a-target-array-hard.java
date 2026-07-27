/*
 * Minimum Number of Increments on Subarrays to Form a Target Array (Hard)
 * https://leetcode.com/problems/minimum-number-of-increments-on-subarrays-to-form-a-target-array/
 *
 * Each increment operation covers a contiguous subarray, so the total number of operations equals the sum of all "upward steps" between adjacent elements (including the implicit step from 0 to target[0]). Whenever target[i] > target[i-1], we need extra operations that start at index i, equal to the difference; when target[i] <= target[i-1], no new operations are needed since existing ranges can just stop there without adding new starts. This greedy works because we only care about increases—decreases just mean some ranges end, costing nothing extra. Time complexity is O(n) and space is O(1).
 */

class Solution {
    public int minNumberOperations(int[] target) {
        int result = target[0];
        for (int i = 1; i < target.length; i++) {
            if (target[i] > target[i - 1]) {
                result += target[i] - target[i - 1];
            }
        }
        return result;
    }
}
