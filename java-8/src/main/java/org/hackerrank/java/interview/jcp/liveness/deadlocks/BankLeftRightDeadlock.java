package org.hackerrank.java.interview.jcp.liveness.deadlocks;

import org.hackerrank.java.interview.jcp.liveness.deadlocks.finance.Account;
import org.hackerrank.java.interview.jcp.liveness.deadlocks.finance.Amount;
import org.hackerrank.java.interview.jcp.liveness.deadlocks.finance.Bank;
import org.hackerrank.java.interview.jcp.liveness.deadlocks.finance.InsufficientFundsException;

import java.util.concurrent.TimeUnit;

class BankLeftRightDeadlock extends Bank {

    @Override
    public boolean transferMoney(Account fromAccount, Account toAccount, Amount amount, long timeout, TimeUnit unit) throws InsufficientFundsException, InterruptedException {
        synchronized (fromAccount) {
            synchronized (toAccount) {
                transferMoneyImpl(fromAccount, toAccount, amount);
                return true;
            }
        }
    }
}
