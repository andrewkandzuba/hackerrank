package org.hackerrank.java.interview.jcp.forkjoin;

import java.util.concurrent.RecursiveTask;

public class LongRunningRecursiveTask extends RecursiveTask<Integer>{
    private static final ThreadLocal<Integer> threadId =
            new ThreadLocal<Integer>() {
                @Override
                protected Integer initialValue() {
                    return ForkJoinRunner.nextId.getAndIncrement();
                }
            };

    private final int n;

    public LongRunningRecursiveTask(int n) {
        this.n = n ;
    }

    @Override
    protected Integer compute() {
        System.out.println(String.format("[Thread-%s] - %s : %s", threadId.get(), "LongRunningRecursiveTask", n));
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(n == 0){
            return n;
        }
        return n - 1;
    }
}
