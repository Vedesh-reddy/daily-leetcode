/*
 * Lexicographically Largest String After Pair Transformations (Medium)
 * https://leetcode.com/problems/lexicographically-largest-string-after-pair-transformations/
 *
 * Repeatedly merging two adjacent equal letters into the next letter is exactly how binary counting works: combining two 'a's into a 'b' is like carrying a bit, two 'b's into a 'c' is the next carry, and so on. So the final largest string for x is determined by the binary representation of x — each set bit at position j contributes the letter ('a'+j), and since higher letters are lexicographically larger and should come first, we output the letters from the highest set bit down to the lowest. This directly matches all given examples. The solution loops through bits for each number, giving O(n log(max(nums[i]))) time and O(1) extra space per query (aside from output).
 */

class Solution {
    public String[] largestString(int[] nums) {
        String[] ans = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = convert(nums[i]);
        }
        return ans;
    }
    
    private String convert(int x) {
        // repeated pairing of 'a's is just binary representation,
        // bit j set -> letter ('a'+j) present; output highest bit first
        StringBuilder sb = new StringBuilder();
        for (int j = 30; j >= 0; j--) {
            if ((x & (1 << j)) != 0) {
                sb.append((char) ('a' + j));
            }
        }
        return sb.toString();
    }
}
