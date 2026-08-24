/*
 * Minimize Array Sum Using Divisible Replacements (Medium)
 * https://leetcode.com/problems/minimize-array-sum-using-divisible-replacements/
 *
 * Since a % b == 0 combined with b % c == 0 implies a % c == 0, every number in the array can eventually be replaced by the smallest value in the array that divides it (chaining replacements). So the problem reduces to, for each distinct value v present in nums, finding the smallest present value that divides v, then summing that smallest divisor for every occurrence of v. I precompute this with a sieve: for each present divisor d (ascending), mark all its multiples that are also present in the array with best[m] = d if not already set, guaranteeing the smallest divisor is recorded first. Finally sum best[nums[i]] over all elements. Time complexity is O(maxV log maxV) for the sieve plus O(n) for the final summation, and space is O(maxV).
 */

class Solution {
    public long minArraySum(int[] nums) {
        int maxV = 0;
        for (int x : nums) maxV = Math.max(maxV, x);
        boolean[] present = new boolean[maxV + 1];
        for (int x : nums) present[x] = true;

        int[] best = new int[maxV + 1]; // smallest present divisor for each present value

        // sieve-like pass: for each divisor d present, mark first (smallest) divisor for its multiples
        for (int d = 1; d <= maxV; d++) {
            if (!present[d]) continue;
            for (int m = d; m <= maxV; m += d) {
                if (present[m] && best[m] == 0) {
                    best[m] = d;
                }
            }
        }

        long sum = 0;
        for (int x : nums) {
            sum += best[x];
        }
        return sum;
    }
}
