/*
 * Flipping an Image (Easy)
 * https://leetcode.com/problems/flipping-an-image/
 *
 * For each row we need to reverse it and flip all bits. Instead of doing two passes, use a two-pointer approach: swap elements from opposite ends of the row while inverting them (XOR with 1) as they're placed. When l == r (odd length middle element), it still gets inverted correctly since the swap logic handles that case with l <= r. This does everything in one pass per row, in place, with O(n^2) time and O(1) extra space for an n x n image.
 */

class Solution {
    public int[][] flipAndInvertImage(int[][] image) {
        int n = image.length;
        for (int[] row : image) {
            int l = 0, r = n - 1;
            while (l <= r) {
                int tmp = row[l] ^ 1;
                row[l] = row[r] ^ 1;
                row[r] = tmp;
                l++;
                r--;
            }
        }
        return image;
    }
}
