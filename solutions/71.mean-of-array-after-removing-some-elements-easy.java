/*
 * Mean of Array After Removing Some Elements (Easy)
 * https://leetcode.com/problems/mean-of-array-after-removing-some-elements/
 *
 * Sort the array, then chop off the bottom 5% and top 5% of elements (n/20 from each end since n is a multiple of 20), and average what's left. Sorting makes it trivial to identify which elements fall into the smallest/largest 5% buckets. Time complexity is O(n log n) due to sorting, space is O(1) extra (ignoring sort's internal usage).
 */

class Solution {
    public double trimMean(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        int cut = n / 20;
        int sum = 0;
        for (int i = cut; i < n - cut; i++) {
            sum += arr[i];
        }
        return (double) sum / (n - 2 * cut);
    }
}
