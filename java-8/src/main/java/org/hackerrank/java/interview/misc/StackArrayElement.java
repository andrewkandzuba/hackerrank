package org.hackerrank.java.interview.misc;

public class StackArrayElement {
    private int index;
    private int capacity;
    private int topIndex;

    public StackArrayElement(int index, int capacity) {
        this.index = index;
        this.topIndex = index - 1;
        this.capacity = capacity;
    }
}
