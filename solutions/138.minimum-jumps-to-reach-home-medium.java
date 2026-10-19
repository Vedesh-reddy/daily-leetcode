/*
 * Minimum Jumps to Reach Home (Medium)
 * https://leetcode.com/problems/minimum-jumps-to-reach-home/
 *
 * The bug starts at 0 and needs to reach x using forward jumps of size a and backward jumps of size b, never jumping backward twice consecutively or onto a forbidden/negative position. I used BFS where each state tracks both the current position and whether the last jump was backward (to enforce the no-double-backward rule), since the same position can be revisited validly depending on jump history. To bound the search space I cap positions at max(forbidden, x) + a + b, since jumping further forward than that can never help reach x or get back to it. Time and space complexity are O(limit) where limit is roughly bounded by the input values, since each (position, lastJumpFlag) state is visited at most once.
 */

class Solution {
    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        Set<Integer> blocked = new HashSet<>();
        int maxPos = x;
        for (int f : forbidden) {
            blocked.add(f);
            maxPos = Math.max(maxPos, f);
        }
        int limit = maxPos + a + b; // beyond this, no point jumping further forward
        
        boolean[][] visited = new boolean[limit + 1][2]; // [pos][lastWasBackward ? 1 : 0]
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 0}); // pos, steps, lastBackward
        visited[0][0] = true;
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int pos = cur[0], steps = cur[1], lastBack = cur[2];
            if (pos == x) return steps;
            
            int forward = pos + a;
            if (forward <= limit && !blocked.contains(forward) && !visited[forward][0]) {
                visited[forward][0] = true;
                queue.offer(new int[]{forward, steps + 1, 0});
            }
            
            if (lastBack == 0) {
                int backward = pos - b;
                if (backward >= 0 && !blocked.contains(backward) && !visited[backward][1]) {
                    visited[backward][1] = true;
                    queue.offer(new int[]{backward, steps + 1, 1});
                }
            }
        }
        
        return -1;
    }
}
