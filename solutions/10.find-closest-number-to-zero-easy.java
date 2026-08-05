/*
 * Find Closest Number to Zero (Easy)
 * https://leetcode.com/problems/find-closest-number-to-zero/
 *
 * We need the value in the array whose absolute value is smallest, and if there's a tie (a number and its negative both present), we prefer the larger (positive) one. The approach is a single linear scan keeping track of the best candidate so far: update it whenever we find a strictly closer value, or an equally close value that's larger. This handles the tie-breaking naturally since comparing `num > result` when absolute values match picks the positive one. Time complexity is O(n) and space complexity is O(1).
 */

class Solution {
    public int findClosestNumber(int[] nums) {
        int result = nums[0];
        for (int num : nums) {
            if (Math.abs(num) < Math.abs(result) || (Math.abs(num) == Math.abs(result) && num > result)) {
                result = num;
            }
        }
        return result;
    }
}
