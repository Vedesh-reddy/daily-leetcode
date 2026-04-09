/*
 * Maximize Sum of At Most K Distinct Elements (Easy)
 * https://leetcode.com/problems/maximize-sum-of-at-most-k-distinct-elements/
 *
 * The task is to pick up to k distinct values from nums that maximize the sum, then return them sorted descending. Since duplicates don't help (picking a distinct value once is as good as picking it multiple times), the optimal strategy is simply to take the k largest distinct values. I used a TreeSet to dedupe and keep values sorted, then walked its descending iterator to grab up to k elements. Time complexity is O(n log n) for building the TreeSet, and space is O(n) for the set plus O(k) for the result.
 */

class Solution {
    public int[] maxKDistinct(int[] nums, int k) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int n : nums) set.add(n);

        int size = Math.min(k, set.size());
        int[] result = new int[size];
        int idx = 0;
        for (Integer n : set.descendingSet()) {
            if (idx == size) break;
            result[idx++] = n;
        }
        return result;
    }
}
