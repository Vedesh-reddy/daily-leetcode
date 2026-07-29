/*
 * Make Lexicographically Smallest Array by Swapping Elements (Medium)
 * https://leetcode.com/problems/make-lexicographically-smallest-array-by-swapping-elements/
 *
 * Elements that can be swapped (directly or transitively) form a group whenever consecutive sorted values differ by at most `limit`, since swapping is allowed for any pair within limit and this creates a chain/union effect. I sort indices by value, then scan through to break them into contiguous groups where adjacent sorted values differ by ≤ limit — each such group is fully interchangeable. Within each group, I take the original positions, sort them, and assign the sorted values to those positions in increasing order, which yields lexicographically smallest arrangement per group. Sorting dominates cost: O(n log n) time, O(n) space.
 */

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) idx[i] = i;
        Arrays.sort(idx, (a, b) -> nums[a] - nums[b]);

        int[] result = new int[n];
        int i = 0;
        while (i < n) {
            int j = i;
            // group consecutive sorted values within limit of each other into same connected group
            while (j + 1 < n && nums[idx[j + 1]] - nums[idx[j]] <= limit) j++;

            // collect original indices in this group, sort them so positions get smallest values first
            List<Integer> positions = new ArrayList<>();
            for (int k = i; k <= j; k++) positions.add(idx[k]);
            Collections.sort(positions);

            for (int k = i; k <= j; k++) {
                result[positions.get(k - i)] = nums[idx[k]];
            }

            i = j + 1;
        }

        return result;
    }
}

import java.util.*;
