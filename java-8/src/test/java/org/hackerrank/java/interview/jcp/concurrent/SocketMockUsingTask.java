package org.hackerrank.java.interview.jcp.concurrent;

import org.hackerrank.java.interview.jcp.interruption.concurrent.CancellableTask;

import java.util.concurrent.RunnableFuture;

public abstract class SocketMockUsingTask implements CancellableTask<SocketMock> {
    private SocketMock socketMock;

    public synchronized void setSocketMock(SocketMock socketMock) {
        this.socketMock = socketMock;
    }

    @Override
    public synchronized void cancel() {

    }

    @Override
    public RunnableFuture<SocketMock> newTask() {
        return null;
    }
}
