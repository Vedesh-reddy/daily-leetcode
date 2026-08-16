/*
 * Maximum Points You Can Obtain from Cards (Medium)
 * https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/
 *
 * Taking k cards from either end is equivalent to leaving behind a contiguous subarray of size n-k in the middle, so maximizing the picked score is the same as minimizing the sum of that leftover window. I compute the total sum, then slide a window of size n-k across the array to find its minimum sum, and subtract that from the total. This avoids simulating all the front/back combinations directly. Time complexity is O(n) and space is O(1).
 */

class Solution {
    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int total = 0;
        for (int c : cardPoints) total += c;
        
        int windowSize = n - k;
        if (windowSize == 0) return total;
        
        int windowSum = 0;
        for (int i = 0; i < windowSize; i++) windowSum += cardPoints[i];
        
        int minWindow = windowSum;
        for (int i = windowSize; i < n; i++) {
            windowSum += cardPoints[i] - cardPoints[i - windowSize];
            minWindow = Math.min(minWindow, windowSum);
        }
        
        return total - minWindow;
    }
}
