/*
 * Invert Binary Tree (Easy)
 * https://leetcode.com/problems/invert-binary-tree/
 *
 * Recursively swap each node's left and right children. Every node is visited once. Time O(n), space O(h) for the recursion stack.
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left = left; this.right = right; }
}

class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode left = invertTree(root.left);
        root.left = invertTree(root.right);
        root.right = left;
        return root;
    }
}
