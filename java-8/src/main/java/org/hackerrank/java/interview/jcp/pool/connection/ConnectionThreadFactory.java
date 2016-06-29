package org.hackerrank.java.interview.jcp.pool.connection;

import java.util.concurrent.ThreadFactory;

public class ConnectionThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        return new DaemonThread(r);
    }

    private static class DaemonThread extends Thread {
        private DaemonThread(Runnable r) {
            super(r);
            this.setDaemon(true);
        }
    }

}
