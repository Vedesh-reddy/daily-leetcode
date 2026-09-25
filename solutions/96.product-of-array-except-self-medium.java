/*
 * Product of Array Except Self (Medium)
 * https://leetcode.com/problems/product-of-array-except-self/
 *
 * Fill the answer with prefix products in one pass, then multiply in suffix products in a reverse pass, avoiding division. Time O(n), extra space O(1).
 */

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];
        answer[0] = 1;
        for (int i = 1; i < n; i++) answer[i] = answer[i - 1] * nums[i - 1];
        int suffix = 1;
        for (int i = n - 1; i >= 0; i--) {
            answer[i] *= suffix;
            suffix *= nums[i];
        }
        return answer;
    }
}
