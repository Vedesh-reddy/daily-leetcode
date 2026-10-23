/*
 * Count the Number of Good Partitions (Hard)
 * https://leetcode.com/problems/count-the-number-of-good-partitions/
 *
 * For each value track its last occurrence index, then sweep through the array extending the current merged segment's end to the max last-occurrence seen so far; whenever the current index reaches that end, a mandatory split boundary is formed (a "block"). Any two blocks must stay separate since they share no values, but within optional boundaries between blocks we can freely cut or not — so if there are k blocks, there are k-1 optional cut points, giving 2^(k-1) good partitions. Computed with fast modular exponentiation (here via simple loop doubling) under 1e9+7. Time complexity O(n), space O(n) for the hashmap of last indices.
 */

class Solution {
    public int numberOfGoodPartitions(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> lastIndex = new HashMap<>();
        for (int i = 0; i < n; i++) {
            lastIndex.put(nums[i], i);
        }

        int blocks = 0;
        int end = -1;
        for (int i = 0; i < n; i++) {
            if (i > end) blocks++;
            end = Math.max(end, lastIndex.get(nums[i]));
        }

        long MOD = 1_000_000_007L;
        long ans = 1;
        for (int i = 0; i < blocks - 1; i++) {
            ans = (ans * 2) % MOD;
        }
        return (int) ans;
    }
}
