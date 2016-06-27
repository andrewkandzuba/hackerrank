package org.hackerrank.java.interview.jcp.pool.connection;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnectionReal implements Connection {
    private static final Logger logger  = LoggerFactory.getLogger(ConnectionReal.class);
    private static final AtomicInteger counter = new AtomicInteger();

    private final long created;
    private final int id;
    private volatile boolean isClosed;

    public ConnectionReal() {
        this.created = System.currentTimeMillis();
        this.id = counter.incrementAndGet();
        this.isClosed = false;
        logger.info(String.format("Open new connection. Total opened = %s", id));
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
        logger.info(String.format("Close existing connection. Total opened = %s", id));
    }
}
