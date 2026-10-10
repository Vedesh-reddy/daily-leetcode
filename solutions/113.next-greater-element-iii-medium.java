/*
 * Next Greater Element III (Medium)
 * https://leetcode.com/problems/next-greater-element-iii/
 *
 * This is the classic "next permutation" problem applied to the digits of n. Scan from the right to find the first digit that's smaller than the digit after it (the pivot), then find the smallest digit to its right that's still larger than the pivot and swap them, then reverse everything after the pivot's original position to get the smallest possible suffix. If no pivot is found, the digits are in descending order and n is already the largest permutation, so return -1. Used a try/catch around parseInt to handle the 32-bit overflow case cheaply. Time complexity is O(d) where d is the number of digits (at most 10), and space is O(d) for the char array.
 */

class Solution {
    public int nextGreaterElement(int n) {
        char[] digits = Integer.toString(n).toCharArray();
        int i = digits.length - 2;
        while (i >= 0 && digits[i] >= digits[i + 1]) {
            i--;
        }
        if (i < 0) return -1;
        
        int j = digits.length - 1;
        while (digits[j] <= digits[i]) {
            j--;
        }
        
        char temp = digits[i];
        digits[i] = digits[j];
        digits[j] = temp;
        
        // reverse suffix to get smallest arrangement
        int left = i + 1, right = digits.length - 1;
        while (left < right) {
            temp = digits[left];
            digits[left] = digits[right];
            digits[right] = temp;
            left++;
            right--;
        }
        
        try {
            return Integer.parseInt(new String(digits));
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
