package org.hackerrank.java.interview.jcp.liveness.deadlocks;

import org.hackerrank.java.interview.jcp.interruption.concurrent.PlatformExecutors;
import org.hackerrank.java.interview.jcp.liveness.deadlocks.finance.Account;
import org.hackerrank.java.interview.jcp.liveness.deadlocks.finance.Amount;
import org.hackerrank.java.interview.jcp.liveness.deadlocks.finance.Bank;
import org.hackerrank.java.interview.jcp.liveness.deadlocks.finance.InsufficientFundsException;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DemonstrateDeadlock {
    private static final int NUM_THREADS = 20;
    private static final int NUM_ACCOUNTS = 5;
    private static final int NUM_ITERATIONS = 1000000;

    public static void main(String[] args) throws Exception {
        if (args.length == 0) throw new IllegalArgumentException("Please specify bank's type!");

        final Bank bank = BankFactory.of(args[0]);
        final Random rnd = new Random();
        final Account[] accounts = new Account[NUM_ACCOUNTS];

        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new Account(rnd.nextInt(1000));
        }
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < NUM_THREADS; i++) {
            es.execute(() -> {
                for (int k = 0; k < NUM_ITERATIONS; k++) {
                    if(Thread.currentThread().isInterrupted()){
                        return;
                    }
                    int fromAcct = rnd.nextInt(NUM_ACCOUNTS);
                    int toAcct = rnd.nextInt(NUM_ACCOUNTS);
                    Amount amount = new Amount(rnd.nextInt(1000));
                    try {
                        bank.transferMoney(accounts[fromAcct], accounts[toAcct], amount, 1, TimeUnit.SECONDS);
                    } catch (InsufficientFundsException ignored) {
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
        }
        PlatformExecutors.shutdownGracefully(es, 1, TimeUnit.SECONDS);
    }
}
