/*
 * Flatten Binary Tree to Linked List (Medium)
 * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
 *
 * The idea is to build the preorder linked list in place without extra recursion stack or auxiliary storage. For each node, if it has a left child, we find the rightmost node of that left subtree (which is where the left subtree's traversal ends), attach the current node's right subtree there, then move the whole left subtree to become the right subtree and null out left. This mirrors Morris traversal's threading trick. We then advance to the new right child and repeat. Time complexity is O(n) since each node's right pointer is traversed a bounded number of times (amortized linear due to the way rightmost search partitions the tree), and space complexity is O(1) extra space, satisfying the follow-up constraint.
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public void flatten(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left != null) {
                // find rightmost node of left subtree, attach current right there
                TreeNode rightmost = curr.left;
                while (rightmost.right != null) {
                    rightmost = rightmost.right;
                }
                rightmost.right = curr.right;
                curr.right = curr.left;
                curr.left = null;
            }
            curr = curr.right;
        }
    }
}
