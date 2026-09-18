/*
 * Shortest Path in a Grid with Obstacles Elimination (Hard)
 * https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/
 *
 * This is a shortest-path-with-state BFS problem: the state is not just the cell but also how many obstacle eliminations remain, since revisiting a cell with more remaining eliminations could still lead to a better path later. I do a standard multi-source-style level BFS where each queue entry is (row, col, remaining k), and track the best (max) remaining k seen at each cell to prune states that can't possibly improve on a prior visit. As an optimization, if k is already at least m+n-2 (the minimum possible Manhattan-distance path length), we can just return that straight-line distance since we could eliminate every obstacle along a direct path. Time complexity is O(m*n*k) since each cell can be enqueued once per distinct remaining-k value, and space is O(m*n) for the best-remaining tracking array plus the queue.
 */

class Solution {
    public int shortestPath(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        if (m == 1 && n == 1) return 0;
        
        // if we can eliminate enough obstacles to just go straight, shortcut
        if (k >= m + n - 2) return m + n - 2;
        
        int[][] best = new int[m][n];
        for (int[] row : best) java.util.Arrays.fill(row, -1);
        
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0, k});
        best[0][0] = k;
        
        int steps = 0;
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            steps++;
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int r = cur[0], c = cur[1], rem = cur[2];
                for (int[] d : dirs) {
                    int nr = r + d[0], nc = c + d[1];
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                    int nrem = rem - grid[nr][nc];
                    if (nrem < 0) continue;
                    if (nr == m - 1 && nc == n - 1) return steps;
                    if (nrem > best[nr][nc]) {
                        best[nr][nc] = nrem;
                        queue.offer(new int[]{nr, nc, nrem});
                    }
                }
            }
        }
        
        return -1;
    }
}
