/*
 * Maximum Frequency Stack (Hard)
 * https://leetcode.com/problems/maximum-frequency-stack/
 *
 * Need to pop the element with highest frequency, breaking ties by most recently pushed. Track a frequency count per value and maintain a map from frequency level to a stack of values pushed at that frequency (group), plus a running maxFreq. On push, bump the value's freq and push it onto that frequency's stack, updating maxFreq if needed. On pop, pop from the stack at maxFreq (this naturally gives the most recent value among ties), decrement its freq count, and if that frequency's stack becomes empty, decrement maxFreq. Both push and pop run in O(1) time, with O(n) space for the maps.
 */

class FreqStack {
    private Map<Integer, Integer> freq;
    private Map<Integer, Deque<Integer>> group;
    private int maxFreq;

    public FreqStack() {
        freq = new HashMap<>();
        group = new HashMap<>();
        maxFreq = 0;
    }

    public void push(int val) {
        int f = freq.getOrDefault(val, 0) + 1;
        freq.put(val, f);
        if (f > maxFreq) maxFreq = f;
        group.computeIfAbsent(f, k -> new ArrayDeque<>()).push(val);
    }

    public int pop() {
        int val = group.get(maxFreq).pop();
        freq.put(val, freq.get(val) - 1);
        if (group.get(maxFreq).isEmpty()) maxFreq--;
        return val;
    }
}

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(val);
 * int param_2 = obj.pop();
 */
