/*
 * Determine Whether Matrix Can Be Obtained By Rotation (Easy)
 * https://leetcode.com/problems/determine-whether-matrix-can-be-obtained-by-rotation/
 *
 * We need to check if mat can become target via some multiple of 90-degree rotations (0, 90, 180, 270). The approach is simple: compare mat to target, and if they don't match, rotate mat 90 degrees clockwise and compare again, repeating up to 4 times total (after 4 rotations we're back to the original). Rotation is done by mapping mat[i][j] to res[j][n-1-i]. Time complexity is O(n^2) since we do a constant number of O(n^2) rotations and comparisons. Space complexity is O(n^2) for the rotated matrix copies.
 */

class Solution {
    public boolean findRotation(int[][] mat, int[][] target) {
        int n = mat.length;
        for (int k = 0; k < 4; k++) {
            if (matches(mat, target)) return true;
            mat = rotate(mat);
        }
        return false;
    }
    
    private boolean matches(int[][] a, int[][] b) {
        int n = a.length;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (a[i][j] != b[i][j]) return false;
        return true;
    }
    
    private int[][] rotate(int[][] mat) {
        int n = mat.length;
        int[][] res = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                res[j][n - 1 - i] = mat[i][j];
        return res;
    }
}
