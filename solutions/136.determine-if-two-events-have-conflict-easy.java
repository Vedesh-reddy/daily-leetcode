/*
 * Determine if Two Events Have Conflict (Easy)
 * https://leetcode.com/problems/determine-if-two-events-have-conflict/
 *
 * Two time intervals overlap unless one ends before the other starts. Since times are in "HH:MM" format with fixed width, string comparison works the same as numeric comparison, so no parsing is needed. The condition checks that event1 starts before or when event2 ends, and event2 starts before or when event1 ends — if both hold, the intervals intersect. Time and space complexity are O(1) since string comparisons on fixed-length strings are constant time.
 */

class Solution {
    public boolean haveConflict(String[] event1, String[] event2) {
        return event1[0].compareTo(event2[1]) <= 0 && event2[0].compareTo(event1[1]) <= 0;
    }
}
