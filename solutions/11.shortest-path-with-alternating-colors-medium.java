/*
 * Shortest Path with Alternating Colors (Medium)
 * https://leetcode.com/problems/shortest-path-with-alternating-colors/
 *
 * This is a BFS over an expanded state space where each node has two possible states depending on the color of the edge used to reach it last (red or blue), since paths must alternate colors. Starting from node 0 with both a "virtual red" and "virtual blue" incoming state lets the first move go either way. At each BFS step we only traverse edges of the opposite color from the last used one, marking (node, color) pairs visited to avoid revisiting, and recording the first time each node is reached at any color state as the shortest alternating path length. Nodes never reached remain -1. Time complexity is O(n + E) since each edge is processed once per color state, and space is O(n + E) for the adjacency lists and visited array.
 */

import java.util.*;

class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        List<List<Integer>> red = new ArrayList<>();
        List<List<Integer>> blue = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            red.add(new ArrayList<>());
            blue.add(new ArrayList<>());
        }
        for (int[] e : redEdges) red.get(e[0]).add(e[1]);
        for (int[] e : blueEdges) blue.get(e[0]).add(e[1]);

        int[] result = new int[n];
        Arrays.fill(result, -1);

        // state: node, color (0 = red used last, 1 = blue used last)
        boolean[][] visited = new boolean[n][2];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        queue.offer(new int[]{0, 1});
        visited[0][0] = true;
        visited[0][1] = true;
        int dist = 0;
        result[0] = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            dist++;
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int node = cur[0], color = cur[1];
                // if last used red, next must be blue, and vice versa
                List<Integer> next = color == 0 ? blue.get(node) : red.get(node);
                int nextColor = color == 0 ? 1 : 0;
                for (int nb : next) {
                    if (!visited[nb][nextColor]) {
                        visited[nb][nextColor] = true;
                        if (result[nb] == -1) result[nb] = dist;
                        queue.offer(new int[]{nb, nextColor});
                    }
                }
            }
        }

        return result;
    }
}
