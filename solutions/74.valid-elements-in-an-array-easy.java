/*
 * Valid Elements in an Array (Easy)
 * https://leetcode.com/problems/valid-elements-in-an-array/
 *
 * An element is valid if it's the max so far from the left, or the max so far from the right (or it's an endpoint). I precompute leftMax[i] = max of all elements before i, and rightMax[i] = max of all elements after i, using two linear passes. Then a single pass checks each element against these arrays, always including the first and last elements. Time complexity is O(n), space is O(n) for the auxiliary arrays.
 */

import java.util.*;

class Solution {
    public List<Integer> findValidElements(int[] nums) {
        int n = nums.length;
        List<Integer> res = new ArrayList<>();
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        leftMax[0] = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i-1], nums[i-1]);
        }
        rightMax[n-1] = Integer.MIN_VALUE;
        for (int i = n-2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i+1], nums[i+1]);
        }
        for (int i = 0; i < n; i++) {
            if (i == 0 || i == n-1 || nums[i] > leftMax[i] || nums[i] > rightMax[i]) {
                res.add(nums[i]);
            }
        }
        return res;
    }
}
