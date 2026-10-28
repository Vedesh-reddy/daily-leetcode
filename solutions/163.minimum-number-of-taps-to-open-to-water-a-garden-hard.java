/*
 * Minimum Number of Taps to Open to Water a Garden (Hard)
 * https://leetcode.com/problems/minimum-number-of-taps-to-open-to-water-a-garden/
 *
 * This is essentially the Jump Game II problem in disguise: convert each tap's range into the farthest point reachable starting from its leftmost coverage point, then greedily expand coverage like minimum jumps to reach the end. I build an array maxReach where maxReach[left] stores the furthest right boundary any tap starting watering from "left" can reach. Then I sweep through positions 0..n using the classic greedy jump-game technique, tracking the current covered boundary and the next reachable boundary, incrementing the tap count whenever I must extend past the current boundary. If at any point the next reachable boundary can't keep up with the current index, the garden can't be fully watered and I return -1. Time complexity is O(n) and space is O(n) for the maxReach array.
 */

class Solution {
    public int minTaps(int n, int[] ranges) {
        int[] maxReach = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            int left = Math.max(0, i - ranges[i]);
            int right = Math.min(n, i + ranges[i]);
            maxReach[left] = Math.max(maxReach[left], right);
        }
        
        int taps = 0, curEnd = 0, nextEnd = 0;
        for (int i = 0; i <= n; i++) {
            if (i > nextEnd) return -1; // gap found
            if (i > curEnd) {
                taps++;
                curEnd = nextEnd;
            }
            nextEnd = Math.max(nextEnd, maxReach[i]);
        }
        
        return taps;
    }
}
