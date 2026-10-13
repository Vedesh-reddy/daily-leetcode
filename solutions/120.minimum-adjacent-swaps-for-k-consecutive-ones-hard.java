/*
 * Minimum Adjacent Swaps for K Consecutive Ones (Hard)
 * https://leetcode.com/problems/minimum-adjacent-swaps-for-k-consecutive-ones/
 *
 * We only care about the positions of the 1's; moving a 0 past a 1 costs one swap per step, so the problem reduces to gathering k of the 1's into consecutive slots with minimum total movement. Subtracting the index from each 1's position (pos[i]-i) removes the effect of the 0's between them, turning the cost into a pure "align k numbers to consecutive integers" problem, which is minimized by choosing the median of the window as the target and summing absolute differences. Prefix sums let us compute the left/right sums around the median in O(1) per window, so sliding the window of size k across all 1-positions gives the answer in O(n) time and O(n) space (n = number of 1's). The final answer is the minimum total cost over all windows.
 */

class Solution {
    public int minMoves(int[] nums, int k) {
        int n = nums.length;
        List<Integer> posList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) posList.add(i);
        }
        int q = posList.size();
        long[] pos = new long[q];
        for (int i = 0; i < q; i++) {
            pos[i] = posList.get(i) - i; // normalize so gaps collapse
        }

        long[] prefix = new long[q + 1];
        for (int i = 0; i < q; i++) {
            prefix[i + 1] = prefix[i] + pos[i];
        }

        long best = Long.MAX_VALUE;
        for (int i = 0; i + k <= q; i++) {
            int mid = i + k / 2;
            long medianVal = pos[mid];

            // left side: mid - j for j in [i, mid-1]
            long leftCount = mid - i;
            long leftSum = prefix[mid] - prefix[i];
            long leftCost = medianVal * leftCount - leftSum;

            // right side: j - mid for j in [mid+1, i+k-1]
            long rightCount = (i + k - 1) - mid;
            long rightSum = prefix[i + k] - prefix[mid + 1];
            long rightCost = rightSum - medianVal * rightCount;

            long total = leftCost + rightCost;
            if (total < best) best = total;
        }

        return (int) best;
    }
}
