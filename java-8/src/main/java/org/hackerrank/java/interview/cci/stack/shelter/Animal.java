package org.hackerrank.java.interview.cci.stack.shelter;

public abstract class Animal {
    private final long timeArrived;
    private final String name;

    public Animal(String name) {
        this.name = name;
        this.timeArrived = System.currentTimeMillis();
    }

    public long getTimeArrived() {
        return timeArrived;
    }

    public String getName() {
        return name;
    }
}
