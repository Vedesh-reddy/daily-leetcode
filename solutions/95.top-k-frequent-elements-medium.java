/*
 * Top K Frequent Elements (Medium)
 * https://leetcode.com/problems/top-k-frequent-elements/
 *
 * Count frequencies, then bucket numbers by frequency (a frequency can't exceed n) and read buckets from highest down until k are collected. Time O(n), space O(n).
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) freq.merge(num, 1, Integer::sum);
        List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i <= nums.length; i++) buckets.add(new ArrayList<>());
        for (Map.Entry<Integer, Integer> e : freq.entrySet()) buckets.get(e.getValue()).add(e.getKey());
        int[] result = new int[k];
        int idx = 0;
        for (int f = nums.length; f > 0 && idx < k; f--) {
            for (int num : buckets.get(f)) {
                if (idx == k) break;
                result[idx++] = num;
            }
        }
        return result;
    }
}
