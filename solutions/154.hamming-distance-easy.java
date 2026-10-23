/*
 * Hamming Distance (Easy)
 * https://leetcode.com/problems/hamming-distance/
 *
 * We need to count the number of bit positions where x and y differ. XOR-ing x and y gives a number where each set bit corresponds to a differing bit position between the two numbers. Then Integer.bitCount counts the number of set bits in that XOR result, which is exactly the Hamming distance. This is a simple, well-known bit trick. Time complexity is O(1) since integers are fixed-width (32 bits), and space complexity is O(1).
 */

class Solution {
    public int hammingDistance(int x, int y) {
        return Integer.bitCount(x ^ y);
    }
}
