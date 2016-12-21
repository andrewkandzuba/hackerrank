package org.hackerrank.java.interview.jcp.liveness.deadlocks.finance;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
    public final Lock lock = new ReentrantLock();
    private volatile int balance;

    public Account(int balance) {
        this.balance = balance;
    }

    void debit(Amount amount){
        balance-= amount.getAmount();
    }

    void credit(Amount amount){
        balance+= amount.getAmount();
    }

    Amount getBalance(){
        return new Amount(balance);
    }
}
