/*
 * Smallest Rotation with Highest Score (Hard)
 * https://leetcode.com/problems/smallest-rotation-with-highest-score/
 *
 * For each rotation k, an element scores a point if its value is <= its new index. Instead of testing every k (O(n^2)), I figured out for each original element nums[i] the exact contiguous range of k values for which it contributes a point, using the relation j=(i-k) mod n and the condition v<=j — this range is [(i+1)%n, (i-v+n)%n], wrapping around if needed. I accumulate these ranges into a difference array (splitting the increment/decrement when the range wraps past n), then take a prefix sum to get the score for every k in one pass, tracking the smallest k with the maximum score. This runs in O(n) time and O(n) space.
 */

class Solution {
    public int bestRotation(int[] nums) {
        int n = nums.length;
        int[] diff = new int[n + 1];

        for (int i = 0; i < n; i++) {
            int v = nums[i];
            if (v >= n) continue; // never scores for any k

            int low = (i + 1) % n;
            int high = (i - v + n) % n;

            if (low <= high) {
                diff[low]++;
                diff[high + 1]--;
            } else {
                // wraps around, split into two segments
                diff[low]++;
                diff[n]--;
                diff[0]++;
                diff[high + 1]--;
            }
        }

        int best = 0, bestScore = -1, cur = 0;
        for (int k = 0; k < n; k++) {
            cur += diff[k];
            if (cur > bestScore) {
                bestScore = cur;
                best = k;
            }
        }

        return best;
    }
}
