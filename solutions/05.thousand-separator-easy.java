/*
 * Thousand Separator (Easy)
 * https://leetcode.com/problems/thousand-separator/
 *
 * Convert n to a string, then walk it from right to left, inserting a '.' every 3 digits (skipping when we hit the leftmost character so we don't get a leading separator). Building the result backwards into a StringBuilder and reversing at the end avoids messy index math. Time complexity is O(d) where d is the number of digits, space is O(d) for the output string.
 */

class Solution {
    public String thousandSeparator(int n) {
        String s = Integer.toString(n);
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
            count++;
            if (count % 3 == 0 && i != 0) {
                sb.append('.');
            }
        }
        return sb.reverse().toString();
    }
}
