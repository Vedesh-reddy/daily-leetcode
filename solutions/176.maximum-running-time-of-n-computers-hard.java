/*
 * Maximum Running Time of N Computers (Hard)
 * https://leetcode.com/problems/maximum-running-time-of-n-computers/
 *
 * We need the max time T such that we can keep n computers running simultaneously using the batteries, swapping as needed. The key insight is that any battery larger than T is effectively capped at contributing T minutes (the extra capacity is wasted since only n computers run at once), while smaller batteries contribute their full capacity. This gives a monotonic feasibility check: sum of min(battery, T) across all batteries must be at least n*T for T to be achievable. Binary search over T (from 0 to total/n) using this check converges to the answer. Time complexity is O(m log(sum/n)) where m is the number of batteries, and space is O(1).
 */

class Solution {
    public long maxRunTime(int n, int[] batteries) {
        long lo = 0, hi = 0;
        for (int b : batteries) hi += b;
        hi /= n;

        while (lo < hi) {
            long mid = lo + (hi - lo + 1) / 2;
            if (canRun(n, batteries, mid)) {
                lo = mid;
            } else {
                hi = mid - 1;
            }
        }
        return lo;
    }

    private boolean canRun(int n, int[] batteries, long time) {
        long total = 0;
        for (int b : batteries) {
            // batteries bigger than time can only contribute "time" worth of usage
            total += Math.min((long) b, time);
        }
        return total >= (long) n * time;
    }
}
