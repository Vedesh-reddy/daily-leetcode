/*
 * License Key Formatting (Easy)
 * https://leetcode.com/problems/license-key-formatting/
 *
 * Strip out the dashes and uppercase everything first, giving a clean alphanumeric string. Then figure out the size of the first group as `len % k` (or `k` if that's 0), and chunk the rest into groups of exactly `k`, joining with dashes. Using a StringBuilder for both the cleaned string and the result keeps things O(n) time and O(n) space, where n is the length of the input.
 */

class Solution {
    public String licenseKeyFormatting(String s, int k) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c != '-') sb.append(Character.toUpperCase(c));
        }

        int len = sb.length();
        int firstGroup = len % k;
        if (firstGroup == 0) firstGroup = k;

        StringBuilder result = new StringBuilder();
        result.append(sb, 0, firstGroup);

        for (int i = firstGroup; i < len; i += k) {
            result.append('-').append(sb, i, i + k);
        }

        return result.toString();
    }
}
