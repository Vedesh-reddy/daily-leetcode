/*
 * Find the K-or of an Array (Easy)
 * https://leetcode.com/problems/find-the-k-or-of-an-array/
 *
 * For each bit position (0 to 31), count how many numbers in the array have that bit set. If the count is at least k, set that bit in the result. This directly mirrors the definition of K-or: a bit survives if at least k numbers share it. Time complexity is O(32 * n) which simplifies to O(n), and space is O(1) since we only track counts and the result.
 */

class Solution {
    public int findKOr(int[] nums, int k) {
        int result = 0;
        for (int bit = 0; bit < 32; bit++) {
            int count = 0;
            for (int num : nums) {
                if ((num & (1 << bit)) != 0) count++;
            }
            if (count >= k) result |= (1 << bit);
        }
        return result;
    }
}
