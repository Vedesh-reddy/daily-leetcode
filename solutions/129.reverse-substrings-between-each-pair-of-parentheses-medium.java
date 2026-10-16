/*
 * Reverse Substrings Between Each Pair of Parentheses (Medium)
 * https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/
 *
 * We need to reverse text inside parentheses, innermost first, and strip all brackets from the result. Using a stack of StringBuilders works well: push a new builder when we hit '(', append chars to the current top builder otherwise, and when we hit ')' pop the top builder, reverse it, and append it to the new top (its parent context). This way nested reversals happen naturally without needing to track matching indices explicitly. Time complexity is O(n^2) in the worst case (deeply nested parentheses causing repeated reversals/appends), though in practice it's close to O(n) for typical inputs; space is O(n) for the stack contents.
 */

class Solution {
    public String reverseParentheses(String s) {
        Deque<StringBuilder> stack = new ArrayDeque<>();
        stack.push(new StringBuilder());
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(new StringBuilder());
            } else if (c == ')') {
                StringBuilder top = stack.pop();
                top.reverse();
                stack.peek().append(top);
            } else {
                stack.peek().append(c);
            }
        }
        
        return stack.pop().toString();
    }
}
