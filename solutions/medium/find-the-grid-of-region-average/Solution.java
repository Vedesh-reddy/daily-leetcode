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
