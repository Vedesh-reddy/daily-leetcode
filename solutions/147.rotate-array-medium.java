/*
 * Rotate Array (Medium)
 * https://leetcode.com/problems/rotate-array/
 *
 * We need to shift every element right by k positions, wrapping around to the front. Using the reversal trick: reverse the whole array, then reverse the first k elements, then reverse the remaining n-k elements. This effectively rotates the array in place without extra storage. Time complexity is O(n) and space complexity is O(1), satisfying the in-place follow-up requirement.
 */

class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }
}
