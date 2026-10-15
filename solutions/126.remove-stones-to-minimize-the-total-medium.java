/*
 * Remove Stones to Minimize the Total (Medium)
 * https://leetcode.com/problems/remove-stones-to-minimize-the-total/
 *
 * Each operation should target the pile with the most stones since removing half of a larger pile yields a bigger reduction than doing the same to a smaller one. Use a max-heap to always extract the current largest pile, halve it (floor division), and push it back, tracking the running sum of stones removed. After k operations, return the sum initialized with total stones minus all reductions. Time complexity is O(n log n + k log n) for heap operations, and space complexity is O(n) for the heap.
 */

class Solution {
    public int minStoneSum(int[] piles, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int sum = 0;
        for (int p : piles) {
            pq.offer(p);
            sum += p;
        }
        for (int i = 0; i < k; i++) {
            int top = pq.poll();
            int reduce = top / 2;
            sum -= reduce;
            pq.offer(top - reduce);
        }
        return sum;
    }
}
