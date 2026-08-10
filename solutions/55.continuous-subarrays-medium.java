/*
 * Continuous Subarrays (Medium)
 * https://leetcode.com/problems/continuous-subarrays/
 *
 * The task asks for subarrays where the max and min differ by at most 2, since |a-b| condition effectively bounds the range. I used a sliding window with two monotonic deques tracking the indices of the current window's max and min values. As the right pointer expands, I update both deques, and while the window's max-min exceeds 2, I shrink from the left, popping expired indices from the deques. For each right position, the number of valid subarrays ending there is right-left+1, which I accumulate into the answer. This runs in O(n) time and O(n) space due to the deques.
 */

class Solution {
    public long continuousSubarrays(int[] nums) {
        Deque<Integer> maxD = new ArrayDeque<>();
        Deque<Integer> minD = new ArrayDeque<>();
        int left = 0;
        long count = 0;
        for (int right = 0; right < nums.length; right++) {
            while (!maxD.isEmpty() && nums[maxD.peekLast()] <= nums[right]) maxD.pollLast();
            maxD.addLast(right);
            while (!minD.isEmpty() && nums[minD.peekLast()] >= nums[right]) minD.pollLast();
            minD.addLast(right);

            while (nums[maxD.peekFirst()] - nums[minD.peekFirst()] > 2) {
                if (maxD.peekFirst() == left) maxD.pollFirst();
                if (minD.peekFirst() == left) minD.pollFirst();
                left++;
            }

            count += right - left + 1;
        }
        return count;
    }
}
