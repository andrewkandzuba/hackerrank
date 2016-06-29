package org.hackerrank.java.interview.jcp.pool.connection;

import org.hackerrank.java.interview.jcp.pool.Pool;

import java.io.IOException;

public class ConnectionDecorator implements Connection {
    private final Pool<Connection> pool;
    private final Connection connection;

    public ConnectionDecorator(Pool<Connection> pool) {
        this.pool = pool;
        try {
            this.connection = pool.issue();
        } catch (InterruptedException e) {
            throw new IllegalStateException("Unable to instantiate connection", e);
        }
    }

    @Override
    public void close() throws IOException {
        pool.release(connection);
    }

    @Override
    public boolean isValid() {
        return connection.isValid();
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

