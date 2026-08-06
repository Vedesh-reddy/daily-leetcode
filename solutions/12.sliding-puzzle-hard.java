/*
 * Sliding Puzzle (Hard)
 * https://leetcode.com/problems/sliding-puzzle/
 *
 * Flatten the 2x3 board into a length-6 string so each state is easy to hash and compare, then treat this as a shortest-path search over the graph of reachable states where an edge connects two states differing by one legal swap of '0' with an adjacent cell. Precompute the adjacency (which flattened indices are neighbors in the 2x3 grid) once, then run BFS from the start state, level by level, until the target "123450" is found, returning the level count as the answer. If BFS exhausts all reachable states without hitting the target, return -1 (this happens for odd permutation parity relative to target). Time and space complexity are O(6!) in the worst case since there are only 720 possible permutations, so this runs essentially in constant time.
 */

class Solution {
    public int slidingPuzzle(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int[] row : board)
            for (int n : row)
                sb.append(n);
        String start = sb.toString();
        String target = "123450";

        // adjacency list for each index in the flattened 2x3 board
        int[][] neighbors = {
            {1, 3},
            {0, 2, 4},
            {1, 5},
            {0, 4},
            {1, 3, 5},
            {2, 4}
        };

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        visited.add(start);
        int moves = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                if (cur.equals(target)) return moves;

                int zero = cur.indexOf('0');
                char[] arr = cur.toCharArray();
                for (int next : neighbors[zero]) {
                    char[] swapped = arr.clone();
                    swapped[zero] = swapped[next];
                    swapped[next] = '0';
                    String nextState = new String(swapped);
                    if (!visited.contains(nextState)) {
                        visited.add(nextState);
                        queue.offer(nextState);
                    }
                }
            }
            moves++;
        }

        return -1;
    }
}
