/*
 * Palindrome Number (Easy)
 * https://leetcode.com/problems/palindrome-number/
 *
 * The task is to check if an integer reads the same forwards and backwards without converting it to a string. The approach reverses only half of the number by repeatedly peeling off digits from x and building reversed until x <= reversed, which naturally stops around the midpoint. Negative numbers are immediately rejected, and numbers ending in 0 (except 0 itself) can't be palindromes since palindromes can't have leading zeros. At the end, compare x to reversed for even-length numbers, or x to reversed/10 for odd-length numbers to drop the middle digit. This runs in O(log x) time since we process roughly half the digits, and O(1) space.
 */

class Solution {
    public boolean isPalindrome(int x) {
        // negatives and trailing-zero (non-zero) numbers can't be palindromes
        if (x < 0 || (x % 10 == 0 && x != 0)) return false;
        
        int reversed = 0;
        while (x > reversed) {
            reversed = reversed * 10 + x % 10;
            x /= 10;
        }
        
        // even length: x == reversed, odd length: middle digit removed via /10
        return x == reversed || x == reversed / 10;
    }
}
