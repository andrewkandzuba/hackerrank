package org.hackerrank.java.interview.jcp.liveness.deadlocks.finance;

public class Amount implements Comparable<Amount> {
    private final int amount;

    public Amount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public int compareTo(Amount o) {
        return Integer.compare(amount, o.amount);
    }
}
