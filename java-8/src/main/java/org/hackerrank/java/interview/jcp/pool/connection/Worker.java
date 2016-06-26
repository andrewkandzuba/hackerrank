package org.hackerrank.java.interview.jcp.pool.connection;


public abstract class Worker implements Runnable {
    private static final ThreadLocal<Connection> connection = new ThreadLocal<>();

    @Override
    public void run() {
        handle(connection.get());
    }

    protected abstract void handle(Connection c);
}
