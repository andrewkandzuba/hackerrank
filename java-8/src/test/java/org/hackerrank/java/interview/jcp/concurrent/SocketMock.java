package org.hackerrank.java.interview.jcp.concurrent;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

class SocketMock implements Closeable {
    private volatile boolean closed = false;

    @Override
    public void close() throws IOException {
        closed = true;
    }

    int read(byte[] buffer) throws SocketMockException {
        int read = 0;
        boolean interrupted = false;
        try {
            while (!closed) {
                System.out.println("Emulates long operation");
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException ignored) {
                    interrupted = true;
                }
            }
            if (closed) {
                System.out.println("Socket is closed abruptly");
                throw new SocketMockException("Socket is closed abruptly");
            }
            return read;
        } finally {
            if(interrupted){
                Thread.currentThread().interrupt();
            }
        }
    }
}
