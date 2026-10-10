/*
 * Equal Sum Arrays With Minimum Number of Operations (Medium)
 * https://leetcode.com/problems/equal-sum-arrays-with-minimum-number-of-operations/
 *
 * The key idea is that each operation can change a single element's contribution to the sum by at most 5 (since values range 1-6), so we want to greedily use operations that reduce the gap the most. First compute the sum difference and ensure nums1 has the larger sum (swap if needed). For each element in the larger array, decreasing it to 1 gives a potential reduction of (value-1); for each element in the smaller array, increasing it to 6 gives a potential increase of (6-value). Bucket these potential changes by magnitude (1 through 5) and greedily apply the largest-magnitude changes first until the difference is closed or exhausted. If all possible changes are used and the difference is still positive, return -1, otherwise return the operation count. Time complexity is O(n+m) for counting plus O(1) for the greedy loop over 5 buckets, and space is O(1) extra.
 */

class Solution {
    public int minOperations(int[] nums1, int[] nums2) {
        int sum1 = 0, sum2 = 0;
        for (int v : nums1) sum1 += v;
        for (int v : nums2) sum2 += v;
        if (sum1 == sum2) return 0;
        // make sum1 > sum2 by swapping if needed
        if (sum1 < sum2) {
            int[] tmp = nums1;
            nums1 = nums2;
            nums2 = tmp;
            int t = sum1;
            sum1 = sum2;
            sum2 = t;
        }
        int diff = sum1 - sum2;
        // count[d] = number of elements that can contribute a change of d (1..5)
        int[] count = new int[6];
        for (int v : nums1) count[v - 1]++; // can decrease by up to v-1
        for (int v : nums2) count[6 - v]++; // can increase by up to 6-v

        int ops = 0;
        for (int d = 5; d >= 1 && diff > 0; d--) {
            int use = Math.min(count[d], (diff + d - 1) / d);
            diff -= use * d;
            ops += use;
        }
        return diff <= 0 ? ops : -1;
    }
}
