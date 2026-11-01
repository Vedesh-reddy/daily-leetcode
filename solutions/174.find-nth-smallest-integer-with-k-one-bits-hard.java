/*
 * Find Nth Smallest Integer With K One Bits (Hard)
 * https://leetcode.com/problems/find-nth-smallest-integer-with-k-one-bits/
 *
 * We need the nth number (1-indexed) whose binary form has exactly k set bits, with the guarantee that the result fits in 50 bits. The idea is to build the answer bit by bit from the most significant bit (position 49) down to bit 0, using combinatorics: for each position, if we leave the current bit as 0, the number of ways to place the remaining required ones in the remaining lower bits is C(remainingBits, onesLeft). If n is within that count, we leave the bit 0; otherwise we subtract that count from n, set the bit to 1, and decrement the needed ones. A precomputed Pascal's triangle up to 50 gives O(1) binomial lookups. This runs in O(50) time per query after O(50^2) precomputation, with O(50^2) space for the combinatorics table.
 */

class Solution {
    public long nthSmallest(long n, int k) {
        // C[i][j] = number of ways to choose j bits set among i remaining bits
        long[][] C = new long[51][51];
        for (int i = 0; i <= 50; i++) {
            C[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                C[i][j] = C[i-1][j-1] + (j <= i-1 ? C[i-1][j] : 0);
            }
        }

        long result = 0;
        int onesLeft = k;
        // bits from position 49 down to 0 (50 bits total, answer < 2^50)
        for (int pos = 49; pos >= 0; pos--) {
            int remainingBits = pos; // bits after this one
            if (onesLeft > remainingBits) {
                // must place a 1 here, not enough room otherwise
                result |= (1L << pos);
                onesLeft--;
                continue;
            }
            // ways to complete with a 0 at this position
            long waysWithZero = (onesLeft <= remainingBits) ? C[remainingBits][onesLeft] : 0;
            if (n <= waysWithZero) {
                // bit stays 0, keep searching
            } else {
                n -= waysWithZero;
                result |= (1L << pos);
                onesLeft--;
            }
        }
        return result;
    }
}
