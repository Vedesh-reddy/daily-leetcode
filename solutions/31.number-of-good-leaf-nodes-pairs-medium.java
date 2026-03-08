/*
 * Number of Good Leaf Nodes Pairs (Medium)
 * https://leetcode.com/problems/number-of-good-leaf-nodes-pairs/
 *
 * Leaves need shortest path (measured through their lowest common ancestor) to be within `distance`. I do a post-order DFS where each node returns the list of distances from itself down to every leaf in its subtree. At each internal node, I count all cross pairs between left-subtree leaf distances and right-subtree leaf distances that sum to ≤ distance (since path through this node = left distance + right distance), then bump every returned distance by 1 for the parent call, pruning any that already exceed `distance` to keep the lists small. Time complexity is O(N * L) worst case where L is leaf count per subtree (bounded by distance ≤ 10 in practice), space is O(N) for recursion plus leaf lists.
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
    int distance;

    public int countPairs(TreeNode root, int distance) {
        this.distance = distance;
        dfs(root);
        return count;
    }

    // returns list of distances from this node to leaves in its subtree
    private int[] dfs(TreeNode node) {
        if (node == null) return new int[0];
        if (node.left == null && node.right == null) return new int[]{1};

        int[] left = dfs(node.left);
        int[] right = dfs(node.right);

        // combine pairs across left and right leaves
        for (int l : left) {
            for (int r : right) {
                if (l + r <= distance) count++;
            }
        }

        int[] combined = new int[left.length + right.length];
        int idx = 0;
        for (int l : left) if (l + 1 <= distance) combined[idx++] = l + 1;
        for (int r : right) if (r + 1 <= distance) combined[idx++] = r + 1;

        return java.util.Arrays.copyOf(combined, idx);
    }
}
