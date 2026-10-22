/*
 * Second Minimum Node In a Binary Tree (Easy)
 * https://leetcode.com/problems/second-minimum-node-in-a-binary-tree/
 *
 * The tree's root always holds the global minimum, so the second minimum must be the smallest value strictly greater than root.val somewhere in the tree. I recurse down, and whenever a node's value differs from the known minimum, it's a valid candidate to return immediately (no need to look deeper since children would only be >= that value). Otherwise I recurse into both children and take the smaller of the two results, treating -1 as "no candidate found." This avoids collecting all values into a set and works in O(n) time and O(h) space for the recursion stack, where n is node count and h is tree height.
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
    public int findSecondMinimumValue(TreeNode root) {
        return helper(root, root.val);
    }

    private long helper(TreeNode node, int min) {
        if (node == null) return -1;
        // if value differs from root min, this is a candidate
        if (node.val != min) return node.val;

        long left = helper(node.left, min);
        long right = helper(node.right, min);

        if (left == -1) return right;
        if (right == -1) return left;
        return Math.min(left, right);
    }
}
