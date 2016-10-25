package org.hackerrank.java.interview.jcp.monitor;


public class LockTest {

    public static void main(String... args) throws InterruptedException {
        Lock lock = new Lock();
        new Thread(lock::notifyComplete).start();
        new Thread(() -> {
            try {
                lock.waitFor();
                System.out.println(lock.getCounter());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
