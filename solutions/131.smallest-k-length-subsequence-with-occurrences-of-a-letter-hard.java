/*
 * Smallest K-Length Subsequence With Occurrences of a Letter (Hard)
 * https://leetcode.com/problems/smallest-k-length-subsequence-with-occurrences-of-a-letter/
 *
 * We need the lexicographically smallest length-k subsequence that contains `letter` at least `repetition` times. The approach builds a monotonic increasing stack while scanning s left to right: we pop a larger top character whenever it's safe — enough characters remain to still fill k slots, and popping it (if it's the target letter) won't drop the letter count below what's still achievable from the suffix. When deciding whether to push a non-target character, we only do so if there's still room left to accommodate the remaining required occurrences of `letter`, otherwise we skip it to preserve space. Precomputed suffix counts of `letter` let us check feasibility in O(1) at each step. Overall this runs in O(n) time (each character pushed/popped at most once) and O(n) space for the suffix array and stack.
 */

class Solution {
    public String smallestSubsequence(String s, int k, char letter, int repetition) {
        int n = s.length();
        int[] suffixCount = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            suffixCount[i] = suffixCount[i + 1] + (s.charAt(i) == letter ? 1 : 0);
        }

        StringBuilder stack = new StringBuilder();
        int countInStack = 0;

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            // pop while it helps lexicographically, keeps enough chars left to reach k, and doesn't break letter requirement
            while (stack.length() > 0 &&
                   stack.charAt(stack.length() - 1) > c &&
                   stack.length() - 1 + (n - i) >= k &&
                   (stack.charAt(stack.length() - 1) != letter ||
                    countInStack - 1 + suffixCount[i] >= repetition)) {
                char removed = stack.charAt(stack.length() - 1);
                stack.deleteCharAt(stack.length() - 1);
                if (removed == letter) countInStack--;
            }

            if (stack.length() < k) {
                if (c == letter) {
                    stack.append(c);
                    countInStack++;
                } else if (k - stack.length() > repetition - countInStack) {
                    // leave room for required letter occurrences still to come
                    stack.append(c);
                }
            }
        }

        return stack.toString();
    }
}
