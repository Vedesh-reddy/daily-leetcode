/*
 * Swap Nodes in Pairs (Medium)
 * https://leetcode.com/problems/swap-nodes-in-pairs/
 *
 * We need to swap each pair of adjacent nodes by relinking pointers, not by swapping values. Using a dummy node before the head simplifies edge cases like swapping the first pair. For each iteration, grab the first and second node of the pair, rewire second to point to first, first to point past the pair, and prev to point to second (the new head of the pair), then advance prev to first (now the tail of the swapped pair). Loop until fewer than two nodes remain. Time complexity is O(n) since each node is visited once, and space complexity is O(1) since only pointers are rearranged.
 */

class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            ListNode first = prev.next;
            ListNode second = first.next;

            first.next = second.next;
            second.next = first;
            prev.next = second;

            prev = first;
        }

        return dummy.next;
    }
}
