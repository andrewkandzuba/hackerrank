package org.hackerrank.java.interview.jcp.liveness.deadlocks;

import org.hackerrank.java.interview.jcp.liveness.deadlocks.finance.Bank;

class BankFactory {
    private enum BankType {
        LEFTRIGHTDEADLOCK,
        INDUCELOCK,
        REENTRANTLOCK
    }

    public static Bank of(String bankType) {
        switch (BankType.valueOf(bankType)) {
            case INDUCELOCK:
                return new BankInduceLock();
            case REENTRANTLOCK:
                return new BackReentrantLock();
            case LEFTRIGHTDEADLOCK:
            default:
                return new BankLeftRightDeadlock();
        }
    }
}
