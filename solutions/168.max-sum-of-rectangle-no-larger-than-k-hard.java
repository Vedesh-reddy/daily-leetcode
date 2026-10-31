/*
 * Max Sum of Rectangle No Larger Than K (Hard)
 * https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/
 *
 * This is the classic 2D Kadane extension: fix a pair of left/right column boundaries, collapse the matrix between them into a 1D array of row sums, then solve the 1D "max subarray sum no larger than k" using prefix sums with a TreeSet to binary-search for the smallest prefix >= (currentPrefix - k). To keep the complexity down we always loop over the smaller of rows/cols by transposing if rows > cols. Time complexity is O(min(m,n)^2 * max(m,n) * log(max(m,n))) due to the TreeSet operations, and space is O(max(m,n)) for the row sums and prefix set. This handles the follow-up naturally since transposing lets us always pick the smaller dimension to iterate pairs over.
 */

class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        // iterate over the smaller dimension for fewer pair combos
        if (m > n) {
            int[][] t = new int[n][m];
            for (int i = 0; i < m; i++)
                for (int j = 0; j < n; j++)
                    t[j][i] = matrix[i][j];
            matrix = t;
            int tmp = m; m = n; n = tmp;
        }

        int result = Integer.MIN_VALUE;

        for (int left = 0; left < m; left++) {
            int[] rowSum = new int[n];
            for (int right = left; right < m; right++) {
                for (int j = 0; j < n; j++) {
                    rowSum[j] += matrix[right][j];
                }

                // find max subarray sum <= k using prefix sums + TreeSet
                TreeSet<Integer> set = new TreeSet<>();
                set.add(0);
                int prefix = 0;
                for (int sum : rowSum) {
                    prefix += sum;
                    Integer ceiling = set.ceiling(prefix - k);
                    if (ceiling != null) {
                        result = Math.max(result, prefix - ceiling);
                    }
                    set.add(prefix);
                }
            }
        }

        return result;
    }
}
