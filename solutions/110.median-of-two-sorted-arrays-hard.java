/*
 * Median of Two Sorted Arrays (Hard)
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 *
 * Binary search a partition of the shorter array so that everything on the left side of both partitions is no larger than everything on the right; the median comes from the boundary values. Time O(log min(m, n)), space O(1).
 */

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) return findMedianSortedArrays(nums2, nums1);
        int m = nums1.length;
        int n = nums2.length;
        int half = (m + n + 1) / 2;
        int lo = 0;
        int hi = m;
        while (lo <= hi) {
            int i = (lo + hi) >>> 1;
            int j = half - i;
            int aLeft = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
            int aRight = i == m ? Integer.MAX_VALUE : nums1[i];
            int bLeft = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
            int bRight = j == n ? Integer.MAX_VALUE : nums2[j];
            if (aLeft <= bRight && bLeft <= aRight) {
                int leftMax = Math.max(aLeft, bLeft);
                if ((m + n) % 2 == 1) return leftMax;
                return (leftMax + Math.min(aRight, bRight)) / 2.0;
            } else if (aLeft > bRight) {
                hi = i - 1;
            } else {
                lo = i + 1;
            }
        }
        throw new IllegalArgumentException("Input arrays are not sorted");
    }
}
