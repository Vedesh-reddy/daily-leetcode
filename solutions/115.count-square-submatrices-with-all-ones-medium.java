/*
 * Count Square Submatrices with All Ones (Medium)
 * https://leetcode.com/problems/count-square-submatrices-with-all-ones/
 *
 * The task is to count all square submatrices (of any size) made entirely of ones. I used dynamic programming where matrix[i][j] is repurposed to store the side length of the largest all-ones square ending at (i, j) as its bottom-right corner. For cells with value 1 (not on the first row/column), this equals 1 plus the minimum of the three neighboring values (top, left, top-left diagonal), since all three must support a smaller square for a larger one to form. Each cell's DP value directly represents the number of distinct squares ending there, so summing all values gives the total count. Time complexity is O(m*n) and space is O(1) extra since the input matrix is modified in place.
 */

class Solution {
    public int countSquares(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int count = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1 && i > 0 && j > 0) {
                    matrix[i][j] = Math.min(matrix[i-1][j-1], 
                                   Math.min(matrix[i-1][j], matrix[i][j-1])) + 1;
                }
                count += matrix[i][j];
            }
        }
        
        return count;
    }
}
