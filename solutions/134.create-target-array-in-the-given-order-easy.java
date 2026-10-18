/*
 * Create Target Array in the Given Order (Easy)
 * https://leetcode.com/problems/create-target-array-in-the-given-order/
 *
 * The task is to build an array by repeatedly inserting values at given positions, shifting existing elements as needed. I used a Java ArrayList since it supports insertion at an arbitrary index with built-in shifting via `add(index, value)`, then converted the list to an int array at the end. Each insertion is O(n) in the worst case due to shifting, so overall time complexity is O(n^2) for n elements, with O(n) extra space for the list and result array.
 */

class Solution {
    public int[] createTargetArray(int[] nums, int[] index) {
        List<Integer> target = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            target.add(index[i], nums[i]);
        }
        int[] result = new int[target.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = target.get(i);
        }
        return result;
    }
}
