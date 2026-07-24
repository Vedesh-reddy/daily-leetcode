/*
 * Binary Search Tree to Greater Sum Tree (Medium)
 * https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/
 *
 * This is the classic BST-to-greater-sum conversion: each node's new value equals its original value plus the sum of all values greater than it. Since a reverse in-order traversal (right, node, left) visits nodes in descending order, I keep a running total as I go and assign it to each node as I visit it, then continue into the left subtree. This works because at the time we visit a node, the running sum already contains every value strictly greater than it. Time complexity is O(n) since each node is visited once, and space is O(h) for the recursion stack where h is the tree height (O(n) worst case for a skewed tree).
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

    public TreeNode bstToGst(TreeNode root) {
        traverse(root);
        return root;
    }

    private void traverse(TreeNode node) {
        if (node == null) return;
        // visit right first since we want descending order
        traverse(node.right);
        sum += node.val;
        node.val = sum;
        traverse(node.left);
    }
}
