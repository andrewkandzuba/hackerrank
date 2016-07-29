package org.hackerrank.java.interview.jcp.interruption.concurrent.executors;

import org.hackerrank.java.interview.jcp.interruption.concurrent.threadfactories.NamedThreadFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ServerThreadPool extends TrackingCancellingPool {
    public ServerThreadPool(String poolName, int parallelismLevel, int capacity) {
        super(parallelismLevel, parallelismLevel,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(capacity),
                new NamedThreadFactory(poolName));
        this.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    }
}
