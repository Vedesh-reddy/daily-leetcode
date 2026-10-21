/*
 * Apple Redistribution into Boxes (Easy)
 * https://leetcode.com/problems/apple-redistribution-into-boxes/
 *
 * We need the minimum number of boxes whose capacities can hold all the apples combined, so first sum up all apples to get the total demand. Then sort the capacities and greedily pick from the largest down, accumulating until the running sum meets or exceeds the total apple count, since using the biggest boxes first minimizes the count needed. The problem guarantees a valid solution exists, so the loop always terminates with an answer. Sorting dominates the complexity at O(m log m) time with O(1) extra space (ignoring sort's internal usage), where m is the number of boxes.
 */

class Solution {
    public int minimumBoxes(int[] apple, int[] capacity) {
        int totalApples = 0;
        for (int a : apple) totalApples += a;

        Arrays.sort(capacity);
        int count = 0;
        int sum = 0;
        for (int i = capacity.length - 1; i >= 0; i--) {
            sum += capacity[i];
            count++;
            if (sum >= totalApples) break;
        }
        return count;
    }
}

import java.util.Arrays;
