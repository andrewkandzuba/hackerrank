package org.hackerrank.java.interview.jcp.interruption.concurrent.executors;

import org.hackerrank.java.interview.jcp.interruption.concurrent.CancellableTask;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

public abstract class TrackingCancellingPool extends MeasurableThreadPool {
    private final Set<Runnable> tasksCancelledAndShutdown = Collections.synchronizedSet(new HashSet<>());

    public TrackingCancellingPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public TrackingCancellingPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public TrackingCancellingPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public TrackingCancellingPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    @Override
    public void execute(Runnable command) {
        super.execute(() -> {
            try {
                command.run();
            } finally {
                if (isShutdown() && Thread.currentThread().isInterrupted()) {
                    tasksCancelledAndShutdown.add(command);
                }
            }
        });
    }

    @Override
    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        if (callable instanceof CancellableTask) {
            return ((CancellableTask<T>) callable).newTask();
        }
        return super.newTaskFor(callable);
    }

    public Set<Runnable> getTasksCancelledAndShutdown() {
        return Collections.unmodifiableSet(tasksCancelledAndShutdown);
    }
}
