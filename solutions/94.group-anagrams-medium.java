/*
 * Group Anagrams (Medium)
 * https://leetcode.com/problems/group-anagrams/
 *
 * Anagrams share the same sorted character sequence, so use that as a hash map key and collect words under it. Time O(n * k log k), space O(n * k).
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> groups = new HashMap<>();
        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            groups.computeIfAbsent(new String(chars), k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(groups.values());
    }
}
