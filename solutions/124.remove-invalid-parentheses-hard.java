/*
 * Remove Invalid Parentheses (Hard)
 * https://leetcode.com/problems/remove-invalid-parentheses/
 *
 * The task is to strip out the fewest parentheses so the remaining string has balanced brackets, collecting all distinct results that achieve that minimum. I used BFS over strings: starting from the original string, each level removes one more parenthesis character from every string in the current frontier. The first level where any string is valid is the minimum-removal level, so once a valid string is found at that level we stop expanding further (but still finish processing the rest of that level to collect all valid results). A visited set avoids re-processing duplicate strings. Time complexity is roughly O(2^n * n) in the worst case since each of the n parenthesis characters can be kept or removed, with n up to 20 here, and space is similarly bounded by the number of distinct generated strings.
 */

import java.util.*;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(s);
        visited.add(s);
        boolean found = false;

        while (!queue.isEmpty()) {
            String cur = queue.poll();
            if (isValid(cur)) {
                result.add(cur);
                found = true;
            }
            if (found) continue; // don't go deeper once we found valid strings at this level

            for (int i = 0; i < cur.length(); i++) {
                char c = cur.charAt(i);
                if (c != '(' && c != ')') continue;
                String next = cur.substring(0, i) + cur.substring(i + 1);
                if (!visited.contains(next)) {
                    visited.add(next);
                    queue.add(next);
                }
            }
        }

        if (result.isEmpty()) result.add("");
        return result;
    }

    private boolean isValid(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') count++;
            else if (c == ')') {
                count--;
                if (count < 0) return false;
            }
        }
        return count == 0;
    }
}
