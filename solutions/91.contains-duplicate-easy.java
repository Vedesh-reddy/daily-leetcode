/*
 * Contains Duplicate (Easy)
 * https://leetcode.com/problems/contains-duplicate/
 *
 * Add values to a hash set; if an add fails, the value was already present. Time O(n), space O(n).
 */

import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (!seen.add(num)) return true;
        }
        return false;
    }
}
