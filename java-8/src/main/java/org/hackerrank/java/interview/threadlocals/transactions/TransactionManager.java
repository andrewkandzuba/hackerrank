package org.hackerrank.java.interview.threadlocals.transactions;

import java.util.UUID;

public class TransactionManager {
    private static final ThreadLocal<String> context = new ThreadLocal<String>();

    public static void startTransaction() throws Exception {
        context.set(UUID.randomUUID().toString());
    }

    public static String getTransactionId() {
        return context.get();
    }

    public static void endTransaction() {
        //logic to end a transaction
        context.remove();
    }
}
