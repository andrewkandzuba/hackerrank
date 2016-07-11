package org.hackerrank.java.interview.jcp.concurrent;

import java.io.Closeable;
import java.io.IOException;

public class SocketMock implements Closeable {
    private volatile boolean closed = false;

    public boolean isOpened() {
        return !closed;
    }

    @Override
    public void close() throws IOException {
         closed = true;
    }
}
