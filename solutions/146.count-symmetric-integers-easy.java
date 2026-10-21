/*
 *   Count Symmetric Integers (Easy)
 * https://leetcode.com/problems/count-symmetric-integers/
 *
 * We need to count numbers in [low, high] whose digit count is even and whose first-half digit sum equals second-half digit sum. Since constraints keep high small, a brute-force scan over every integer in the range works fine: convert to string, skip odd-length numbers, split into two halves, and compare sums. Time complexity is O((high-low) * d) where d is the digit count (small constant), and space is O(d) for the string representation.
 */

class Solution {
    public int countSymmetricIntegers(int low, int high) {
        int count = 0;
        for (int x = low; x <= high; x++) {
            String s = String.valueOf(x);
            int n = s.length();
            if (n % 2 != 0) continue;
            int half = n / 2;
            int sum1 = 0, sum2 = 0;
            for (int i = 0; i < half; i++) sum1 += s.charAt(i) - '0';
            for (int i = half; i < n; i++) sum2 += s.charAt(i) - '0';
            if (sum1 == sum2) count++;
        }
        return count;
    }
}
