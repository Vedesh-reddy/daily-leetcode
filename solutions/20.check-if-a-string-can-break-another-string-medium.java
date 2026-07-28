/*
 * Check If a String Can Break Another String (Medium)
 * https://leetcode.com/problems/check-if-a-string-can-break-another-string/
 *
 * Two strings s1 and s2 can be rearranged (independently) so that one dominates the other character-by-character. Sorting both strings makes the smallest letters align with the smallest letters, which is the arrangement that maximizes the chance of domination in either direction. After sorting, just do a single pass checking if a[i] >= b[i] for all i (a breaks b) or b[i] >= a[i] for all i (b breaks a). If either holds, return true. Time complexity is O(n log n) due to sorting, space is O(n) for the char arrays (or O(1) extra if using counting sort with a 26-length array, which would improve to O(n) overall).
 */

class Solution {
    public boolean checkIfCanBreak(String s1, String s2) {
        char[] a = s1.toCharArray();
        char[] b = s2.toCharArray();
        Arrays.sort(a);
        Arrays.sort(b);
        
        boolean aBreaksB = true, bBreaksA = true;
        for (int i = 0; i < a.length; i++) {
            if (a[i] < b[i]) aBreaksB = false;
            if (b[i] < a[i]) bBreaksA = false;
        }
        return aBreaksB || bBreaksA;
    }
}
