/*
 * Find Minimum in Rotated Sorted Array II (Hard)
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/
 *
 * The array is a rotated sorted array that may contain duplicates, and we need the minimum element. I use a modified binary search: compare the middle element with the rightmost element. If mid > right, the minimum must be in the right half (lo = mid+1). If mid < right, the minimum is at mid or to its left (hi = mid). If they're equal, we can't determine which side has the minimum due to duplicates, so we just decrement hi to shrink the search space safely without skipping the answer. Loop ends when lo == hi, which points to the minimum. Time complexity is O(log n) average but O(n) worst case (when many duplicates force linear shrinking), space is O(1).
 */

class Solution {
    public int findMin(int[] nums) {
        int lo = 0, hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] > nums[hi]) {
                lo = mid + 1;
            } else if (nums[mid] < nums[hi]) {
                hi = mid;
            } else {
                // duplicate, can't tell which side, shrink search space safely
                hi--;
            }
        }
        return nums[lo];
    }
}
