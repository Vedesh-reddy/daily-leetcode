/*
 * Happy Number (Easy)
 * https://leetcode.com/problems/happy-number/
 *
 * The task is to check whether repeatedly replacing a number with the sum of squares of its digits eventually reaches 1 (happy) or falls into a repeating cycle that never hits 1 (unhappy). I use a HashSet to track previously seen values; if the process ever revisits a number before reaching 1, it's stuck in a cycle and n is not happy. Each iteration computes the digit-square sum via simple mod/divide operations. Time complexity is hard to bound tightly but is effectively O(log n) per transformation with a small number of iterations before cycling, and space is O(k) where k is the number of unique values visited before termination.
 */

class Solution {
    public boolean isHappy(int n) {
        Set<Integer> seen = new HashSet<>();
        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            n = sumOfSquares(n);
        }
        return n == 1;
    }

    private int sumOfSquares(int n) {
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }
}
