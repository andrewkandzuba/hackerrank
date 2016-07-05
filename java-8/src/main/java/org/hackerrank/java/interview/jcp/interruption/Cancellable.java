package org.hackerrank.java.interview.jcp.interruption;

public abstract class Cancellable extends Thread {
    public final void cancel(){
        interrupt();
    }
}
