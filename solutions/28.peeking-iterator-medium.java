/*
 * Peeking Iterator (Medium)
 * https://leetcode.com/problems/peeking-iterator/
 *
 * Need to wrap an existing Iterator so we can look at the next value without consuming it. The trick is to cache one element: when peek() is called and nothing is cached, pull from the underlying iterator and store it; subsequent peeks return the cached value. next() returns the cached value if present (clearing it), otherwise pulls directly from the underlying iterator. hasNext() checks either the cache or delegates to the underlying iterator. All operations run in O(1) time (amortized, since each element is pulled from the underlying iterator at most once) with O(1) extra space for the single cached element.
 */

import java.util.Iterator;

class PeekingIterator implements Iterator<Integer> {
    private Iterator<Integer> iter;
    private Integer peeked;

    public PeekingIterator(Iterator<Integer> iterator) {
        iter = iterator;
        peeked = null;
    }

    public Integer peek() {
        if (peeked == null) {
            peeked = iter.next();
        }
        return peeked;
    }

    @Override
    public Integer next() {
        if (peeked != null) {
            Integer res = peeked;
            peeked = null;
            return res;
        }
        return iter.next();
    }

    @Override
    public boolean hasNext() {
        return peeked != null || iter.hasNext();
    }
}

class Solution {
}
