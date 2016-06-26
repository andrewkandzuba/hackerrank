package org.hackerrank.java.interview.jcp.pool.connection;


import org.hackerrank.java.interview.jcp.pool.Poolable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class Connection implements Poolable {
    private static final Logger logger  = LoggerFactory.getLogger(Connection.class);
    private static final AtomicInteger counter = new AtomicInteger();

    private final long created;
    private final int id;
    private volatile boolean isClosed;

    public Connection() {
        this.created = System.currentTimeMillis();
        this.id = counter.incrementAndGet();
        this.isClosed = false;

        logger.info(String.format("Opens new connection. Total opened = %s", id));

    }

    @Override
    public boolean isValid() {
        return (System.currentTimeMillis() - created > 300 * 1000 && !isClosed);
    }

    public int getId() {
        return id;
    }

    @Override
    public void close() throws IOException {
        isClosed = true;
        logger.info(String.format("Closes existing connection. Total opened = %s", id));
    }
}
