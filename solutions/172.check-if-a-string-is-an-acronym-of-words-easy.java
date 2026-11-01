/*
 * Check if a String Is an Acronym of Words (Easy)
 * https://leetcode.com/problems/check-if-a-string-is-an-acronym-of-words/
 *
 * We need to check whether s equals the concatenation of the first letters of each word in words. The approach first checks the lengths match (since each word contributes exactly one character), then iterates through the list comparing each word's first character to the corresponding character in s, returning false on any mismatch. If all characters match, return true. Time complexity is O(n) where n is the number of words, and space complexity is O(1) aside from input storage.
 */

import java.util.List;

class Solution {
    public boolean isAcronym(List<String> words, String s) {
        if (words.size() != s.length()) return false;
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).charAt(0) != s.charAt(i)) return false;
        }
        return true;
    }
}
