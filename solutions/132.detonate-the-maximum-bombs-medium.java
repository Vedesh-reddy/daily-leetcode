/*
 * Detonate the Maximum Bombs (Medium)
 * https://leetcode.com/problems/detonate-the-maximum-bombs/
 *
 * Model each bomb as a node in a directed graph: an edge from bomb i to bomb j exists if j's location falls within i's blast radius (checked via squared distance to avoid floating-point sqrt). Detonating a bomb triggers a chain reaction, so from each starting bomb I run a BFS/DFS over this directed graph to count how many bombs get detonated, then take the max over all starting points. Time complexity is O(n^2) for building the graph plus O(n) BFS calls each O(n+e), overall O(n^3) worst case since edges can be up to n^2; space is O(n^2) for the adjacency lists.
 */

class Solution {
    public int maximumDetonation(int[][] bombs) {
        int n = bombs.length;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < n; i++) {
            long xi = bombs[i][0], yi = bombs[i][1], ri = bombs[i][2];
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                long xj = bombs[j][0], yj = bombs[j][1];
                long dx = xi - xj, dy = yi - yj;
                // use squared distance to avoid sqrt precision issues
                if (dx * dx + dy * dy <= ri * ri) {
                    graph.get(i).add(j);
                }
            }
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            boolean[] visited = new boolean[n];
            max = Math.max(max, bfs(i, graph, visited));
        }
        return max;
    }

    private int bfs(int start, List<List<Integer>> graph, boolean[] visited) {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        visited[start] = true;
        int count = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            count++;
            for (int next : graph.get(cur)) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }
        return count;
    }
}
