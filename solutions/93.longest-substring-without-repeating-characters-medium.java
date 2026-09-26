/*
 * Longest Substring Without Repeating Characters (Medium)
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 *
 * Sliding window: remember the last index of each character and jump the left edge past a repeat. Window length at each step is a candidate answer. Time O(n), space O(charset).
 */

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int[] last = new int[128];
        java.util.Arrays.fill(last, -1);
        int best = 0;
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (last[c] >= left) left = last[c] + 1;
            last[c] = right;
            best = Math.max(best, right - left + 1);
        }
        return best;
    }
}
