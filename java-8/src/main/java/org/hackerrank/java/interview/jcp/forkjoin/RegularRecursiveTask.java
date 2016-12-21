package org.hackerrank.java.interview.jcp.forkjoin;

import java.util.concurrent.RecursiveTask;

public class RegularRecursiveTask extends RecursiveTask<Integer> {
    private static final ThreadLocal<Integer> threadId =
            new ThreadLocal<Integer>() {
                @Override
                protected Integer initialValue() {
                    return ForkJoinRunner.nextId.getAndIncrement();
                }
            };

    private final int n;

    public RegularRecursiveTask(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        System.out.println(String.format("[Thread-%s] - %s : %s", threadId.get(), "RegularRecursiveTask - start", n));
        if(n == 0){
            return n;
        }
        LongRunningRecursiveTask task = new LongRunningRecursiveTask(n - 1);
        task.fork();
        RegularRecursiveTask task1 = new RegularRecursiveTask(n - 1);
        Integer result =  task1.compute() + task.join();
        System.out.println(String.format("[Thread-%s] - %s : %s", threadId.get(), "RegularRecursiveTask - end", result));
        return result;
    }
}
