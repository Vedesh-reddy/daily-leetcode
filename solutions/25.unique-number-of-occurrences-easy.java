/*
 * Unique Number of Occurrences (Easy)
 * https://leetcode.com/problems/unique-number-of-occurrences/
 *
 * The task is to check whether every distinct value in the array occurs a different number of times than every other value. I first build a frequency map counting occurrences of each number, then walk through the resulting counts and add them to a set—if adding a count fails (already present), two values share the same occurrence count, so return false. If all counts are unique, return true. Time complexity is O(n) for building the frequency map plus O(k) for checking uniqueness where k is the number of distinct elements, so overall O(n). Space complexity is O(n) for the map and set in the worst case.
 */

class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int n : arr) {
            count.put(n, count.getOrDefault(n, 0) + 1);
        }
        Set<Integer> seen = new HashSet<>();
        for (int c : count.values()) {
            if (!seen.add(c)) return false;
        }
        return true;
    }
}
