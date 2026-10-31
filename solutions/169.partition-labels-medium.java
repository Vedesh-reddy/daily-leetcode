/*
 * Partition Labels (Medium)
 * https://leetcode.com/problems/partition-labels/
 *
 * We need to split the string into the maximum number of contiguous chunks such that no letter spills across two chunks. First pass: record the last index each character appears at. Then sweep through the string tracking the furthest "end" boundary required by characters seen so far in the current chunk; whenever the current index reaches that boundary, the chunk is complete and can be cut there. This greedy approach works because extending the end only happens when a new character forces the chunk to grow, and once no pending characters demand more room, it's safe to close the partition. Time complexity O(n), space O(1) extra (ignoring output list) since the alphabet size is fixed at 26.
 */

import java.util.*;

class Solution {
    public List<Integer> partitionLabels(String s) {
        int[] last = new int[26];
        for (int i = 0; i < s.length(); i++) {
            last[s.charAt(i) - 'a'] = i;
        }

        List<Integer> result = new ArrayList<>();
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            end = Math.max(end, last[s.charAt(i) - 'a']);
            if (i == end) {
                result.add(end - start + 1);
                start = i + 1;
            }
        }
        return result;
    }
}
