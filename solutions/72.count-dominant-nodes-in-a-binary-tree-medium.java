/*
 * Count Dominant Nodes in a Binary Tree (Medium)
 * https://leetcode.com/problems/count-dominant-nodes-in-a-binary-tree/
 *
 * The task is to find nodes whose value equals the maximum value found anywhere in their own subtree. I do a post-order DFS: each recursive call returns the maximum value in the subtree rooted at that node. For a given node, I compute the max of its left subtree, right subtree, and its own value; if the node's value matches that overall max, it's dominant and I increment a counter. Null children return Integer.MIN_VALUE so they never affect the max calculation. Time complexity is O(n) since each node is visited once, and space complexity is O(h) for the recursion stack, where h is the tree height (O(log n) for a complete binary tree).
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
    int count = 0;

    public int countDominantNodes(TreeNode root) {
        dfs(root);
        return count;
    }

    private int dfs(TreeNode node) {
        if (node == null) return Integer.MIN_VALUE;
        int leftMax = dfs(node.left);
        int rightMax = dfs(node.right);
        int subtreeMax = Math.max(node.val, Math.max(leftMax, rightMax));
        if (node.val == subtreeMax) count++;
        return subtreeMax;
    }
}
