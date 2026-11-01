/*
 * Design Memory Allocator (Medium)
 * https://leetcode.com/problems/design-memory-allocator/
 *
 * The task is to simulate a memory array where blocks get tagged with ids and later cleared. I just keep an int array where 0 means free and any other value is the owning mID. Allocate scans left to right tracking a run of free cells, and once it hits the requested size it stamps that range with mID and returns the start index; if no run is big enough it returns -1. FreeMemory does a linear scan clearing every cell matching the given id and counts how many it cleared. Given the constraint of at most 1000 calls, this O(n) per operation brute force is plenty fast — overall roughly O(calls * n) time and O(n) space.
 */

class Allocator {
    int[] mem;

    public Allocator(int n) {
        mem = new int[n];
    }
    
    public int allocate(int size, int mID) {
        int count = 0;
        for (int i = 0; i < mem.length; i++) {
            if (mem[i] == 0) {
                count++;
                if (count == size) {
                    int start = i - size + 1;
                    for (int j = start; j <= i; j++) mem[j] = mID;
                    return start;
                }
            } else {
                count = 0;
            }
        }
        return -1;
    }
    
    public int freeMemory(int mID) {
        int freed = 0;
        for (int i = 0; i < mem.length; i++) {
            if (mem[i] == mID) {
                mem[i] = 0;
                freed++;
            }
        }
        return freed;
    }
}

/**
 * Your Allocator object will be instantiated and called as such:
 * Allocator obj = new Allocator(n);
 * int param_1 = obj.allocate(size,mID);
 * int param_2 = obj.freeMemory(mID);
 */
