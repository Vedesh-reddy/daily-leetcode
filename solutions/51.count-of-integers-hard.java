/*
 * Count of Integers (Hard)
 * https://leetcode.com/problems/count-of-integers/
 *
 * This is a classic digit-DP problem: count integers x with num1 <= x <= num2 whose digit sum lies in [min_sum, max_sum]. I compute f(N) = count of valid integers in [0, N] using a tight-bound recursive digit DP with memoization on (position, current digit sum) for the "free" (non-tight) branches, then the answer is f(num2) - f(num1 - 1), handling the edge case where num1 is "0" via a subtractOne helper that returns a sentinel "-1". The DP explores each digit position choosing 0..9 (or up to the bound digit when tight), pruning early if the running sum already exceeds max_sum, and checks the final sum against [min_sum, max_sum] once all digits are placed. Complexity is O(L * maxSum * 10) per call where L is the string length (up to 22) and maxSum up to 400, so it's very fast; space is O(L * maxSum) for memoization.
 */

class Solution {
    static final int MOD = 1_000_000_007;
    int minSum, maxSum;
    Integer[][] memo;

    public int count(String num1, String num2, int min_sum, int max_sum) {
        this.minSum = min_sum;
        this.maxSum = max_sum;
        long a = solve(num2);
        long b = solve(subtractOne(num1));
        long ans = (a - b) % MOD;
        if (ans < 0) ans += MOD;
        return (int) ans;
    }

    private long solve(String num) {
        if (num.equals("-1")) return 0; // num1 was "0" equivalent edge, no numbers below
        memo = new Integer[num.length()][maxSum + 1];
        return dfs(0, 0, true, num);
    }

    private long dfs(int pos, int sum, boolean tight, String num) {
        if (sum > maxSum) return 0;
        if (pos == num.length()) {
            return (sum >= minSum && sum <= maxSum) ? 1 : 0;
        }
        if (!tight && memo[pos][sum] != null) return memo[pos][sum];

        int limit = tight ? num.charAt(pos) - '0' : 9;
        long total = 0;
        for (int d = 0; d <= limit; d++) {
            total += dfs(pos + 1, sum + d, tight && d == limit, num);
        }
        total %= MOD;
        if (!tight) memo[pos][sum] = (int) total;
        return total;
    }

    // subtract one from a numeric string, returns "-1" if input is "0"
    private String subtractOne(String num) {
        char[] arr = num.toCharArray();
        int i = arr.length - 1;
        while (i >= 0 && arr[i] == '0') {
            arr[i] = '9';
            i--;
        }
        if (i < 0) return "-1"; // was all zeros, i.e., "0"
        arr[i]--;
        int start = 0;
        while (start < arr.length - 1 && arr[start] == '0') start++;
        return new String(arr, start, arr.length - start);
    }
}
