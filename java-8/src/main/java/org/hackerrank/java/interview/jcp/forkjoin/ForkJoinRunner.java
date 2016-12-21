package org.hackerrank.java.interview.jcp.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicInteger;

public class ForkJoinRunner implements Runnable {
    public final static AtomicInteger nextId = new AtomicInteger(0);

    private final ForkJoinPool pool = new ForkJoinPool(2);

    public static void main(String... args) {
        new Thread(new ForkJoinRunner()).start();
    }

    @Override
    public void run() {
        pool.invoke(new RegularRecursiveTask(1000));
        pool.invoke(new FibonacciTask(100));
        while (pool.getActiveThreadCount() > 0){
            System.out.printf("******************************************\n");
            System.out.printf("Main: Parallelism: %d\n", pool.getParallelism());
            System.out.printf("Main: Active Threads: %d\n", pool.getActiveThreadCount());
            System.out.printf("Main: Task Count: %d\n", pool.getQueuedTaskCount());
            System.out.printf("Main: Steal Count: %d\n", pool.getStealCount());
            System.out.printf("******************************************\n");
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
