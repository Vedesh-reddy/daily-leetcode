/*
 * Goal Parser Interpretation (Easy)
 * https://leetcode.com/problems/goal-parser-interpretation/
 *
 * The task is to scan the command string and replace each token with its mapped value: "G" stays "G", "()" becomes "o", and "(al)" becomes "al", concatenating results in order. I walk through the string with an index pointer, checking the current character to decide which token it is and how far to advance (1, 2, or 4 positions), appending the corresponding substitution to a StringBuilder. This avoids regex or split overhead and processes the string in a single linear pass. Time complexity is O(n) where n is the length of command, and space complexity is O(n) for the output builder.
 */

class Solution {
    public String interpret(String command) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < command.length()) {
            char c = command.charAt(i);
            if (c == 'G') {
                sb.append('G');
                i++;
            } else if (c == '(' && command.charAt(i + 1) == ')') {
                sb.append('o');
                i += 2;
            } else {
                sb.append("al");
                i += 4;
            }
        }
        return sb.toString();
    }
}
