/*
 * Construct the Rectangle (Easy)
 * https://leetcode.com/problems/construct-the-rectangle/
 *
 * We need two factors L and W of area with L >= W and L-W minimized. Starting the search for W at sqrt(area) and decreasing ensures we find the factor pair closest together, since the first divisor found from sqrt downward gives the smallest possible gap. Time complexity is O(sqrt(area)) in the worst case (e.g., when area is prime), and space is O(1).
 */

class Solution {
    public int[] constructRectangle(int area) {
        int w = (int) Math.sqrt(area);
        while (area % w != 0) {
            w--;
        }
        return new int[]{area / w, w};
    }
}
