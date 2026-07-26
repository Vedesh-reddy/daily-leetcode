/*
 * Mark Elements on Array by Performing Queries (Medium)
 * https://leetcode.com/problems/mark-elements-on-array-by-performing-queries/
 *
 * The task is to simulate a marking process on an array where each query marks a specific index (if unmarked) then marks the k smallest unmarked values (ties broken by index), tracking the sum of unmarked elements after each query. The approach pre-sorts indices by value (then index) once upfront, then uses a moving pointer through this sorted order to greedily mark the next smallest unmarked elements across queries without re-sorting each time. A running total tracks the sum of unmarked elements, decremented whenever an element becomes marked. Each index is only marked once, so the pointer advances at most n times total across all queries. Time complexity is O(n log n + n + m) for the initial sort plus the linear scan and query processing; space complexity is O(n) for the sorted index array and marked boolean array.
 */

class Solution {
    public long[] unmarkedSumArray(int[] nums, int[][] queries) {
        int n = nums.length;
        long total = 0;
        for (int x : nums) total += x;
        
        boolean[] marked = new boolean[n];
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) idx[i] = i;
        Arrays.sort(idx, (a, b) -> nums[a] != nums[b] ? nums[a] - nums[b] : a - b);
        
        int ptr = 0; // pointer into sorted idx array
        long[] ans = new long[queries.length];
        
        for (int q = 0; q < queries.length; q++) {
            int index = queries[q][0];
            int k = queries[q][1];
            
            if (!marked[index]) {
                marked[index] = true;
                total -= nums[index];
            }
            
            while (k > 0 && ptr < n) {
                int i = idx[ptr];
                if (!marked[i]) {
                    marked[i] = true;
                    total -= nums[i];
                    k--;
                }
                ptr++;
            }
            
            ans[q] = total;
        }
        
        return ans;
    }
}
