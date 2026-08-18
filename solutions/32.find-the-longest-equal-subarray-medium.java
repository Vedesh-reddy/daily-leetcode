/*
 * Find the Longest Equal Subarray (Medium)
 * https://leetcode.com/problems/find-the-longest-equal-subarray/
 *
 * The task is to pick a subarray, keep only its most common value's occurrences, and delete the rest (at most k deletions) so the remaining elements are all equal — we want the longest such result. I used a sliding window over the array while tracking the frequency of each value inside the window with a hash map. As long as (window length - max frequency in window) stays within k, the window is valid; otherwise shrink from the left. Since we only need the window to never shrink permanently smaller than previously achieved valid sizes, tracking maxFreq lazily (without decreasing it on shrink) still gives correct answers because we only care about the maximum window size ever achieved. Time complexity is O(n) since each index enters and leaves the window once, and space is O(n) for the frequency map in the worst case of distinct values.
 */

class Solution {
    public int longestEqualSubarray(List<Integer> nums, int k) {
        int n = nums.size();
        Map<Integer, Integer> freq = new HashMap<>();
        int left = 0, maxFreq = 0, ans = 0;

        for (int right = 0; right < n; right++) {
            int val = nums.get(right);
            freq.merge(val, 1, Integer::sum);
            maxFreq = Math.max(maxFreq, freq.get(val));

            // window size minus most frequent count = deletions needed
            while ((right - left + 1) - maxFreq > k) {
                int leftVal = nums.get(left);
                freq.merge(leftVal, -1, Integer::sum);
                left++;
            }

            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }
}
