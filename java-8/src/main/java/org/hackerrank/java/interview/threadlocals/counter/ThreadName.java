package org.hackerrank.java.interview.threadlocals.counter;

import java.util.Random;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadName extends Thread {

    private static ThreadLocal<Integer> localCounter = ThreadLocal.withInitial(() -> 0);
    private final static Random random = new Random(1024);

    @Override
    public void run() {
        try {
            for(int i = 0; i < random.nextInt(); i++){
                System.out.println("I'm " + Thread.currentThread().getName() + " and I've built " + localCounter.get() + " of bricks");
                localCounter.set(localCounter.get() + 1);
            }
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String... args){
        ThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 2, new ThreadCounterFactory());
        for(int i = 0; i < 100; i++){
            executor.submit(new ThreadName());
        }
        try{
            executor.shutdown();
            executor.awaitTermination(5000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e){
            e.printStackTrace();
            executor.shutdownNow();
        }
    }
}
