/*
 * Minimum Index Sum of Two Lists (Easy)
 * https://leetcode.com/problems/minimum-index-sum-of-two-lists/
 *
 * Need to find strings common to both lists whose combined index sum (position in list1 plus position in list2) is minimal, returning all ties. Approach: build a hash map from string to its index in list1, then iterate list2, checking for matches and computing the sum on the fly, tracking the current minimum and collecting matching strings into a result list (clearing it whenever a smaller sum is found). This avoids a second pass or extra storage of all sums. Time complexity is O(n + m) where n and m are the lengths of list1 and list2, and space complexity is O(n) for the map plus O(k) for the result where k is the number of tied answers.
 */

class Solution {
    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
        }

        int minSum = Integer.MAX_VALUE;
        List<String> result = new ArrayList<>();

        for (int j = 0; j < list2.length; j++) {
            Integer i = map.get(list2[j]);
            if (i == null) continue;
            int sum = i + j;
            if (sum < minSum) {
                minSum = sum;
                result.clear();
                result.add(list2[j]);
            } else if (sum == minSum) {
                result.add(list2[j]);
            }
        }

        return result.toArray(new String[0]);
    }
}
