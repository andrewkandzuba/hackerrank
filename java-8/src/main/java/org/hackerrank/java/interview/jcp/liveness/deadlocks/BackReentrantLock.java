package org.hackerrank.java.interview.jcp.liveness.deadlocks;

import org.hackerrank.java.interview.jcp.liveness.deadlocks.finance.Account;
import org.hackerrank.java.interview.jcp.liveness.deadlocks.finance.Amount;
import org.hackerrank.java.interview.jcp.liveness.deadlocks.finance.Bank;
import org.hackerrank.java.interview.jcp.liveness.deadlocks.finance.InsufficientFundsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

public class BackReentrantLock extends Bank {
    private static Logger logger = LoggerFactory.getLogger(BackReentrantLock.class);
    private final Random rnd = new Random();

    @Override
    public boolean transferMoney(Account fromAccount, Account toAccount, Amount amount, long timeout, TimeUnit unit) throws InsufficientFundsException, InterruptedException {

        long fixedDelay = getFixedDelayComponentNanos(timeout, unit);
        long randMod = getRandomDelayModulusNanos(timeout, unit);
        long stopTime = System.nanoTime() + unit.toNanos(timeout);
        int counter = 0;

        logger.info("fixedDelay = {}, randMod = {}, stopTime={}", fixedDelay, randMod, stopTime);
        while (true) {
            if (fromAccount.lock.tryLock()) {
                try {
                    if (toAccount.lock.tryLock()) {
                        try {
                            return transferMoneyImpl(fromAccount, toAccount, amount);
                        } finally {
                            toAccount.lock.unlock();
                        }
                    }
                } finally {
                    fromAccount.lock.unlock();
                }
            }
            if (System.nanoTime() > stopTime) {
                logger.error("Unable to transfer money within a requested period of time {} {}", timeout, unit);
                return false;
            }
            NANOSECONDS.sleep(fixedDelay + rnd.nextLong() % randMod);
            logger.info("Retry {} ", counter++);
        }
    }

    private long getFixedDelayComponentNanos(long timeout, TimeUnit unit) {
        return TimeUnit.NANOSECONDS.convert(timeout, unit) / 10;
    }

    private long getRandomDelayModulusNanos(long timeout, TimeUnit unit) {
        return getFixedDelayComponentNanos(timeout, unit) / (rnd.nextInt(10) + 1);
    }
}
