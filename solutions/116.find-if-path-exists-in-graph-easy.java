/*
 * Find if Path Exists in Graph (Easy)
 * https://leetcode.com/problems/find-if-path-exists-in-graph/
 *
 * We need to check whether source and destination belong to the same connected component in an undirected graph. Using union-find, every edge merges its two endpoints into the same set, with path compression keeping the find operation nearly O(1) amortized. After processing all edges, source and destination are connected iff they share the same root. Time complexity is O(E·α(n)) for building the DSU plus O(α(n)) for the final check, essentially linear; space is O(n) for the parent array. A BFS/DFS over an adjacency list would work equally well with O(V+E) time and space.
 */

class Solution {
    private int[] parent;

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        for (int[] e : edges) {
            union(e[0], e[1]);
        }

        return find(source) == find(destination);
    }

    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // path compression
        }
        return parent[x];
    }

    private void union(int a, int b) {
        int ra = find(a), rb = find(b);
        if (ra != rb) parent[ra] = rb;
    }
}
