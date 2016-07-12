package org.hackerrank.java.interview.jcp.concurrent;

import java.util.concurrent.ThreadFactory;

public class UEHThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setUncaughtExceptionHandler((t1, e) -> {
            System.out.println("From custom UncaughtExceptionHandler: " + t1.getName() + " " + e.getCause());
        });
        return t;
    }
}
