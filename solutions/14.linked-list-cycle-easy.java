/*
 * Linked List Cycle (Easy)
 * https://leetcode.com/problems/linked-list-cycle/
 *
 * The task is to detect whether a singly linked list loops back on itself at any point. I used Floyd's cycle detection (tortoise and hare): a slow pointer advances one node at a time while a fast pointer advances two nodes at a time. If there's a cycle, the fast pointer will eventually lap the slow pointer and they'll meet; if there's no cycle, fast will hit null first. This avoids needing extra storage like a visited-set to track seen nodes. Time complexity is O(n) since the fast pointer traverses at most twice the list length before either exiting or meeting slow. Space complexity is O(1), satisfying the follow-up constraint.
 */

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }
}
