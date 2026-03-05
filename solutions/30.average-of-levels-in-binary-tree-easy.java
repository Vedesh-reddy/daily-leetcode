/*
 * Average of Levels in Binary Tree (Easy)
 * https://leetcode.com/problems/average-of-levels-in-binary-tree/
 *
 * Need the average node value at each depth of the tree. Did a standard BFS level-order traversal: process one full level at a time by tracking the queue size before dequeuing, summing values and pushing children, then dividing the sum by the level's node count. Used a long accumulator for sum since up to 10^4 nodes each near INT_MAX could overflow an int. Time complexity O(n) since every node is visited once, space O(n) for the queue and result list in the worst case (a very wide tree).
 */

import java.util.*;

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
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            long sum = 0; // avoid overflow across many nodes
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            result.add((double) sum / size);
        }

        return result;
    }
}
