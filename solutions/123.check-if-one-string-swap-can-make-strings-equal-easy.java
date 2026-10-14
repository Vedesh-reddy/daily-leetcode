/*
 * Check if One String Swap Can Make Strings Equal (Easy)
 * https://leetcode.com/problems/check-if-one-string-swap-can-make-strings-equal/
 *
 * We need to determine if swapping exactly two characters in one string can make it equal to the other. First check if the strings are already equal (zero swaps needed, which satisfies "at most one"). Otherwise, find all positions where the characters differ. If there aren't exactly two differing positions, no single swap can fix it, so return false. If there are exactly two, check if swapping those two characters in s1 would match s2 - this means s1's char at the first diff position should equal s2's char at the second, and vice versa. Time complexity is O(n) for the single pass comparison, space is O(n) worst case for storing diff indices (though bounded by 2 in practice since we return early).
 */

class Solution {
    public boolean areAlmostEqual(String s1, String s2) {
        if (s1.equals(s2)) return true;
        
        List<Integer> diff = new ArrayList<>();
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) diff.add(i);
        }
        
        if (diff.size() != 2) return false;
        
        int i = diff.get(0), j = diff.get(1);
        return s1.charAt(i) == s2.charAt(j) && s1.charAt(j) == s2.charAt(i);
    }
}

import java.util.*;
