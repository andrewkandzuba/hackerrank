package org.hackerrank.java.interview.jcp.interruption;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadQueuePutter {

    public static void main(String... args) throws InterruptedException {
        final BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(1);

        final Thread putter = new Thread(() -> {
            try {
                System.out.println("Putter thread interrupted status is : " + Thread.currentThread().isInterrupted());
                System.out.println("Putter thread state is : " + Thread.currentThread().getState());
                queue.put(0);
                queue.put(1);
            } catch (InterruptedException e) {
                System.out.println("Putter thread interrupted status inside InterruptedException block is : " + Thread.currentThread().isInterrupted());
                System.out.println("Putter thread state inside InterruptedException block is : " + Thread.currentThread().getState());
                Thread.currentThread().interrupt();
                System.out.println("Putter thread interrupted status inside InterruptedException block is : " + Thread.currentThread().isInterrupted());
                System.out.println("Putter thread state inside InterruptedException block is : " + Thread.currentThread().getState());
            }
        });

        final Thread putterMonitor = new Thread(() -> {
            while (putter.isAlive()) {
                try {
                    System.out.println("Putter thread interrupted status is : " + putter.isInterrupted());
                    System.out.println("Putter thread state is : " + putter.getState());
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    return;
                }
            }
        });

        putter.start();
        putterMonitor.start();
        Thread.sleep(5000);
        putter.interrupt();
    }
}
