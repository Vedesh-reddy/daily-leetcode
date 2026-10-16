/*
 * Count Good Integers in a Range (Hard)
 * https://leetcode.com/problems/count-good-integers-in-a-range/
 *
 * A good integer is one where every pair of adjacent digits differs by at most k, which is essentially a generalized "stepping number" condition. I solved this with digit DP: count how many good integers lie in [0, n] via a recursive function over digit positions tracking the previous digit, whether we're still tight against n's prefix, and whether a nonzero digit has started (to correctly skip leading zeros). The answer is count(r) - count(l-1). Memoization is applied only on the non-tight branches since the tight branch depends on the specific prefix of n. Complexity is O(len * 10 * 2) states each doing O(10) work, so roughly O(len) effectively, with len being the number of digits of r (at most ~19 for a long), making this extremely fast even for very large ranges.
 */

class Solution {
    private String num;
    private int K;
    private Long[][][] memo;

    public long goodIntegers(long l, long r, int k) {
        return count(r, k) - count(l - 1, k);
    }

    private long count(long n, int k) {
        if (n < 0) return 0;
        num = Long.toString(n);
        K = k;
        memo = new Long[num.length()][11][2];
        return dp(0, 10, true, false);
    }

    private long dp(int pos, int prev, boolean tight, boolean started) {
        if (pos == num.length()) return started ? 1 : 0;

        if (!tight && memo[pos][prev][started ? 1 : 0] != null) {
            return memo[pos][prev][started ? 1 : 0];
        }

        int limit = tight ? num.charAt(pos) - '0' : 9;
        long res = 0;

        for (int d = 0; d <= limit; d++) {
            boolean nextTight = tight && (d == limit);
            if (!started && d == 0) {
                // still haven't placed a nonzero digit, leading zero continues
                res += dp(pos + 1, 10, nextTight, false);
            } else {
                if (started && Math.abs(d - prev) > K) continue;
                res += dp(pos + 1, d, nextTight, true);
            }
        }

        if (!tight) memo[pos][prev][started ? 1 : 0] = res;
        return res;
    }
}
