/*
 * Super Egg Drop (Hard)
 * https://leetcode.com/problems/super-egg-drop/
 *
 * Instead of thinking "given k eggs and n floors, how many moves", flip it to "given k eggs and m moves, what's the max number of floors we can distinguish". If we drop an egg and it breaks we have i-1 eggs and m-1 moves left below us; if it survives we have i eggs and m-1 moves left above, plus the floor we tested itself. That gives dp[i] = dp[i] + dp[i-1] + 1 (using previous move's values), updated in place from high egg count to low to avoid overwriting needed values. We increase move count until the max floors coverable with k eggs reaches n. This runs in O(k * moves) time where moves is O(log n), and O(k) space.
 */

class Solution {
    public int superEggDrop(int k, int n) {
        int[] dp = new int[k + 1]; // dp[i] = max floors coverable with i eggs at current move count
        int moves = 0;
        while (dp[k] < n) {
            moves++;
            for (int i = k; i >= 1; i--) {
                dp[i] = dp[i] + dp[i - 1] + 1;
            }
        }
        return moves;
    }
}
