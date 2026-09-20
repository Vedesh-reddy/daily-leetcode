/*
 * Valid Parentheses (Easy)
 * https://leetcode.com/problems/valid-parentheses/
 *
 * Push the expected closing bracket for every opener onto a stack; every closer must match the top. The string is valid only if the stack ends empty. Time O(n), space O(n).
 */

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(') stack.push(')');
            else if (c == '[') stack.push(']');
            else if (c == '{') stack.push('}');
            else if (stack.isEmpty() || stack.pop() != c) return false;
        }
        return stack.isEmpty();
    }
}
