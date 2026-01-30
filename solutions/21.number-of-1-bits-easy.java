/*
 * Number of 1 Bits (Easy)
 * https://leetcode.com/problems/number-of-1-bits/
 *
 * The task is to count how many bits are set to 1 in the binary form of a given integer. I used Brian Kernighan's trick: repeatedly AND the number with (n-1), which clears the lowest set bit each iteration, and count how many times this can be done until n becomes 0. This avoids checking every bit position individually, running in O(k) time where k is the number of set bits (at most 32), and O(1) space. For repeated calls, a lookup table (e.g., precomputing counts for all byte values 0-255) could speed things up by breaking the 32-bit number into 4 bytes and summing table lookups.
 */

class Solution {
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n &= (n - 1); // clears lowest set bit
            count++;
        }
        return count;
    }
}
