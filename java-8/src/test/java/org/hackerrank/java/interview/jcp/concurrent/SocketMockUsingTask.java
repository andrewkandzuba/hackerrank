package org.hackerrank.java.interview.jcp.concurrent;

import org.hackerrank.java.interview.jcp.interruption.concurrent.CancellableTask;

import java.io.IOException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

abstract class SocketMockUsingTask<T> implements CancellableTask<T> {
    private SocketMock socketMock;

    synchronized void setSocketMock(SocketMock socketMock) {
        this.socketMock = socketMock;
    }

    @Override
    public synchronized void cancel() {
        try {
            if (socketMock != null) {
                socketMock.close();
            }
        } catch (IOException ignored) {}
    }

    @Override
    public RunnableFuture<T> newTask() {
        return new FutureTask<T>(this) {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                boolean isCancelled;
                try {
                    SocketMockUsingTask.this.cancel();
                } finally {
                    isCancelled = super.cancel(mayInterruptIfRunning);
                }
                return isCancelled;
            }
        };
    }
}
