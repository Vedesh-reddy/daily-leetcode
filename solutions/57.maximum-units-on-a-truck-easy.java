/*
 * Maximum Units on a Truck (Easy)
 * https://leetcode.com/problems/maximum-units-on-a-truck/
 *
 * The goal is to fill a truck with a limited number of boxes to maximize total units, given several box types each with a count and per-box unit value. Greedy approach works here: sort box types by units-per-box in descending order, then fill the truck starting with the highest-value boxes first, taking as many as possible from each type until the truck is full. This works because swapping a higher-value box for a lower-value one never improves the total. Sorting takes O(n log n) and the fill pass is O(n), so overall time complexity is O(n log n) with O(1) extra space (ignoring sort's internal usage).
 */

class Solution {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);
        
        int total = 0;
        for (int[] box : boxTypes) {
            if (truckSize <= 0) break;
            int take = Math.min(truckSize, box[0]);
            total += take * box[1];
            truckSize -= take;
        }
        
        return total;
    }
}
