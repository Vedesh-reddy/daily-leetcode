/*
 * Convert BST to Greater Tree (Medium)
 * https://leetcode.com/problems/convert-bst-to-greater-tree/
 *
 * Every node's new value is its old value plus the sum of all greater keys, which in a BST corresponds exactly to visiting nodes in descending order (right, node, left) and keeping a running total. I traverse right subtree first, add the current node's value to a running sum, assign that sum back to the node, then recurse left. This guarantees that by the time we touch a node, every larger value has already been folded into the running sum. Time complexity is O(n) since each node is visited once, and space is O(h) for recursion stack where h is the tree height (worst case O(n) for a skewed tree).
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
    private int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        traverse(root);
        return root;
    }

    private void traverse(TreeNode node) {
        if (node == null) return;
        traverse(node.right);
        sum += node.val;
        node.val = sum;
        traverse(node.left);
    }
}
