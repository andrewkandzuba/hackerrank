package org.hackerrank.java.interview.jcp.pool.connection;

import org.hackerrank.java.interview.jcp.pool.Pool;

import java.io.IOException;

public class ConnectionDecorator implements Connection {
    private static final Pool<ConnectionReal> pool = new Pool<>(ConnectionReal::new);
    private final ConnectionReal connection;

    public ConnectionDecorator() {
        this.connection = pool.issue();
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
}
