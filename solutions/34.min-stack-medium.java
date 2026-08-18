/*
 * Min Stack (Medium)
 * https://leetcode.com/problems/min-stack/
 *
 * Need a stack that also answers "current minimum" in O(1). Approach: maintain a second stack (minStack) parallel to the main stack, where each position stores the minimum of all elements up to that point. On push, compare new value with current min and push the smaller one onto minStack; on pop, pop both stacks together so they stay aligned. This avoids storing (value, min) pairs but achieves the same effect. Time complexity is O(1) for all four operations, and space is O(n) for n elements since both stacks grow linearly.
 */

class MinStack {
    private Deque<Integer> stack;
    private Deque<Integer> minStack;

    public MinStack() {
        stack = new ArrayDeque<>();
        minStack = new ArrayDeque<>();
    }
    
    public void push(int value) {
        stack.push(value);
        // keep min on top of minStack, push again if not smaller so pop stays in sync
        if (minStack.isEmpty() || value <= minStack.peek()) {
            minStack.push(value);
        } else {
            minStack.push(minStack.peek());
        }
    }
    
    public void pop() {
        stack.pop();
        minStack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(value);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
