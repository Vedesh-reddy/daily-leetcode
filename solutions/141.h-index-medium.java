/*
 * H-Index (Medium)
 * https://leetcode.com/problems/h-index/
 *
 * The task is to find the largest h such that at least h papers have h or more citations each. I used a bucket/counting sort trick: since h-index can never exceed n (the number of papers), I clamp each citation count to n and tally counts into buckets indexed 0..n. Then I scan from the top bucket down, accumulating a running total of papers with citations >= current index i; the first i where the running total is >= i is the answer. This avoids a full sort and runs in O(n) time and O(n) space, compared to the O(n log n) sort-based approach.
 */

class Solution {
    public int hIndex(int[] citations) {
        int n = citations.length;
        int[] buckets = new int[n + 1];
        // cap citations at n since h-index can't exceed number of papers
        for (int c : citations) {
            buckets[Math.min(c, n)]++;
        }
        int total = 0;
        for (int i = n; i >= 0; i--) {
            total += buckets[i];
            if (total >= i) return i;
        }
        return 0;
    }
}
