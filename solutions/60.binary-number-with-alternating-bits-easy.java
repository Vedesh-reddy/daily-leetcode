/*
 * Binary Number with Alternating Bits (Easy)
 * https://leetcode.com/problems/binary-number-with-alternating-bits/
 *
 * The task is to check that no two adjacent bits in the binary form of n are equal. I extract the last bit, shift right, and compare each new bit to the previous one, returning false as soon as two consecutive bits match. If the loop finishes without a match, the bits alternate properly. Time complexity is O(log n) since we process each bit once, and space complexity is O(1).
 */

class Solution {
    public boolean hasAlternatingBits(int n) {
        int prev = n & 1;
        n >>= 1;
        while (n > 0) {
            int cur = n & 1;
            if (cur == prev) return false;
            prev = cur;
            n >>= 1;
        }
        return true;
    }
}
