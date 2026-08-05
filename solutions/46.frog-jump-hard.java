/*
 * Frog Jump (Hard)
 * https://leetcode.com/problems/frog-jump/
 *
 * The frog starts at stone 0 with a "last jump" of 0, and from each stone we branch out to k-1, k, k+1 sized jumps landing on any stone that exists at that position. I used a HashMap from stone position to a set of jump sizes that can reach that stone, building it up in order since stones are sorted ascending. For each stone, iterate its reachable jump sizes and propagate the three possible next jumps to whichever stones exist at those landing positions. The answer is whether the last stone ends up with any recorded jump sizes reaching it. Time complexity is O(n^2) since each stone can have up to n jump sizes and each triggers O(1) amortized work with hash lookups, space is O(n^2) worst case for storing jump sets.
 */

class Solution {
    public boolean canCross(int[] stones) {
        int n = stones.length;
        Map<Integer, Set<Integer>> jumps = new HashMap<>();
        for (int s : stones) jumps.put(s, new HashSet<>());
        jumps.get(0).add(0);

        for (int i = 0; i < n; i++) {
            int pos = stones[i];
            for (int k : jumps.get(pos)) {
                for (int step = k - 1; step <= k + 1; step++) {
                    if (step <= 0) continue;
                    int next = pos + step;
                    if (jumps.containsKey(next)) {
                        jumps.get(next).add(step);
                    }
                }
            }
        }

        return !jumps.get(stones[n - 1]).isEmpty();
    }
}
