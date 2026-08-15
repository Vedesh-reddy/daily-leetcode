/*
 * Report Spam Message (Medium)
 * https://leetcode.com/problems/report-spam-message/
 *
 * The task is to check whether at least two words in `message` appear in `bannedWords`. I put all banned words into a HashSet for O(1) lookups, then iterate through `message`, counting how many words are found in the set, stopping early once the count reaches 2. Time complexity is O(m + n) where m and n are the lengths of `message` and `bannedWords`, and space complexity is O(n) for the set.
 */

class Solution {
    public boolean reportSpam(String[] message, String[] bannedWords) {
        Set<String> banned = new HashSet<>(Arrays.asList(bannedWords));
        int count = 0;
        for (String word : message) {
            if (banned.contains(word)) {
                count++;
                if (count >= 2) return true;
            }
        }
        return false;
    }
}
