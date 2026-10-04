/*
 * Kth Largest Element in an Array (Medium)
 * https://leetcode.com/problems/kth-largest-element-in-an-array/
 *
 * Keep a min-heap of size k; after processing every number its top is the kth largest. Time O(n log k), space O(k).
 */

import java.util.PriorityQueue;

class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int num : nums) {
            heap.offer(num);
            if (heap.size() > k) heap.poll();
        }
        return heap.peek();
    }
}
