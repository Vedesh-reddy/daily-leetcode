/*
 * LFU Cache (Hard)
 * https://leetcode.com/problems/lfu-cache/
 *
 * LFU cache needs O(1) get/put while tracking both frequency and recency for tie-breaking. The trick is keeping a map from frequency -> LinkedHashSet of keys (insertion order gives LRU ordering within a frequency bucket) plus maps from key->value and key->freq. On access, remove the key from its current frequency bucket, bump its frequency, and reinsert into the new bucket's set (appended at the end, making it most recent). Track `minFreq` so eviction just pulls the first element (LRU) from the bucket at minFreq; when a bucket empties and it was minFreq, increment minFreq. On put, if the key exists we just update value and touch it; otherwise, evict if full, then insert new key with freq 1 and reset minFreq to 1. All operations (HashMap/LinkedHashSet insert, remove, iterator.next) are O(1) average, giving overall O(1) time per operation and O(capacity) space.
 */

import java.util.*;

class LFUCache {
    private int capacity;
    private int minFreq;
    private Map<Integer, Integer> keyToVal;
    private Map<Integer, Integer> keyToFreq;
    private Map<Integer, LinkedHashSet<Integer>> freqToKeys;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;
        keyToVal = new HashMap<>();
        keyToFreq = new HashMap<>();
        freqToKeys = new HashMap<>();
    }

    public int get(int key) {
        if (!keyToVal.containsKey(key)) return -1;
        touch(key);
        return keyToVal.get(key);
    }

    public void put(int key, int value) {
        if (capacity <= 0) return;

        if (keyToVal.containsKey(key)) {
            keyToVal.put(key, value);
            touch(key);
            return;
        }

        if (keyToVal.size() >= capacity) {
            // evict LRU key from minFreq bucket
            LinkedHashSet<Integer> set = freqToKeys.get(minFreq);
            int evictKey = set.iterator().next();
            set.remove(evictKey);
            if (set.isEmpty()) freqToKeys.remove(minFreq);
            keyToVal.remove(evictKey);
            keyToFreq.remove(evictKey);
        }

        keyToVal.put(key, value);
        keyToFreq.put(key, 1);
        freqToKeys.computeIfAbsent(1, k -> new LinkedHashSet<>()).add(key);
        minFreq = 1;
    }

    private void touch(int key) {
        int freq = keyToFreq.get(key);
        LinkedHashSet<Integer> oldSet = freqToKeys.get(freq);
        oldSet.remove(key);
        if (oldSet.isEmpty()) {
            freqToKeys.remove(freq);
            // if this bucket was minFreq bump it, since it's now gone
            if (minFreq == freq) minFreq++;
        }
        int newFreq = freq + 1;
        keyToFreq.put(key, newFreq);
        freqToKeys.computeIfAbsent(newFreq, k -> new LinkedHashSet<>()).add(key);
    }
}
