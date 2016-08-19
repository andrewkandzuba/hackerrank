package org.hackerrank.java.interview.jcp.bytecode;

public class Monitor {
    private int i;

    public synchronized void intrinsicMethod(){
        i++;
    }

    public void syncBlockMethod() {
        synchronized (this) {
            i++;
        }
    }
}
