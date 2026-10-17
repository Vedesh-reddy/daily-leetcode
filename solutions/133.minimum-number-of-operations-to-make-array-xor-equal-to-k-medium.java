/*
 * Minimum Number of Operations to Make Array XOR Equal to K (Medium)
 * https://leetcode.com/problems/minimum-number-of-operations-to-make-array-xor-equal-to-k/
 *
 * XOR all the array elements together with k - if a bit is set in this combined XOR, it means that bit differs from what's needed, so it requires exactly one flip to fix. XOR is associative/commutative, so computing the running XOR of all nums and then XORing with k tells us exactly which bit positions are mismatched between the array's XOR and the target k. The answer is simply the number of set bits (popcount) in that result, since each mismatched bit can be fixed independently with one flip on any element. Time complexity is O(n) to iterate through nums, space is O(1).
 */

class Solution {
    public int minOperations(int[] nums, int k) {
        int xorAll = k;
        for (int num : nums) {
            xorAll ^= num;
        }
        return Integer.bitCount(xorAll);
    }
}
