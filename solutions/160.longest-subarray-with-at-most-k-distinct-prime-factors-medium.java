/*
 * Longest Subarray With at Most K Distinct Prime Factors (Medium)
 * https://leetcode.com/problems/longest-subarray-with-at-most-k-distinct-prime-factors/
 *
 * Each number's distinct prime factors are precomputed once via trial division up to sqrt(value). A sliding window maintains a frequency map of prime factors currently present in the window; the number of keys in that map is the distinct prime factor count for the window. When that count exceeds k, the left edge advances, decrementing counts and removing keys that hit zero. The answer is the max window width seen. Precomputation costs O(n*sqrt(maxVal)), and the sliding window itself runs in amortized O(n) with map operations, giving overall roughly O(n*sqrt(maxVal)) time and O(n) extra space for storing factor sets plus the frequency map.
 */

class Solution {
    public int longestSubarray(int[] nums, int k) {
        int n = nums.length;
        List<Set<Integer>> factors = new ArrayList<>();
        for (int num : nums) {
            factors.add(primeFactors(num));
        }

        Map<Integer, Integer> primeCount = new HashMap<>();
        int distinct = 0;
        int left = 0;
        int best = 0;

        for (int right = 0; right < n; right++) {
            for (int p : factors.get(right)) {
                int c = primeCount.getOrDefault(p, 0);
                if (c == 0) distinct++;
                primeCount.put(p, c + 1);
            }

            while (distinct > k) {
                for (int p : factors.get(left)) {
                    int c = primeCount.get(p);
                    if (c == 1) {
                        primeCount.remove(p);
                        distinct--;
                    } else {
                        primeCount.put(p, c - 1);
                    }
                }
                left++;
            }

            best = Math.max(best, right - left + 1);
        }

        return best;
    }

    private Set<Integer> primeFactors(int num) {
        Set<Integer> set = new HashSet<>();
        int x = num;
        for (int p = 2; (long) p * p <= x; p++) {
            if (x % p == 0) {
                set.add(p);
                while (x % p == 0) x /= p;
            }
        }
        if (x > 1) set.add(x);
        return set;
    }
}
