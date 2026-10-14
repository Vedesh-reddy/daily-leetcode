/*
 * Count the Number of Infection Sequences (Hard)
 * https://leetcode.com/problems/count-the-number-of-infection-sequences/
 *
 * The healthy people are split into gaps by the sick positions: a left boundary gap, a right boundary gap, and middle gaps between consecutive sick people. Boundary gaps can only be infected from one side so there's exactly one valid internal order, while a middle gap of size k can be infected from either end at each step giving 2^(k-1) valid internal orders. Treating each gap's valid internal order as a fixed subsequence, the total number of ways to interleave all gaps into one global sequence is the multinomial coefficient (total_healthy)! / (product of gap_i!), which is then multiplied by the product of 2^(k-1) over all middle gaps. Modular inverses via Fermat's little theorem handle the division under modulo 1e9+7. Time complexity O(n) for factorial precomputation plus O(m log MOD) for modular exponentiations; space O(n) for factorials.
 */

class Solution {
    static final int MOD = 1_000_000_007;

    public int numberOfSequence(int n, int[] sick) {
        int m = sick.length;
        long[] fact = new long[n + 1];
        fact[0] = 1;
        for (int i = 1; i <= n; i++) fact[i] = fact[i - 1] * i % MOD;

        int totalHealthy = n - m;
        long ans = fact[totalHealthy];

        // first gap (before sick[0]) - boundary, only one direction
        int firstGap = sick[0];
        ans = ans * modInverse(fact[firstGap]) % MOD;

        // last gap (after sick[m-1]) - boundary
        int lastGap = n - 1 - sick[m - 1];
        ans = ans * modInverse(fact[lastGap]) % MOD;

        // middle gaps - two-sided, contribute 2^(k-1) internal orderings
        for (int i = 0; i + 1 < m; i++) {
            int gap = sick[i + 1] - sick[i] - 1;
            if (gap > 0) {
                ans = ans * modInverse(fact[gap]) % MOD;
                ans = ans * modPow(2, gap - 1) % MOD;
            }
        }

        return (int) ans;
    }

    private long modPow(long base, long exp) {
        long result = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1) result = result * base % MOD;
            base = base * base % MOD;
            exp >>= 1;
        }
        return result;
    }

    private long modInverse(long a) {
        return modPow(a, MOD - 2);
    }
}
