/*
 * Longest Increasing Subsequence (Medium)
 * https://leetcode.com/problems/longest-increasing-subsequence/
 *
 * Patience sorting: tails[i] holds the smallest tail of an increasing subsequence of length i + 1; binary search where each number fits. Time O(n log n), space O(n).
 */

class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] tails = new int[nums.length];
        int size = 0;
        for (int num : nums) {
            int lo = 0;
            int hi = size;
            while (lo < hi) {
                int mid = (lo + hi) >>> 1;
                if (tails[mid] < num) lo = mid + 1;
                else hi = mid;
            }
            tails[lo] = num;
            if (lo == size) size++;
        }
        return size;
    }
}
