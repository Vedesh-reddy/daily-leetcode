/*
 * Two Sum (Easy)
 * https://leetcode.com/problems/two-sum/
 *
 * Store each value's index in a hash map while scanning; for each number check whether its complement was already seen. One pass finds the pair. Time O(n), space O(n).
 */

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> seen = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer j = seen.get(target - nums[i]);
            if (j != null) {
                return new int[] {j, i};
            }
            seen.put(nums[i], i);
        }
        return new int[0];
    }
}
