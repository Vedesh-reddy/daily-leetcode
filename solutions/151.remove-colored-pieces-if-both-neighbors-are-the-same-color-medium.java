/*
 * Remove Colored Pieces if Both Neighbors are the Same Color (Medium)
 * https://leetcode.com/problems/remove-colored-pieces-if-both-neighbors-are-the-same-color/
 *
 * Each removable piece at index i requires colors[i-1], colors[i], colors[i+1] to all match. Count how many positions qualify for 'A' moves and how many for 'B' moves - these counts directly determine the total number of moves each player can make throughout the game, since removing a piece only affects counts locally but total available moves are fixed by the initial run-length structure (each run of length L of a color contributes max(L-2,0) removable moves). Alice wins if and only if she has strictly more available moves than Bob, since they alternate and whoever runs out first loses. Single pass O(n) time, O(1) space.
 */

class Solution {
    public boolean winnerOfGame(String colors) {
        int aMoves = 0, bMoves = 0;
        int n = colors.length();
        for (int i = 1; i < n - 1; i++) {
            if (colors.charAt(i) == colors.charAt(i-1) && colors.charAt(i) == colors.charAt(i+1)) {
                if (colors.charAt(i) == 'A') aMoves++;
                else bMoves++;
            }
        }
        return aMoves > bMoves;
    }
}
