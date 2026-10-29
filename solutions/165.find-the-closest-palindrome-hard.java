/*
 * Find the Closest Palindrome (Hard)
 * https://leetcode.com/problems/find-the-closest-palindrome/
 *
 * The closest palindrome must share roughly the same digit-length prefix as n, so I take the left half (including middle for odd length) and try that prefix unchanged, incremented, and decremented, mirroring each to build full candidate palindromes. I also handle the boundary cases where the answer has one fewer digit (all 9s) or one more digit (100...001), since these arise when n is like "1000" or "9999". Single-digit n is handled separately since the answer is just n-1. All candidates are collected in a set (to dedupe), n itself is excluded, and then I pick the one with minimum absolute difference, breaking ties by choosing the smaller value. Time complexity is O(len) for string operations with O(1) candidates, so overall O(len) time and space.
 */

class Solution {
    public String nearestPalindromic(String n) {
        int len = n.length();
        long num = Long.parseLong(n);
        
        if (len == 1) {
            return String.valueOf(num - 1);
        }
        
        java.util.Set<Long> candidates = new java.util.HashSet<>();
        
        // edge cases: 999...9 and 100...001
        candidates.add((long) Math.pow(10, len - 1) - 1);
        candidates.add((long) Math.pow(10, len) + 1);
        
        long prefix = Long.parseLong(n.substring(0, (len + 1) / 2));
        
        for (long p = prefix - 1; p <= prefix + 1; p++) {
            String ps = String.valueOf(p);
            String candidate;
            if (len % 2 == 0) {
                candidate = ps + new StringBuilder(ps).reverse().toString();
            } else {
                candidate = ps + new StringBuilder(ps.substring(0, ps.length() - 1)).reverse().toString();
            }
            candidates.add(Long.parseLong(candidate));
        }
        
        candidates.remove(num);
        
        long best = -1;
        for (long c : candidates) {
            if (c < 0) continue;
            if (best == -1) {
                best = c;
            } else {
                long diff1 = Math.abs(c - num);
                long diff2 = Math.abs(best - num);
                if (diff1 < diff2 || (diff1 == diff2 && c < best)) {
                    best = c;
                }
            }
        }
        
        return String.valueOf(best);
    }
}
