/*
 * Check if Number Has Equal Digit Count and Digit Value (Easy)
 * https://leetcode.com/problems/check-if-number-has-equal-digit-count-and-digit-value/
 *
 * The task is to verify that for each index i, the number of times digit i appears in the string equals the value at num[i]. I first build a frequency array counting occurrences of each digit 0-9 in the string, then compare each position's digit value against the corresponding frequency count. If any mismatch is found, return false; otherwise return true. Time complexity is O(n) since we scan the string twice (build counts, then verify), and space complexity is O(1) since the frequency array is fixed size 10.
 */

class Solution {
    public boolean digitCount(String num) {
        int n = num.length();
        int[] count = new int[10];
        for (char c : num.toCharArray()) {
            count[c - '0']++;
        }
        for (int i = 0; i < n; i++) {
            if (count[i] != num.charAt(i) - '0') {
                return false;
            }
        }
        return true;
    }
}
