/*
 * Number of Different Integers in a String (Easy)
 * https://leetcode.com/problems/number-of-different-integers-in-a-string/
 *
 * The task is to find distinct integers embedded in a string, where letters act as separators and leading zeros should be ignored when comparing values. Since the numbers can be arbitrarily long (too big for int/long), I scan the string manually, extract maximal digit runs, strip any leading zeros (keeping at least one digit if the whole run is zeros), and store the resulting normalized string in a HashSet to dedupe. The answer is the set's size. This avoids overflow issues from parsing large numbers directly. Time complexity is O(n) where n is the string length, and space complexity is O(n) for the set of extracted substrings.
 */

class Solution {
    public int numDifferentIntegers(String word) {
        Set<String> set = new HashSet<>();
        int n = word.length();
        int i = 0;
        while (i < n) {
            if (Character.isDigit(word.charAt(i))) {
                int j = i;
                while (j < n && Character.isDigit(word.charAt(j))) j++;
                // strip leading zeros
                int start = i;
                while (start < j - 1 && word.charAt(start) == '0') start++;
                set.add(word.substring(start, j));
                i = j;
            } else {
                i++;
            }
        }
        return set.size();
    }
}
