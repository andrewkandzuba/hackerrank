package org.hackerrank.java.interview.jcp.monitor;

class Lock {
    private final Object GUARD = new Object();
    private volatile boolean completed = false;
    private int counter = 0;

    int getCounter() {
        return counter;
    }

    void waitFor() throws InterruptedException {
        synchronized (GUARD) {
            while (!completed) {
                GUARD.wait();
            }
        }
    }

    void notifyComplete() {
        synchronized (GUARD) {
            counter += 1;
            completed = true;
            GUARD.notifyAll();
        }
    }
}
