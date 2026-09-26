/*
 * Valid Anagram (Easy)
 * https://leetcode.com/problems/valid-anagram/
 *
 * Count letters of the first string up and the second string down in a 26-slot array; anagrams leave every slot at zero. Time O(n), space O(1).
 */

class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] counts = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i) - 'a']++;
            counts[t.charAt(i) - 'a']--;
        }
        for (int c : counts) {
            if (c != 0) return false;
        }
        return true;
    }
}
