/*
 * Word Search (Medium)
 * https://leetcode.com/problems/word-search/
 *
 * The task is to find if a word can be traced through adjacent (up/down/left/right) cells in the grid without reusing a cell. I used backtracking DFS: for each cell matching the word's first character, recursively explore all four directions while marking the current cell as visited (using a temporary marker character) and restoring it after the recursive call returns. The base case succeeds when the full word index is matched, and fails early on out-of-bounds or mismatched characters, which naturally prunes the search. Time complexity is O(m*n*4^L) in the worst case where L is the word length, and space complexity is O(L) for the recursion stack (board is modified in-place, no extra visited array needed).
 */

class Solution {
    public boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, word, i, j, 0)) return true;
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int i, int j, int idx) {
        if (idx == word.length()) return true;
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(idx)) {
            return false;
        }

        char temp = board[i][j];
        board[i][j] = '#'; // mark visited
        boolean found = dfs(board, word, i + 1, j, idx + 1) ||
                         dfs(board, word, i - 1, j, idx + 1) ||
                         dfs(board, word, i, j + 1, idx + 1) ||
                         dfs(board, word, i, j - 1, idx + 1);
        board[i][j] = temp; // backtrack

        return found;
    }
}
