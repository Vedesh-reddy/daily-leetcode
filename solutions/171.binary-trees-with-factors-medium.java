/*
 * Binary Trees With Factors (Medium)
 * https://leetcode.com/problems/binary-trees-with-factors/
 *
 * Each array value can be a leaf by itself, or an internal node whose two children multiply to its value. Sort the array so that for any product arr[i], its potential factor pairs come from smaller values already processed. For each arr[i], check all smaller arr[j] that divide it, and if the complementary factor arr[i]/arr[j] is also present in the array (tracked via a map), add dp[arr[j]] * dp[other] to dp[arr[i]], representing the number of trees rooted at arr[i] with that factor split. Sum all dp values at the end modulo 1e9+7. Time complexity is O(n^2) due to the nested loop over pairs, space is O(n) for the hashmap.
 */

class Solution {
    public int numFactoredBinaryTrees(int[] arr) {
        int MOD = 1_000_000_007;
        Arrays.sort(arr);
        Map<Integer, Long> dp = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            long count = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] % arr[j] == 0) {
                    int other = arr[i] / arr[j];
                    if (dp.containsKey(other)) {
                        count = (count + dp.get(arr[j]) * dp.get(other)) % MOD;
                    }
                }
            }
            dp.put(arr[i], count);
        }
        long total = 0;
        for (long v : dp.values()) {
            total = (total + v) % MOD;
        }
        return (int) total;
    }
}
