/*
 * Minimum Remove to Make Valid Parentheses (Medium)
 * https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
 *
 * Need to strip the fewest parentheses so what remains is balanced, keeping letters untouched. Use a stack to track indices of unmatched '(' as we scan left to right; any ')' that finds no '(' on the stack is immediately marked for removal. After the scan, whatever indices remain on the stack are unmatched '(' and also get marked for removal. Finally build the result by skipping all marked indices. This runs in O(n) time and O(n) space for the stack and removal marker array.
 */

class Solution {
    public String minRemoveToMakeValid(String s) {
        char[] chars = s.toCharArray();
        Deque<Integer> stack = new ArrayDeque<>();
        boolean[] remove = new boolean[chars.length];

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                stack.push(i);
            } else if (chars[i] == ')') {
                if (!stack.isEmpty()) {
                    stack.pop();
                } else {
                    remove[i] = true;
                }
            }
        }

        // leftover '(' with no match
        while (!stack.isEmpty()) {
            remove[stack.pop()] = true;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if (!remove[i]) sb.append(chars[i]);
        }
        return sb.toString();
    }
}
