/*
 * Maximum Width of Binary Tree (Medium)
 * https://leetcode.com/problems/maximum-width-of-binary-tree/
 *
 * Each node gets a positional index as if the tree were a complete binary tree (root=0, left child=2*i, right child=2*i+1). For each level, the width is the difference between the last and first index plus one. To prevent overflow on deep/skewed trees, I re-base the index relative to the first node's index at that level before computing children's indices. BFS with paired queues (nodes and indices) tracks level boundaries naturally. Time complexity is O(n) and space complexity is O(n) for the queues.
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
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        int maxWidth = 0;
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Long> idxQueue = new LinkedList<>();
        nodeQueue.offer(root);
        idxQueue.offer(0L);
        while (!nodeQueue.isEmpty()) {
            int size = nodeQueue.size();
            long first = 0, last = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = nodeQueue.poll();
                long idx = idxQueue.poll();
                if (i == 0) first = idx;
                if (i == size - 1) last = idx;
                // shift to avoid overflow for deep trees
                idx -= first;
                if (node.left != null) {
                    nodeQueue.offer(node.left);
                    idxQueue.offer(idx * 2);
                }
                if (node.right != null) {
                    nodeQueue.offer(node.right);
                    idxQueue.offer(idx * 2 + 1);
                }
            }
            maxWidth = Math.max(maxWidth, (int)(last - first + 1));
        }
        return maxWidth;
    }
}
