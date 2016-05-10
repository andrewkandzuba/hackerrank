package org.hackerrank.java.interview;

import org.junit.Test;

public class TestHeapSort {
    private final static int MAX_ARRAY_LENGTH = 10;

    @Test
    public void testHeapSort() throws Exception {
        // input data
        Integer[] array = {98, 23, 1, 98, 45, 67, 1, 4, 89, 0};
       /* Integer[] array = new Integer[MAX_ARRAY_LENGTH];
        Random randSequence = new Random();
        for (int i = 0; i < MAX_ARRAY_LENGTH; i++) {
            array[i] = randSequence.nextInt(100);
            System.out.print(array[i] + ",");
        }*/
        System.out.println();

        // build a tree
        Heap<Integer> heap = new Heap<>();
        array = heap.heapSort(array);
        for (int i = 0; i < MAX_ARRAY_LENGTH; i++) {
            System.out.print(array[i] + ",");
        }
    }
}
