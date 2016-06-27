package org.hackerrank.java.interview.jcp.pool.connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.ThreadFactory;

public class ConnectionThreadFactory implements ThreadFactory {
    private static final Logger logger  = LoggerFactory.getLogger(ConnectionThreadFactory.class);
    private final static ThreadLocal<Connection> CONNECTION_THREAD_LOCAL = new ThreadLocal<>();
    public static ThreadFactory createThreadFactory(){
        return new ConnectionThreadFactory();
    }
    public static Connection getConnection(){
        return CONNECTION_THREAD_LOCAL.get();
    }

    private ConnectionThreadFactory() {
    }

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(){
            @Override
            public void run() {
                try(Connection c = new ConnectionDecorator()) {
                    logger.info(String.format("Start new executor's thread with connection: %s", c.getId()));
                    CONNECTION_THREAD_LOCAL.set(c);
                    r.run();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    CONNECTION_THREAD_LOCAL.remove();
                    logger.info("Finish executor's thread");
                }
            }
        };
    }
}
