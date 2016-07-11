package org.hackerrank.java.interview.jcp.interruption.concurrent;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class TrackingExecutorService extends AbstractExecutorService {
    private final ExecutorService es;
    private final Set<Runnable> tasksCancelledAndShutdown = Collections.synchronizedSet(new HashSet<>());

    public TrackingExecutorService(ExecutorService es) {
        this.es = es;
    }

    @Override
    public void shutdown() {
        es.shutdown();
    }

    @Override
    public List<Runnable> shutdownNow() {
        return es.shutdownNow();
    }

    @Override
    public boolean isShutdown() {
        return es.isShutdown();
    }

    @Override
    public boolean isTerminated() {
        return es.isTerminated();
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return es.awaitTermination(timeout, unit);
    }

    @Override
    public void execute(Runnable command) {
        es.execute(() -> {
            try {
                command.run();
            } finally {
                if(isShutdown() && Thread.currentThread().isInterrupted()){
                    tasksCancelledAndShutdown.add(command);
                }
            }
        });
    }

    public Set<Runnable> getTasksCancelledAndShutdown() {
        return tasksCancelledAndShutdown;
    }
}
