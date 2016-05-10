package org.hackerrank.java.interview;

public class Heap<T extends Comparable<? super T>> {

    public T[] heapSort(T[] array) {
        // Nothing to sort
        if (array.length == 0 || array.length == 1) {
            return array;
        }
        // HeapSort
        buildHeap(0, array, array.length);
        int l = array.length;
        do {
            T v = array[l - 1];
            array[l - 1] = array[0];
            array[0] = v;
            reHeap(0, array, --l);
        } while (l > 0);
        return array;
    }

    private void buildHeap(int current, T[] array, int length) {
        if (current < length) {
            // build current
            exchangeValue(getParentIndex(current), current, array);
            // build left subtree
            buildHeap(getLeftChildIndex(current), array, length);
            // build right subtree
            buildHeap(getRightChildIndex(current), array, length);
        }
    }

    private void reHeap(int parent, T[] array, int length) {
        int leftChildIndex = getLeftChildIndex(parent);
        int rightChildIndex = getRightChildIndex(parent);
        if (leftChildIndex < length || rightChildIndex < length) {
            if (array[leftChildIndex].compareTo(array[parent]) > 0) {
                T v = array[leftChildIndex];
                array[leftChildIndex] = array[parent];
                array[parent] = v;
                reHeap(leftChildIndex, array, length);
            } else if (array[rightChildIndex].compareTo(array[parent]) > 0) {
                T v = array[rightChildIndex];
                array[rightChildIndex] = array[parent];
                array[parent] = v;
                reHeap(rightChildIndex, array, length);
            }
        }
    }

    private void exchangeValue(int parent, int current, T[] array) {
        if (parent >= 0 && current >= 0) {
            if (array[current].compareTo(array[parent]) > 0) {
                T v = array[current];
                array[current] = array[parent];
                array[parent] = v;
                exchangeValue(getParentIndex(parent), parent, array);
            }
        }
    }

    private static int getParentIndex(int i) {
        return ((i - 1) / 2);
    }

    private static int getLeftChildIndex(int i) {
        return 2 * i + 1;
    }

    private static int getRightChildIndex(int i) {
        return 2 * (i + 1);
    }
}
