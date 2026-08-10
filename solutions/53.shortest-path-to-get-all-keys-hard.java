/*
 * Shortest Path to Get All Keys (Hard)
 * https://leetcode.com/problems/shortest-path-to-get-all-keys/
 *
 * The task is a BFS over an expanded state space where each state is (row, col, keysCollectedMask). Locks block movement unless the corresponding key bit is already set in the mask, and stepping on a key cell sets that bit going forward. Since there are at most 6 keys, the mask fits in 6 bits, giving at most m*n*64 distinct states, so a standard visited[][][] array keeps BFS efficient. BFS naturally finds the shortest path since all moves cost 1, and we terminate as soon as we dequeue a state where mask equals the target "all keys collected" mask. Time complexity is O(m*n*2^k) and space is the same for the visited array and queue, where k is number of keys (≤6). If the queue empties without reaching the full mask, it's unreachable, so return -1.
 */

class Solution {
    public int shortestPathAllKeys(String[] grid) {
        int m = grid.length, n = grid[0].length();
        int sx = -1, sy = -1, allKeys = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = grid[i].charAt(j);
                if (c == '@') { sx = i; sy = j; }
                else if (c >= 'a' && c <= 'f') allKeys |= (1 << (c - 'a'));
            }
        }

        boolean[][][] visited = new boolean[m][n][1 << 6];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sx, sy, 0, 0}); // x, y, mask, steps
        visited[sx][sy][0] = true;

        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1], mask = cur[2], steps = cur[3];
            if (mask == allKeys) return steps;

            for (int[] d : dirs) {
                int nx = x + d[0], ny = y + d[1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                char c = grid[nx].charAt(ny);
                if (c == '#') continue;
                int nmask = mask;
                if (c >= 'A' && c <= 'F') {
                    // need corresponding key
                    if ((mask & (1 << (c - 'A'))) == 0) continue;
                } else if (c >= 'a' && c <= 'f') {
                    nmask = mask | (1 << (c - 'a'));
                }
                if (!visited[nx][ny][nmask]) {
                    visited[nx][ny][nmask] = true;
                    queue.offer(new int[]{nx, ny, nmask, steps + 1});
                }
            }
        }

        return -1;
    }
}
