package org.hackerrank.java.interview.jcp.concurrent;

public class SocketMockException extends Exception {
    public SocketMockException() {
    }

    public SocketMockException(String message) {
        super(message);
    }

    public SocketMockException(String message, Throwable cause) {
        super(message, cause);
    }

    public SocketMockException(Throwable cause) {
        super(cause);
    }

    public SocketMockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
