/*
 * Sort List (Medium)
 * https://leetcode.com/problems/sort-list/
 *
 * This is the classic merge sort on a linked list. I split the list into two halves using slow/fast pointers (slow ends up at the node before the midpoint), recursively sort each half, then merge the two sorted halves together using a dummy head to simplify pointer handling. Base case stops recursion when the list has 0 or 1 nodes. Time complexity is O(n log n) since each level of recursion does O(n) merge work across log n levels. Space complexity is O(log n) due to recursion stack (the merge itself is done in place without extra node allocation, satisfying the O(1) auxiliary memory for merging, though the recursive call stack itself isn't strictly constant space).
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        // find middle with slow/fast pointers
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow.next;
        slow.next = null;

        ListNode left = sortList(head);
        ListNode right = sortList(mid);

        return merge(left, right);
    }

    private ListNode merge(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (a != null && b != null) {
            if (a.val <= b.val) {
                tail.next = a;
                a = a.next;
            } else {
                tail.next = b;
                b = b.next;
            }
            tail = tail.next;
        }
        tail.next = (a != null) ? a : b;
        return dummy.next;
    }
}
