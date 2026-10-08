/*
 * Number of Beautiful Partitions (Hard)
 * https://leetcode.com/problems/number-of-beautiful-partitions/
 *
 * We need to split s into exactly k contiguous pieces, each at least minLength long, each starting with a prime digit (2,3,5,7) and ending with a non-prime digit. I use a DP where dp[i][j] counts ways to form j valid pieces using the first i characters, with position i being the end of the j-th piece (so s[i-1] must be non-prime). To extend from j-1 to j, we need a previous cut point p (0 <= p <= i-minLength) where s[p] is prime, so I maintain a running prefix sum over valid prime-start positions of dp[p][j-1] to answer each dp[i][j] in O(1). This reduces the total complexity to O(n*k) time and O(n) space (rolling the j dimension). The answer is dp[n][k] taken mod 1e9+7, with quick early exits if the first/last character constraints or k*minLength > n make it trivially impossible.
 */

class Solution {
    public int beautifulPartitions(String s, int k, int minLength) {
        int n = s.length();
        int MOD = 1_000_000_007;

        // quick impossible checks
        if (!isPrime(s.charAt(0)) || isPrime(s.charAt(n - 1))) return 0;
        if ((long) k * minLength > n) return 0;

        long[] dpPrev = new long[n + 1];
        dpPrev[0] = 1; // j = 0 partitions, only position 0 is valid "end"

        for (int j = 1; j <= k; j++) {
            long[] prefix = new long[n + 1];
            for (int p = 1; p <= n; p++) {
                long add = 0;
                if (isPrime(s.charAt(p - 1))) add = dpPrev[p - 1];
                prefix[p] = (prefix[p - 1] + add) % MOD;
            }

            long[] dpCur = new long[n + 1];
            for (int i = j * minLength; i <= n; i++) {
                if (!isPrime(s.charAt(i - 1))) {
                    int limit = i - minLength; // p ranges 0..limit
                    if (limit >= 0) {
                        dpCur[i] = prefix[limit + 1];
                    }
                }
            }
            dpPrev = dpCur;
        }

        return (int) (dpPrev[n] % MOD);
    }

    private boolean isPrime(char c) {
        return c == '2' || c == '3' || c == '5' || c == '7';
    }
}
