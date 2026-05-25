/*
 * Split Two Strings to Make Palindrome (Medium)
 * https://leetcode.com/problems/split-two-strings-to-make-palindrome/
 *
 * The idea is to match characters from the outside in between a and b (or b and a) as long as they're equal - these matched pairs can always form a palindrome shell regardless of split point. Once a mismatch occurs at positions i,j, the remaining middle chunk must itself be a palindrome, taken either from a or from b, since one side's prefix/suffix must come entirely from one string for a valid split. We try both starting orientations (a's prefix + b's suffix, and b's prefix + a's suffix) since either could work. Each check runs in O(n) with two pointers plus an O(n) palindrome check on the leftover middle, giving overall O(n) time and O(1) extra space.
 */

class Solution {
    public boolean checkPalindromeFormation(String a, String b) {
        return check(a, b) || check(b, a);
    }
    
    private boolean check(String a, String b) {
        int i = 0, j = a.length() - 1;
        while (i < j && a.charAt(i) == b.charAt(j)) {
            i++;
            j--;
        }
        if (i >= j) return true;
        return isPalindrome(a, i, j) || isPalindrome(b, i, j);
    }
    
    private boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }
}
