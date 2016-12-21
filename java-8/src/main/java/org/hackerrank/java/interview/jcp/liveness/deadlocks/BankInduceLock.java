package org.hackerrank.java.interview.jcp.liveness.deadlocks;

import org.hackerrank.java.interview.jcp.liveness.deadlocks.finance.Account;
import org.hackerrank.java.interview.jcp.liveness.deadlocks.finance.Amount;
import org.hackerrank.java.interview.jcp.liveness.deadlocks.finance.Bank;
import org.hackerrank.java.interview.jcp.liveness.deadlocks.finance.InsufficientFundsException;

import java.util.concurrent.TimeUnit;

class BankInduceLock extends Bank {
    private static final Object tieLock = new Object();

    public boolean transferMoney(Account fromAccount, Account toAccount, Amount amount, long timeout, TimeUnit unit) throws InsufficientFundsException, InterruptedException  {
        class Helper {
            private void transfer() throws InsufficientFundsException {
                transferMoneyImpl(fromAccount, toAccount, amount);
            }
        }
        int fromHash = System.identityHashCode(fromAccount);
        int toHash = System.identityHashCode(toAccount);
        if (fromHash < toHash) {
            synchronized (fromAccount) {
                synchronized (toAccount) {
                    new Helper().transfer();
                }
            }
        } else if (fromHash > toHash) {
            synchronized (toAccount) {
                synchronized (fromAccount) {
                    new Helper().transfer();
                }
            }
        } else {
            synchronized (tieLock) {
                synchronized (fromAccount) {
                    synchronized (toAccount) {
                        new Helper().transfer();
                    }
                }
            }
        }
        return true;
    }
}
