/*
 * Sum of Elements With Frequency Divisible by K (Easy)
 * https://leetcode.com/problems/sum-of-elements-with-frequency-divisible-by-k/
 *
 * The task is to count how many times each value appears in the array, then include that value in the running total (once per occurrence) only if its count is an exact multiple of k. I used a HashMap to tally frequencies in a single pass, then iterated over the map entries, adding key*value to the sum whenever value % k == 0. This handles the edge case naturally since if nothing qualifies the sum stays 0. Time complexity is O(n) for building and scanning the map, and space complexity is O(n) in the worst case for distinct elements.
 */

class Solution {
    public int sumDivisibleByK(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int n : nums) {
            freq.put(n, freq.getOrDefault(n, 0) + 1);
        }
        
        int sum = 0;
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            if (entry.getValue() % k == 0) {
                sum += entry.getKey() * entry.getValue();
            }
        }
        return sum;
    }
}
