/*
 * Find the Grid of Region Average (Medium)
 * https://leetcode.com/problems/find-the-grid-of-region-average/
 *
 * For every possible 3x3 top-left corner, check whether all adjacent pixel pairs within
 * that subgrid differ by at most threshold; if so it's a valid region, so compute its
 * floor average and add that value into accumulator arrays (sum and count) for each of
 * the 9 cells it covers. After scanning all subgrids, each cell's result is the floor of
 * its accumulated sum divided by count, or the original pixel value if it belongs to no
 * region. This brute-force scan checks O(m*n) subgrid origins, each requiring O(1) work
 * (9 cells, fixed adjacency checks), giving overall O(m*n) time and O(m*n) space for the
 * auxiliary arrays.
 */

class Solution {
    public int[][] resultGrid(int[][] image, int threshold) {
        int m = image.length, n = image[0].length;
        int[][] sum = new int[m][n];
        int[][] count = new int[m][n];

        for (int i = 0; i + 2 < m; i++) {
            for (int j = 0; j + 2 < n; j++) {
                if (isValidRegion(image, i, j, threshold)) {
                    int total = 0;
                    for (int r = i; r < i + 3; r++)
                        for (int c = j; c < j + 3; c++)
                            total += image[r][c];
                    int avg = total / 9;
                    for (int r = i; r < i + 3; r++) {
                        for (int c = j; c < j + 3; c++) {
                            sum[r][c] += avg;
                            count[r][c]++;
                        }
                    }
                }
            }
        }

        int[][] result = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = count[i][j] == 0 ? image[i][j] : sum[i][j] / count[i][j];
            }
        }
        return result;
    }

    private boolean isValidRegion(int[][] image, int i, int j, int threshold) {
        for (int r = i; r < i + 3; r++) {
            for (int c = j; c < j + 3; c++) {
                // check right neighbor
                if (c + 1 < j + 3 && Math.abs(image[r][c] - image[r][c + 1]) > threshold) return false;
                // check down neighbor
                if (r + 1 < i + 3 && Math.abs(image[r][c] - image[r + 1][c]) > threshold) return false;
            }
        }
        return true;
    }
}
