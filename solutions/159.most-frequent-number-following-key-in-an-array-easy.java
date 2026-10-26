/*
 * Most Frequent Number Following Key In an Array (Easy)
 * https://leetcode.com/problems/most-frequent-number-following-key-in-an-array/
 *
 * The task is to find which value most often shows up right after the given key in the array. I scan through nums, and whenever I hit the key, I bump a counter for the following element in a HashMap, tracking the current max as I go so a final pass isn't needed. Since the problem guarantees a unique maximum, there's no tie-breaking logic required. Time complexity is O(n) for the single pass, and space is O(n) in the worst case for the frequency map.
 */

class Solution {
    public int mostFrequent(int[] nums, int key) {
        Map<Integer, Integer> count = new HashMap<>();
        int best = nums[0], bestCount = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == key) {
                int c = count.merge(nums[i + 1], 1, Integer::sum);
                if (c > bestCount) {
                    bestCount = c;
                    best = nums[i + 1];
                }
            }
        }
        return best;
    }
}
