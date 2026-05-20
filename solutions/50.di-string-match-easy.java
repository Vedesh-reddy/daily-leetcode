/*
 * DI String Match (Easy)
 * https://leetcode.com/problems/di-string-match/
 *
 * The idea is to use two pointers, lo starting at 0 and hi starting at n, since these are the smallest and largest available values. For each character, if it's 'I' we need an increase so we place the current lowest unused value; if it's 'D' we need a decrease so we place the current highest unused value. After processing all characters, lo and hi converge to the same value, which fills the last slot. This greedy approach always satisfies the constraints since placing the extreme values guarantees the required relative order. Time complexity is O(n) and space complexity is O(n) for the output array.
 */

class Solution {
    public int[] diStringMatch(String s) {
        int n = s.length();
        int[] res = new int[n + 1];
        int lo = 0, hi = n;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'I') {
                res[i] = lo++;
            } else {
                res[i] = hi--;
            }
        }
        res[n] = lo; // lo == hi at this point
        return res;
    }
}
