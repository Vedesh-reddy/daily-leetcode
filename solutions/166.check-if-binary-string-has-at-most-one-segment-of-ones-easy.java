/*
 * Check if Binary String Has at Most One Segment of Ones (Easy)
 * https://leetcode.com/problems/check-if-binary-string-has-at-most-one-segment-of-ones/
 *
 * The task is to determine whether the binary string has only a single contiguous block of '1's (or none/all ones). Since the string never has leading zeros, any second segment of ones would have to be preceded by a transition from '1' to '0' and then back to '1', meaning a "01" substring would appear before that new segment starts. So simply checking for the absence of "01" in the string tells us if there's more than one segment. This runs in O(n) time and O(1) extra space (aside from the substring search internals).
 */

class Solution {
    public boolean checkOnesSegment(String s) {
        // if more than one segment, there must be a "01" somewhere
        return !s.contains("01");
    }
}
