/*
 * Count Valid Word Occurrences (Medium)
 * https://leetcode.com/problems/count-valid-word-occurrences/
 *
 * First concatenate all chunks into one string, then scan it character by character to rebuild words: letters always extend the current word, a hyphen only extends it if both neighboring characters in the original string are lowercase letters (making it a "joiner"), otherwise any non-letter character (space, non-joiner hyphen) terminates the current word. Each completed word is tallied in a HashMap. Finally, each query is looked up in the map, defaulting to 0 if absent. Time complexity is O(total length of chunks + total length of queries) since we do a single linear scan plus constant-time map lookups, and space complexity is O(number of distinct words) for the frequency map.
 */

class Solution {
    public int[] countWordOccurrences(String[] chunks, String[] queries) {
        StringBuilder sb = new StringBuilder();
        for (String c : chunks) sb.append(c);
        String s = sb.toString();
        int n = s.length();

        java.util.HashMap<String, Integer> freq = new java.util.HashMap<>();
        StringBuilder word = new StringBuilder();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (Character.isLowerCase(c)) {
                word.append(c);
            } else if (c == '-') {
                boolean joiner = i > 0 && i < n - 1
                        && Character.isLowerCase(s.charAt(i - 1))
                        && Character.isLowerCase(s.charAt(i + 1));
                if (joiner) {
                    word.append(c);
                } else {
                    // separator hyphen, flush current word
                    if (word.length() > 0) {
                        freq.merge(word.toString(), 1, Integer::sum);
                        word.setLength(0);
                    }
                }
            } else {
                // any other separator (space, etc.)
                if (word.length() > 0) {
                    freq.merge(word.toString(), 1, Integer::sum);
                    word.setLength(0);
                }
            }
        }
        if (word.length() > 0) {
            freq.merge(word.toString(), 1, Integer::sum);
        }

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = freq.getOrDefault(queries[i], 0);
        }
        return ans;
    }
}
