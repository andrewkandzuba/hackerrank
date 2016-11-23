package org.hackerrank.java.interview.jcp.interruption.concurrent.threadfactories;

import java.util.concurrent.ThreadFactory;

public class NamedThreadFactory implements ThreadFactory {
    private final String poolName;

    public NamedThreadFactory(String poolName) {
        this.poolName = poolName;
    }

    public Thread newThread(Runnable runnable) {
        return new MeasurableThread(runnable, poolName);
    }
}