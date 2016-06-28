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
        this.id = counter.getAndIncrement();
        this.isClosed = false;
        logger.info(String.format("Open new connection - %s", id));
    }

    @Override
    public boolean isValid() {
        return (System.currentTimeMillis() - created < 300 * 1000 && !isClosed);
    }

    public int getId() {
        return id;
    }

    @Override
    public void close() throws IOException {
        isClosed = true;
        logger.info(String.format("Close existing connection - %s", id));
    }

    @Override
    public int compareTo(Object o) {
        ConnectionReal cr = (ConnectionReal) o;
        if(this.created > cr.created){
            return -1;
        } else if(this.created < cr.created){
            return 1;
        } else {
            return 0;
        }
    }
}
