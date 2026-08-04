/*
 * Day of the Year (Easy)
 * https://leetcode.com/problems/day-of-the-year/
 *
 * Parse the year, month, and day from the fixed-format string using substring indices. Determine if the year is a leap year to handle February correctly (29 days vs 28). Sum up the days of all months before the given month, then add the day of the current month. Time complexity is O(1) since we only loop through at most 11 months, and space is O(1) for the fixed-size days array.
 */

class Solution {
    public int dayOfYear(String date) {
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7));
        int day = Integer.parseInt(date.substring(8, 10));
        
        int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        boolean leap = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
        if (leap) days[1] = 29;
        
        int total = day;
        for (int i = 0; i < month - 1; i++) {
            total += days[i];
        }
        return total;
    }
}
