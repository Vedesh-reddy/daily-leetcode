/*
 * Mirror Distance of an Integer (Easy)
 * https://leetcode.com/problems/mirror-distance-of-an-integer/
 *
 * The task is to reverse the digits of n and return the absolute difference between n and its reversal. I peel off digits from n one at a time using mod/divide by 10 and build up the reversed number, which naturally drops any leading zeros (e.g., 10 reversed becomes 1). Once the loop empties num, I just compute the absolute difference with Math.abs. Time complexity is O(d) where d is the number of digits in n, and space is O(1).
 */

class Solution {
    public int mirrorDistance(int n) {
        int rev = 0, num = n;
        while (num > 0) {
            rev = rev * 10 + num % 10;
            num /= 10;
        }
        return Math.abs(n - rev);
    }
}
