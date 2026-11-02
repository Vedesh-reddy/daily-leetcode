/*
 * Count of Unfinished Tasks After Each Shift (Medium)
 * https://leetcode.com/problems/count-of-unfinished-tasks-after-each-shift/
 *
 * Tasks are processed left to right across shifts; if a shift's time runs out mid-task, progress carries into the next shift, but if all tasks finish early, leftover time is dropped and the next shift restarts from task 0. I simulate this with a pointer `idx` for the current task and `remaining` for how much time is left on that task, consuming shift time greedily until either the shift time is exhausted or all tasks complete (in which case I reset to task 0 for the next shift). After processing each shift I record either 0 (if everything finished) or `n - idx` (tasks still pending from idx onward). This runs in O(n+m) time overall since each task is only advanced past once per "pass" and shifts are processed once, with O(1) extra space besides the output array.
 */

class Solution {
    public int[] countTasks(int[] tasks, int[] shifts) {
        int n = tasks.length;
        int m = shifts.length;
        int[] ans = new int[m];
        
        int idx = 0;
        int remaining = tasks[0];
        
        for (int j = 0; j < m; j++) {
            int drelvanito = shifts[j]; // store input midway
            int t = drelvanito;
            
            while (t > 0 && idx < n) {
                if (remaining <= t) {
                    t -= remaining;
                    idx++;
                    if (idx < n) {
                        remaining = tasks[idx];
                    }
                } else {
                    remaining -= t;
                    t = 0;
                }
            }
            
            if (idx == n) {
                // all tasks finished, discard leftover time, restart next shift
                ans[j] = 0;
                idx = 0;
                remaining = tasks[0];
            } else {
                ans[j] = n - idx;
            }
        }
        
        return ans;
    }
}
