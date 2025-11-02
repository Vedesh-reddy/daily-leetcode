/*
 * Swim in Rising Water (Hard)
 * https://leetcode.com/problems/swim-in-rising-water/
 *
 * The task asks for the minimum water level t at which a path exists from top-left to bottom-right, where every cell on the path has elevation ≤ t. This is essentially finding the path that minimizes the maximum elevation encountered, which is a classic Dijkstra-style problem: use a min-heap keyed on cell elevation, always expand the currently lowest-elevation reachable frontier cell, and track the running maximum elevation seen so far as the answer. Once the bottom-right cell is popped from the heap, the tracked max is the answer since it represents the minimal possible "peak" elevation needed along any path. Time complexity is O(n^2 log n) due to heap operations over all cells, and space complexity is O(n^2) for the visited array and heap.
 */

class Solution {
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        boolean[][] visited = new boolean[n][n];
        pq.offer(new int[]{grid[0][0], 0, 0});
        visited[0][0] = true;
        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
        int ans = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int t = cur[0], x = cur[1], y = cur[2];
            ans = Math.max(ans, t);
            if (x == n - 1 && y == n - 1) return ans;

            for (int[] d : dirs) {
                int nx = x + d[0], ny = y + d[1];
                if (nx >= 0 && nx < n && ny >= 0 && ny < n && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    pq.offer(new int[]{grid[nx][ny], nx, ny});
                }
            }
        }
        return ans;
    }
}
