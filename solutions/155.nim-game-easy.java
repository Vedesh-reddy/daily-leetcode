/*
 * Nim Game (Easy)
 * https://leetcode.com/problems/nim-game/
 *
 * We need to determine if the first player can force a win in Nim where each turn removes 1-3 stones and taking the last stone wins. The key insight is that if n is a multiple of 4, whatever number (1-3) the current player removes, the opponent can always remove enough to bring the total removed in that round to 4, eventually leaving the opponent to take the last stone. So multiples of 4 are losing positions, and all other values are winning positions since you can always reduce the heap to a multiple of 4 for your opponent. The solution is simply checking n % 4 != 0. Time complexity is O(1) and space complexity is O(1).
 */

class Solution {
    public boolean canWinNim(int n) {
        // lose only when n is a multiple of 4
        return n % 4 != 0;
    }
}
