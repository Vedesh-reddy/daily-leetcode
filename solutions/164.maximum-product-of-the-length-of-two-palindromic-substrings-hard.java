/*
 * Maximum Product of the Length of Two Palindromic Substrings (Hard)
 * https://leetcode.com/problems/maximum-product-of-the-length-of-two-palindromic-substrings/
 *
 * The task reduces to: for every split point, find the longest odd-length palindrome entirely to the left and the longest entirely to the right, then maximize the product of those two lengths. I used Manacher's algorithm restricted to odd-center palindromes to get, for every center, the maximal radius in O(n). From that I derive, for every index, the best palindrome length that *ends* exactly there and the best that *starts* exactly there, then do a prefix max (`left[i]`) and suffix max (`right[i]`) sweep so `left[i]` is the best odd palindrome fully contained in `[0,i]` and `right[i]` is the best fully contained in `[i,n-1]`. Finally I scan all split points `i` and take `max(left[i]*right[i+1])`. Overall time is O(n) for Manacher's plus O(n) for the sweeps, so O(n) total, with O(n) extra space for the arrays.
 */

class Solution {
    public long maxProduct(String s) {
        int n = s.length();
        int[] d1 = new int[n];
        int l = 0, r = -1;
        for (int i = 0; i < n; i++) {
            int k = (i > r) ? 1 : Math.min(d1[l + r - i], r - i + 1);
            while (i - k >= 0 && i + k < n && s.charAt(i - k) == s.charAt(i + k)) k++;
            d1[i] = k;
            k--;
            if (i + k > r) {
                l = i - k;
                r = i + k;
            }
        }

        int[] left = new int[n];
        int[] right = new int[n];

        for (int i = 0; i < n; i++) {
            int k = d1[i] - 1;
            int start = i - k;
            int end = i + k;
            int len = 2 * k + 1;
            if (len > left[end]) left[end] = len;
            if (len > right[start]) right[start] = len;
        }

        // propagate shortest palindrome fallback of length 1 implicitly covered since k=0 case included
        for (int i = 1; i < n; i++) {
            left[i] = Math.max(left[i], left[i - 1]);
        }
        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.max(right[i], right[i + 1]);
        }

        long best = 0;
        for (int i = 0; i < n - 1; i++) {
            long prod = (long) left[i] * right[i + 1];
            if (prod > best) best = prod;
        }
        return best;
    }
}
