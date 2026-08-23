/*
 * Palindrome Linked List (Easy)
 * https://leetcode.com/problems/palindrome-linked-list/
 *
 * Need to check if the linked list reads the same forwards and backwards. Since we can't easily go backwards in a singly linked list, the O(1) space trick is to find the middle using slow/fast pointers, reverse the second half in place, then walk both halves comparing values. Fast pointer moving 2 steps for every 1 of slow lands slow at the midpoint when fast hits the end. After comparison I don't bother restoring the list since that's not required by the problem. Time complexity is O(n), space is O(1) since we only reverse pointers rather than copying to an array.
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
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;

        // find middle
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // reverse second half
        ListNode prev = null, curr = slow;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        // compare first half and reversed second half
        ListNode p1 = head, p2 = prev;
        boolean result = true;
        while (p2 != null) {
            if (p1.val != p2.val) {
                result = false;
                break;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        return result;
    }
}
