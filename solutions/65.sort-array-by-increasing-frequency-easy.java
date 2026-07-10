/*
 * Sort Array by Increasing Frequency (Easy)
 * https://leetcode.com/problems/sort-array-by-increasing-frequency/
 *
 * The task is to sort elements primarily by how often they appear (ascending), and break ties by sorting the actual values in descending order. I first build a frequency map with a HashMap, then box the int array into Integer[] so I can use a custom comparator with Arrays.sort. The comparator compares frequencies first, and if equal, compares values in reverse order. Finally I unbox back into an int[] for the return. Time complexity is O(n log n) due to sorting, space is O(n) for the map and boxed array.
 */

class Solution {
    public int[] frequencySort(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int n : nums) freq.merge(n, 1, Integer::sum);

        Integer[] boxed = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) boxed[i] = nums[i];

        Arrays.sort(boxed, (a, b) -> {
            int fa = freq.get(a), fb = freq.get(b);
            if (fa != fb) return fa - fb;
            return b - a; // same freq -> decreasing value
        });

        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) res[i] = boxed[i];
        return res;
    }
}
