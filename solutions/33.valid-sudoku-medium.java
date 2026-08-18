/*
 * Valid Sudoku (Medium)
 * https://leetcode.com/problems/valid-sudoku/
 *
 * Need to check that no digit 1-9 repeats in any row, column, or 3x3 box among filled cells. I use three boolean arrays (rows, cols, boxes) each sized 9x9, indexed by the row/col/box number and the digit (0-8). The box index is computed as (r/3)*3 + (c/3), a standard trick to map a 9x9 grid into 9 sub-boxes. Single pass over all 81 cells, checking and marking each tracker as I go, returning false immediately on a duplicate. Time complexity is O(81) which is O(1) for fixed board size, space is O(1) as well since arrays are fixed size.
 */

class Solution {
    public boolean isValidSudoku(char[][] board) {
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] boxes = new boolean[9][9];

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char ch = board[r][c];
                if (ch == '.') continue;
                int num = ch - '1';
                int boxIdx = (r / 3) * 3 + (c / 3);

                if (rows[r][num] || cols[c][num] || boxes[boxIdx][num]) {
                    return false;
                }
                rows[r][num] = true;
                cols[c][num] = true;
                boxes[boxIdx][num] = true;
            }
        }
        return true;
    }
}
