/*
 * Adjacent Increasing Subarrays Detection I (Easy)
 * https://leetcode.com/problems/adjacent-increasing-subarrays-detection-i/
 *
 * The task asks whether two back-to-back blocks of length k, one starting right after the other, are each strictly increasing. I precompute runLen[i], the length of the strictly increasing run ending at index i, using a single pass comparing each element to its predecessor. Then for every valid starting index a, I just check that runLen at the end of the first block and at the end of the second block are both at least k, which confirms both blocks are strictly increasing without rescanning them. This runs in O(n) time and O(n) extra space for the runLen array.
 */

import java.util.List;

class Solution {
    public boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
        int n = nums.size();
        int[] runLen = new int[n];
        runLen[0] = 1;
        for (int i = 1; i < n; i++) {
            if (nums.get(i) > nums.get(i - 1)) {
                runLen[i] = runLen[i - 1] + 1;
            } else {
                runLen[i] = 1;
            }
        }

        for (int a = 0; a + 2 * k - 1 < n; a++) {
            int endFirst = a + k - 1;
            int endSecond = a + 2 * k - 1;
            if (runLen[endFirst] >= k && runLen[endSecond] >= k) {
                return true;
            }
        }
        return false;
    }
}
