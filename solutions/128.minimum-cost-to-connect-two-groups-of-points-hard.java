/*
 * Minimum Cost to Connect Two Groups of Points (Hard)
 * https://leetcode.com/problems/minimum-cost-to-connect-two-groups-of-points/
 *
 * We need every point in group1 connected to at least one point in group2 and vice versa, minimizing total edge cost. I used bitmask DP where the mask tracks which group2 points have been covered so far while processing group1 points one by one in order. At each row i, we try connecting it to every possible column j, forcing that connection and recursing to the next row with the updated mask (this guarantees row i gets at least one edge). Once all rows are processed, any group2 column not yet covered must be paid for using its cheapest available edge to any row, since it still needs a connection. Complexity is O(size1 * 2^size2 * size2) time and O(size1 * 2^size2) space for memoization, which is efficient given typical constraints (size2 ≤ ~15).
 */

class Solution {
    public int connectTwoGroups(List<List<Integer>> cost) {
        int size1 = cost.size();
        int size2 = cost.get(0).size();
        int fullMask = (1 << size2) - 1;

        // min cost to connect each point in group2 to any point in group1
        int[] minCol = new int[size2];
        Arrays.fill(minCol, Integer.MAX_VALUE);
        for (int j = 0; j < size2; j++) {
            for (int i = 0; i < size1; i++) {
                minCol[j] = Math.min(minCol[j], cost.get(i).get(j));
            }
        }

        Integer[][] memo = new Integer[size1][1 << size2];
        return dp(0, 0, cost, size1, size2, fullMask, minCol, memo);
    }

    private int dp(int i, int mask, List<List<Integer>> cost, int size1, int size2,
                   int fullMask, int[] minCol, Integer[][] memo) {
        if (i == size1) {
            // pay for any uncovered group2 points individually
            int res = 0;
            for (int j = 0; j < size2; j++) {
                if ((mask & (1 << j)) == 0) {
                    res += minCol[j];
                }
            }
            return res;
        }
        if (memo[i][mask] != null) return memo[i][mask];

        int best = Integer.MAX_VALUE;
        for (int j = 0; j < size2; j++) {
            int c = cost.get(i).get(j) + dp(i + 1, mask | (1 << j), cost, size1, size2, fullMask, minCol, memo);
            best = Math.min(best, c);
        }
        memo[i][mask] = best;
        return best;
    }
}
