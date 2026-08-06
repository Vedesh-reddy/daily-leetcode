/*
 * Concatenate Non-Zero Digits and Multiply by Sum I (Easy)
 * https://leetcode.com/problems/concatenate-non-zero-digits-and-multiply-by-sum-i/
 *
 * The task is to strip out zero digits from n, glue the remaining digits together to form x (preserving original order), sum x's digits, and return x multiplied by that sum. Since building x from the least significant digit means the digits come out in reverse order, I accumulate them using an increasing power-of-ten multiplier so the final x naturally comes out in the correct original order without needing a separate reversal step. If all digits are zero (or n is 0), x stays 0 and sum stays 0, giving a result of 0, matching the problem's edge case. Time complexity is O(log n) since we process each digit once, and space is O(1).
 */

class Solution {
    public long sumAndMultiply(int n) {
        long x = 0;
        long sum = 0;
        // extract digits in reverse, skip zeros, build reversed x
        long multiplier = 1;
        while (n > 0) {
            int d = n % 10;
            n /= 10;
            if (d != 0) {
                x += d * multiplier;
                multiplier *= 10;
                sum += d;
            }
        }
        return x * sum;
    }
}
