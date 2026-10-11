/*
 * Maximum Population Year (Easy)
 * https://leetcode.com/problems/maximum-population-year/
 *
 * Each person contributes +1 at their birth year and -1 at their death year, so I use a difference array over the fixed year range (1950-2050) to track population changes. Sweeping through the years while accumulating the running sum gives the population at each year; I track the maximum and its earliest occurrence. Time complexity is O(n + Y) where n is the number of logs and Y is the year range (constant ~100), and space is O(Y) for the delta array.
 */

class Solution {
    public int maximumPopulation(int[][] logs) {
        int[] delta = new int[2051];
        for (int[] log : logs) {
            delta[log[0]]++;
            delta[log[1]]--;
        }
        int maxPop = 0, year = 1950, cur = 0;
        for (int y = 1950; y <= 2050; y++) {
            cur += delta[y];
            if (cur > maxPop) {
                maxPop = cur;
                year = y;
            }
        }
        return year;
    }
}
