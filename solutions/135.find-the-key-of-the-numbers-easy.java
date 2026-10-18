/*
 * Find the Key of the Numbers (Easy)
 * https://leetcode.com/problems/find-the-key-of-the-numbers/
 *
 * Pad each number to a 4-digit string with leading zeros, then for each position take the minimum digit across the three numbers to build the key string. Parsing the resulting string back into an int automatically strips any leading zeros, giving the correct output format. Time complexity is O(1) since we always work with fixed-length (4-digit) strings, and space is O(1) as well.
 */

class Solution {
    public int generateKey(int num1, int num2, int num3) {
        String s1 = String.format("%04d", num1);
        String s2 = String.format("%04d", num2);
        String s3 = String.format("%04d", num3);
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int d = Math.min(s1.charAt(i) - '0', Math.min(s2.charAt(i) - '0', s3.charAt(i) - '0'));
            sb.append(d);
        }
        
        return Integer.parseInt(sb.toString());
    }
}
