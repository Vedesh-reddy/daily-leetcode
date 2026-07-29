/*
 * String Matching in an Array (Easy)
 * https://leetcode.com/problems/string-matching-in-an-array/
 *
 * The task is to find every word that appears as a substring inside some other word in the array. Since the constraints are small, a brute-force double loop works fine: for each word, check if it's contained in any other distinct word using String.contains, and if so add it once and move to the next word. Time complexity is O(n^2 * m) where n is the number of words and m is the average string length (due to substring search cost), and space is O(n) for the result list.
 */

import java.util.*;

class Solution {
    public List<String> stringMatching(String[] words) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if (i != j && words[j].contains(words[i])) {
                    res.add(words[i]);
                    break;
                }
            }
        }
        return res;
    }
}
