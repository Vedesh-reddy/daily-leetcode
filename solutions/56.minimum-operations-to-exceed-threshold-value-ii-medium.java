/*
 * Minimum Operations to Exceed Threshold Value II (Medium)
 * https://leetcode.com/problems/minimum-operations-to-exceed-threshold-value-ii/
 *
 * This asks for the minimum number of merge operations, each combining the two smallest values with the formula min*2+max, until every element is at least k. A min-heap naturally gives fast access to the two smallest elements at each step, so I repeatedly pop the two smallest, compute the combined value, push it back, and count operations until the heap's minimum reaches k. Using long avoids overflow risk from repeated combination even though constraints are small. Time complexity is O(n log n) since each of the up to n-1 operations does O(log n) heap work, and space is O(n) for the heap.
 */

class Solution {
    public int minOperations(int[] nums, int k) {
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (int num : nums) pq.add((long) num);
        
        int ops = 0;
        while (pq.peek() < k) {
            long x = pq.poll();
            long y = pq.poll();
            long combined = Math.min(x, y) * 2 + Math.max(x, y);
            pq.add(combined);
            ops++;
        }
        return ops;
    }
}
