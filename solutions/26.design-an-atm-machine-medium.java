/*
 * Design an ATM Machine (Medium)
 * https://leetcode.com/problems/design-an-atm-machine/
 *
 * The ATM needs to track counts of 5 banknote denominations and greedily withdraw starting from the largest denomination down to the smallest, without substituting smaller bills for a shortfall on a larger one. I keep a running count array updated on deposit, and on withdraw I iterate from $500 down to $20, taking as many of each denomination as possible (bounded by both availability and remaining amount), then check if the amount was fully covered—if not, return [-1] without mutating state. Used long for counts to safely handle sums up to 1e9 without overflow during multiplication. Both deposit and withdraw run in O(1) time (fixed 5 denominations) and O(1) space aside from the stored counts array.
 */

class ATM {
    private long[] count;
    private int[] values = {20, 50, 100, 200, 500};

    public ATM() {
        count = new long[5];
    }
    
    public void deposit(int[] banknotesCount) {
        for (int i = 0; i < 5; i++) {
            count[i] += banknotesCount[i];
        }
    }
    
    public int[] withdraw(int amount) {
        int[] result = new int[5];
        for (int i = 4; i >= 0; i--) {
            long use = Math.min(count[i], amount / values[i]);
            result[i] = (int) use;
            amount -= use * values[i];
        }
        if (amount != 0) {
            return new int[]{-1};
        }
        for (int i = 0; i < 5; i++) {
            count[i] -= result[i];
        }
        return result;
    }
}
