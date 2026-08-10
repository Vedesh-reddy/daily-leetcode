/*
 * Flatten a Multilevel Doubly Linked List (Medium)
 * https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/
 *
 * Walk the list node by node; whenever a node has a child, splice the child list in between the current node and its next node, then keep walking (this naturally dives into deeper children before returning to shallower levels since we just keep following `next`). Need to find the tail of the child sublist each time to reconnect it to the original next pointer, and remember to null out the child pointer per the problem's requirement. No recursion or extra stack needed since we just continue traversing forward through the newly spliced-in nodes. Time complexity O(n) since each node is visited a constant number of times (once as current, plus tail-finding walks which together only traverse each node once overall), space is O(1) extra (ignoring recursion, since this is iterative).
 */

/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/

class Solution {
    public Node flatten(Node head) {
        if (head == null) return null;

        Node curr = head;
        while (curr != null) {
            if (curr.child != null) {
                Node next = curr.next;
                Node child = curr.child;

                curr.child = null;
                curr.next = child;
                child.prev = curr;

                // find tail of child list to reconnect
                Node tail = child;
                while (tail.next != null) {
                    tail = tail.next;
                }

                tail.next = next;
                if (next != null) {
                    next.prev = tail;
                }
            }
            curr = curr.next;
        }

        return head;
    }
}
