package org.hackerrank.java.interview.jcp.pool;

public class PoolableRetrieveException extends Exception {
    public PoolableRetrieveException() {
    }

    public PoolableRetrieveException(String message) {
        super(message);
    }

    public PoolableRetrieveException(String message, Throwable cause) {
        super(message, cause);
    }
}
