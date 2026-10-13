/*
 * Longest Uncommon Subsequence I (Easy)
 * https://leetcode.com/problems/longest-uncommon-subsequence-i/
 *
 * If the two strings are equal, every subsequence of one is also a subsequence of the other, so no uncommon subsequence exists and we return -1. Otherwise, the longer string itself (or either, if same length) can't be a subsequence of the other since they differ somewhere—if it were, lengths being equal or less would force equality or containment issues, but since the strings differ, the whole longer string isn't a subsequence of the shorter/different one. So the answer is simply the max of the two lengths. Time complexity is O(n) for the equality check, space is O(1).
 */

class Solution {
    public int findLUSlength(String a, String b) {
        if (a.equals(b)) return -1;
        return Math.max(a.length(), b.length());
    }
}
