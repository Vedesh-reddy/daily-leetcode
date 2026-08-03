/*
 * Maximum Difference by Remapping a Digit (Easy)
 * https://leetcode.com/problems/maximum-difference-by-remapping-a-digit/
 *
 * Bob picks one digit value and remaps every occurrence of it to another digit, and we want the max possible resulting number minus the min possible. For the maximum, the best move is to find the first digit in the string that isn't already '9' and turn all its copies into '9' (if all digits are already 9, num stays the same). For the minimum, the best move is to turn all copies of the leading digit into '0', since that shrinks the most significant digit as much as possible (leading zeros are allowed). Convert num to a string, do the two replacements, parse back to ints, and subtract. Time complexity is O(d) where d is the number of digits, space is O(d) for the string representations.
 */

class Solution {
    public int minMaxDifference(int num) {
        String s = Integer.toString(num);
        
        // max: find first digit that isn't 9, replace all its occurrences with 9
        char maxDigit = 0;
        for (char c : s.toCharArray()) {
            if (c != '9') { maxDigit = c; break; }
        }
        String maxStr = maxDigit == 0 ? s : s.replace(maxDigit, '9');
        
        // min: replace all occurrences of first digit with 0
        char minDigit = s.charAt(0);
        String minStr = s.replace(minDigit, '0');
        
        int maxVal = Integer.parseInt(maxStr);
        int minVal = Integer.parseInt(minStr);
        
        return maxVal - minVal;
    }
}
