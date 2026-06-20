/*
 * Minimum Energy to Maintain Brightness (Medium)
 * https://leetcode.com/problems/minimum-energy-to-maintain-brightness/
 *
 * The key insight is that the required brightness level (not the specific time interval) determines how many bulbs must be simultaneously on, and one bulb can illuminate at most 3 positions (itself plus two neighbors), so the minimum bulb count is ceil(min(n, brightness) / 3). Since bulbs can be freely turned on/off each time unit at no extra setup cost, the total energy is just that fixed bulb count multiplied by the number of distinct time units that need to satisfy the brightness requirement. Those time units are simply the union of all given intervals, so I merge overlapping/adjacent intervals and sum their lengths. Sorting intervals takes O(m log m) and merging is O(m), giving overall O(m log m) time and O(m) space where m is the number of intervals.
 */

class Solution {
    public long minEnergy(int n, int brightness, int[][] intervals) {
        // minimum bulbs needed: each bulb can light up to 3 positions (itself + 2 neighbors)
        int need = Math.min(n, brightness);
        int k = (need + 2) / 3;

        // merge intervals to get total distinct covered time units
        int[][] sorted = intervals.clone();
        java.util.Arrays.sort(sorted, (a, b) -> a[0] - b[0]);

        long totalTime = 0;
        int curStart = -1, curEnd = -2;
        for (int[] iv : sorted) {
            if (iv[0] > curEnd + 1) {
                if (curEnd >= curStart) {
                    totalTime += (curEnd - curStart + 1);
                }
                curStart = iv[0];
                curEnd = iv[1];
            } else {
                curEnd = Math.max(curEnd, iv[1]);
            }
        }
        if (curEnd >= curStart) {
            totalTime += (curEnd - curStart + 1);
        }

        return (long) k * totalTime;
    }
}
