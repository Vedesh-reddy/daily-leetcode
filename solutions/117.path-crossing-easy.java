/*
 * Path Crossing (Easy)
 * https://leetcode.com/problems/path-crossing/
 *
 * We simulate the walk starting at the origin, tracking the current (x, y) coordinate after each move. To detect crossing, we encode each visited coordinate into a single long (packing x into the high bits and y into the low bits) and store these in a HashSet. If adding a new coordinate fails because it's already in the set, the path has crossed itself, so we return true immediately. If we finish the whole path without a collision, we return false. Time complexity is O(n) where n is the length of path, and space complexity is O(n) for storing visited points.
 */

class Solution {
    public boolean isPathCrossing(String path) {
        Set<Long> visited = new HashSet<>();
        int x = 0, y = 0;
        visited.add(0L);
        for (char c : path.toCharArray()) {
            switch (c) {
                case 'N': y++; break;
                case 'S': y--; break;
                case 'E': x++; break;
                case 'W': x--; break;
            }
            long key = ((long) x << 32) | (y & 0xffffffffL);
            if (!visited.add(key)) return true;
        }
        return false;
    }
}
