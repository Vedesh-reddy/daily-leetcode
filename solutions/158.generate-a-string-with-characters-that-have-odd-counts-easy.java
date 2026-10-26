/*
 * Generate a String With Characters That Have Odd Counts (Easy)
 * https://leetcode.com/problems/generate-a-string-with-characters-that-have-odd-counts/
 *
 * We need a string of length n where every distinct character appears an odd number of times. The simplest approach: if n is odd, just repeat a single character n times (one odd count). If n is even, use n-1 copies of one character (odd since n-1 is odd) plus a single different character (count 1, also odd). This covers all cases with minimal characters used. Time complexity is O(n) to build the string, and space complexity is O(n) for the output.
 */

class Solution {
    public String generateTheString(int n) {
        StringBuilder sb = new StringBuilder();
        if (n % 2 == 1) {
            for (int i = 0; i < n; i++) sb.append('a');
        } else {
            for (int i = 0; i < n - 1; i++) sb.append('a');
            sb.append('b');
        }
        return sb.toString();
    }
}
