/*
 * Find the Distance Value Between Two Arrays (Easy)
 * https://leetcode.com/problems/find-the-distance-value-between-two-arrays/
 *
 * For each element in arr1, we check whether every element in arr2 differs from it by more than d; if so, it contributes to the distance value. This is a direct brute-force approach with an early break once a violating pair is found. Time complexity is O(n*m) where n and m are the lengths of arr1 and arr2, and space complexity is O(1) aside from input storage. Given the small constraint bounds, this simple nested loop is efficient enough without needing sorting or binary search optimizations.
 */

class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int count = 0;
        for (int a : arr1) {
            boolean valid = true;
            for (int b : arr2) {
                if (Math.abs(a - b) <= d) {
                    valid = false;
                    break;
                }
            }
            if (valid) count++;
        }
        return count;
    }
}
