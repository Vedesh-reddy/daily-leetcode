/*
 * Sum of Sortable Integers (Hard)
 * https://leetcode.com/problems/sum-of-sortable-integers/
 *
 * For each divisor k of n, the rotated result of each block can only contain the same multiset of values as the original block, so the final sorted array must equal the globally sorted version of nums. This means a block is fixable exactly when the corresponding segment of the sorted array is a cyclic rotation of the original segment - checked efficiently by doubling the original segment and searching for the sorted segment as a substring via KMP. Summing k over all divisors where every block passes this rotation check gives the answer. Time complexity is O(n·d(n)) where d(n) is the number of divisors of n (each divisor costs O(n) total work across its blocks), and space is O(n) for the sorted array and temporary buffers.
 */

class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int[] sorted = nums.clone();
        java.util.Arrays.sort(sorted);
        
        long sum = 0;
        for (int k = 1; k <= n; k++) {
            if (n % k != 0) continue;
            boolean ok = true;
            for (int s = 0; s < n && ok; s += k) {
                if (!isRotation(nums, sorted, s, k)) ok = false;
            }
            if (ok) sum += k;
        }
        return (int) sum;
    }
    
    // check if sorted[start..start+len-1] is a cyclic rotation of nums[start..start+len-1]
    private boolean isRotation(int[] a, int[] b, int start, int len) {
        int[] text = new int[2 * len];
        for (int i = 0; i < len; i++) {
            text[i] = a[start + i];
            text[i + len] = a[start + i];
        }
        int[] pattern = new int[len];
        for (int i = 0; i < len; i++) pattern[i] = b[start + i];
        
        return kmpSearch(text, pattern);
    }
    
    private boolean kmpSearch(int[] text, int[] pattern) {
        int m = pattern.length;
        int[] lps = new int[m];
        int len = 0;
        int i = 1;
        while (i < m) {
            if (pattern[i] == pattern[len]) {
                len++;
                lps[i] = len;
                i++;
            } else if (len != 0) {
                len = lps[len - 1];
            } else {
                lps[i] = 0;
                i++;
            }
        }
        
        int t = 0, p = 0;
        while (t < text.length) {
            if (text[t] == pattern[p]) {
                t++;
                p++;
                if (p == m) return true;
            } else if (p != 0) {
                p = lps[p - 1];
            } else {
                t++;
            }
        }
        return false;
    }
}
