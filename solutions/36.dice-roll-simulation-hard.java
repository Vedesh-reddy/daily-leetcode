/*
 * Dice Roll Simulation (Hard)
 * https://leetcode.com/problems/dice-roll-simulation/
 *
 * The problem asks for the number of length-n dice sequences where no face i repeats more than rollMax[i] times in a row. I track dp[face][count] = number of valid sequences of the current length ending with "face" repeated exactly "count" times consecutively. For each new roll, switching to a different face resets its streak to 1 (summed from all states of other faces), while continuing the same face increments the streak count as long as it stays within rollMax. Iterating this n-1 times from the base case (length 1, count 1 for every face) and summing all dp states at the end gives the answer mod 1e9+7. Time complexity is O(n * 6 * 6 * maxRoll) which is efficient since rollMax values are small, and space is O(6 * maxRoll).
 */

class Solution {
    public int dieSimulator(int n, int[] rollMax) {
        final int MOD = 1_000_000_007;
        int maxRoll = 0;
        for (int r : rollMax) maxRoll = Math.max(maxRoll, r);

        // dp[face][count] = ways to reach state ending with 'face' repeated 'count' times
        long[][] dp = new long[6][maxRoll + 1];
        for (int f = 0; f < 6; f++) dp[f][1] = 1;

        for (int roll = 2; roll <= n; roll++) {
            long[][] ndp = new long[6][maxRoll + 1];
            for (int f = 0; f < 6; f++) {
                // sum of all ways ending in any other face (any count)
                long otherSum = 0;
                for (int of = 0; of < 6; of++) {
                    if (of == f) continue;
                    for (int c = 1; c <= rollMax[of]; c++) {
                        otherSum = (otherSum + dp[of][c]) % MOD;
                    }
                }
                ndp[f][1] = otherSum;
                for (int c = 2; c <= rollMax[f]; c++) {
                    ndp[f][c] = dp[f][c - 1];
                }
            }
            dp = ndp;
        }

        long total = 0;
        for (int f = 0; f < 6; f++) {
            for (int c = 1; c <= rollMax[f]; c++) {
                total = (total + dp[f][c]) % MOD;
            }
        }
        return (int) total;
    }
}
