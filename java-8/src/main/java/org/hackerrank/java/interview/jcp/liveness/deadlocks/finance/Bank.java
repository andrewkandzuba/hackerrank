package org.hackerrank.java.interview.jcp.liveness.deadlocks.finance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public abstract class Bank {
    private static Logger logger = LoggerFactory.getLogger(Bank.class);

    public abstract boolean transferMoney(final Account fromAccount, final Account toAccount, final Amount amount, long timeout, TimeUnit unit) throws InsufficientFundsException, InterruptedException;

    protected final boolean transferMoneyImpl(final Account fromAccount, final Account toAccount, final Amount amount) throws InsufficientFundsException {
        if (fromAccount.getBalance().compareTo(amount) < 0) {
            logger.error("Unable to complete a transfer of {}. Insufficient funds", amount.getAmount());
            throw new InsufficientFundsException();
        } else {
            fromAccount.debit(amount);
            toAccount.credit(amount);
            logger.info("Transfer of {} has successfully completed", amount.getAmount());
            return true;
        }
    }
}
