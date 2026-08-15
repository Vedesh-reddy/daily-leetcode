/*
 * Construct K Palindrome Strings (Medium)
 * https://leetcode.com/problems/construct-k-palindrome-strings/
 *
 * The problem asks whether s's characters can be rearranged into exactly k non-empty palindromes using every character. A palindrome allows at most one character with an odd frequency (the middle character), so the number of characters with odd counts is the minimum number of palindromes needed. If that odd count is <= k, we can always split into exactly k palindromes (pad by borrowing characters from even-count pools to make more strings), and if s.length() < k we can't even fill k non-empty strings. Approach: count frequency of each letter, count how many have odd frequency, and compare with k plus check length constraint. Time complexity O(n) for counting characters, O(1) extra space (fixed 26-size array).
 */

class Solution {
    public boolean canConstruct(String s, int k) {
        if (s.length() < k) return false;
        int[] count = new int[26];
        for (char c : s.toCharArray()) count[c - 'a']++;
        int odd = 0;
        for (int c : count) if (c % 2 != 0) odd++;
        // each palindrome can absorb one odd count, extra odds need extra strings
        return odd <= k;
    }
}
