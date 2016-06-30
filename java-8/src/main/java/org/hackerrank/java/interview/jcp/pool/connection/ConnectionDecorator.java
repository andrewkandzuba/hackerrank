package org.hackerrank.java.interview.jcp.pool.connection;

import org.hackerrank.java.interview.jcp.cache.Pool;

import java.io.IOException;

public class ConnectionDecorator implements Connection {
    private final Pool<Connection> pool;
    private final Connection connection;

    public ConnectionDecorator(Pool<Connection> pool) throws InterruptedException {
        this.pool = pool;
        this.connection = pool.take();
    }

    @Override
    public void close() throws IOException {
        try {
            pool.release(connection);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public boolean isExpired() {
        return connection.isExpired();
    }

    @Override
    public int getId() {
        return connection.getId();
    }

    @Override
    @SuppressWarnings("unchecked")
    public int compareTo(Object o) {
        return connection.compareTo(o);
    }
}

