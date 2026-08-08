/*
 * Maximum AND Sum of Array (Hard)
 * https://leetcode.com/problems/maximum-and-sum-of-array/
 *
 * Each slot can hold 0, 1, or 2 numbers, so a slot's occupancy fits in a base-3 digit; encoding all slots' counts into one integer gives a compact state space of size 3^numSlots. I do a DFS/memo where the recursion index (number of nums already placed) is derived implicitly from the state (sum of digits), so we just track which nums[] index we're on alongside the mask. At each step we try placing the current number into every slot that still has room (digit < 2), take the AND with (slot+1), and recurse into the updated mask, memoizing results per mask since the placed-count is uniquely determined once mask and greedy left-to-right processing order are fixed. Time complexity is O(3^numSlots * numSlots) and space is O(3^numSlots) for memoization, which is efficient given numSlots ≤ 9 per constraints.
 */

class Solution {
    private int[] nums;
    private int numSlots;
    private int n;
    private Integer[] memo;
    private int[] pow3;

    public int maximumANDSum(int[] nums, int numSlots) {
        this.nums = nums;
        this.numSlots = numSlots;
        this.n = nums.length;
        int total = 1;
        for (int i = 0; i < numSlots; i++) total *= 3;
        memo = new Integer[total];
        pow3 = new int[numSlots];
        pow3[0] = 1;
        for (int i = 1; i < numSlots; i++) pow3[i] = pow3[i-1] * 3;
        return dfs(0, 0);
    }

    // mask encodes base-3 digit per slot: how many items already placed there (0,1,2)
    private int dfs(int mask, int placed) {
        if (placed == n) return 0;
        if (memo[mask] != null) return memo[mask];

        int best = 0;
        int m = mask;
        for (int s = 0; s < numSlots; s++) {
            int cnt = m % 3;
            m /= 3;
            if (cnt < 2) {
                int newMask = mask + pow3[s];
                int val = (nums[placed] & (s + 1)) + dfs(newMask, placed + 1);
                if (val > best) best = val;
            }
        }
        memo[mask] = best;
        return best;
    }
}
