package org.hackerrank.java.interview.misc;

import java.util.ArrayList;

public class StackArray<T> extends ArrayList<T> {
    private final int[] segments;
    private final T[] array;

    @SuppressWarnings("unchecked")
    public StackArray(int number, int capacity) {
        this.segments = new int[number];
        this.array = (T[]) new Object[capacity];
    }

    public void push(T v, int to) throws Exception {
        if (segments.length - 1 > to) {
            throw new Exception("No such stack");
        }
        int segmentStart = to * (array.length / segments.length);
        int segmentNextTop = segmentStart + segments[to];
        segments[to] += 1;

    }
}
