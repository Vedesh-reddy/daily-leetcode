/*
 * Reachable Nodes In Subdivided Graph (Hard)
 * https://leetcode.com/problems/reachable-nodes-in-subdivided-graph/
 *
 * The idea is to treat each original node normally but recognize that each edge's subdivided nodes form a path, so the number reachable from either endpoint depends on remaining moves after reaching that endpoint. First run Dijkstra from node 0 on the original graph, using edge weight = cnt+1 (the number of steps to cross the full subdivided edge), which gives the shortest distance to each original node. Count original nodes with dist <= maxMoves. Then for each edge, compute how many subdivision nodes are reachable from u's side (min(cnt, maxMoves - dist[u])) and from v's side similarly, capping the sum at cnt since nodes can't be double counted. Sum these up with the original node count for the final answer. Time complexity is O(E log V) for Dijkstra plus O(E) for the edge processing, space is O(V + E).
 */

class Solution {
    public int reachableNodes(int[][] edges, int maxMoves, int n) {
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        for (int[] e : edges) {
            graph.computeIfAbsent(e[0], k -> new HashMap<>()).put(e[1], e[2]);
            graph.computeIfAbsent(e[1], k -> new HashMap<>()).put(e[0], e[2]);
        }
        
        // dijkstra from node 0
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[]{0, 0});
        boolean[] visited = new boolean[n];
        
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int node = cur[0], d = cur[1];
            if (visited[node]) continue;
            visited[node] = true;
            if (!graph.containsKey(node)) continue;
            for (Map.Entry<Integer, Integer> entry : graph.get(node).entrySet()) {
                int next = entry.getKey();
                int weight = entry.getValue();
                int nd = d + weight + 1;
                if (nd < dist[next]) {
                    dist[next] = nd;
                    pq.offer(new int[]{next, nd});
                }
            }
        }
        
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (dist[i] <= maxMoves) count++;
        }
        
        // for each edge, count how many subdivided nodes are reachable from each end
        for (int[] e : edges) {
            int u = e[0], v = e[1], cnt = e[2];
            int usedFromU = dist[u] <= maxMoves ? Math.min(cnt, maxMoves - dist[u]) : 0;
            int usedFromV = dist[v] <= maxMoves ? Math.min(cnt, maxMoves - dist[v]) : 0;
            count += Math.min(cnt, usedFromU + usedFromV);
        }
        
        return count;
    }
}
