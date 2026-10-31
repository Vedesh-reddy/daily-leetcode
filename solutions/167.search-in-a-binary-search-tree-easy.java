/*
 * Search in a Binary Search Tree (Easy)
 * https://leetcode.com/problems/search-in-a-binary-search-tree/
 *
 * We need to find the node with a given value in a BST and return the subtree rooted there, or null if it doesn't exist. Since it's a BST, at each node we can compare val to the current node's value: if equal we're done, if smaller go left, if larger go right, discarding the other half of the tree each time. This is done iteratively with a simple while loop until we either find the match or hit a null. Time complexity is O(h) where h is the height of the tree (O(log n) average, O(n) worst case for a skewed tree), and space complexity is O(1) since it's iterative.
 */

class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        while (root != null && root.val != val) {
            root = val < root.val ? root.left : root.right;
        }
        return root;
    }
}
