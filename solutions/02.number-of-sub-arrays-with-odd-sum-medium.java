/*
 * Number of Sub-arrays With Odd Sum (Medium)
 * https://leetcode.com/problems/number-of-sub-arrays-with-odd-sum/
 *
 * A subarray sum is odd exactly when its prefix sums have different parities at the two endpoints, so I track running counts of even and odd prefix sums as I scan left to right, starting with one even prefix (the empty prefix before index 0). At each step, adding the current element updates the running prefix sum's parity, and I add the opposite-parity count to the result (odd count if current prefix is even, even count if current prefix is odd) since those pairings yield odd subarray sums ending at this index. This avoids computing actual subarray sums, using only prefix parity tracking. Time complexity is O(n), space is O(1) aside from the input array.
 */

class Solution {
    public int numOfSubarrays(int[] arr) {
        final int MOD = 1_000_000_007;
        long odd = 0, even = 1; // prefix sum counts, even starts at 1 for empty prefix
        long prefix = 0;
        long result = 0;
        for (int num : arr) {
            prefix += num;
            if (prefix % 2 == 0) {
                result = (result + odd) % MOD;
                even++;
            } else {
                result = (result + even) % MOD;
                odd++;
            }
        }
        return (int) (result % MOD);
    }
}
