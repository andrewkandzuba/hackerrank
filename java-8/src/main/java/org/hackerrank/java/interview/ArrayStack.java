package org.hackerrank.java.interview;

import java.util.ArrayList;

public class ArrayStack<T> extends ArrayList<T> {
    private final int[] partitions;
    private final ArrayList<T> array;

    public ArrayStack(ArrayList<T> array, int count) {
        this.partitions = new int[count];
        this.array = array;
    }

    public void pushTo(T v, int partition) throws Exception {
        if(partitions.length > partition){
            throw new Exception("Wrong stack index");
        }

    }
    
    private int reserveNextIndex(int partition){
        int nextFreeIndex = 0;
        for(int index : partitions){
            if(nextFreeIndex < index){
                nextFreeIndex = index;
            }
        }
        this.partitions[partition] = nextFreeIndex;
        return nextFreeIndex;
    }
}
