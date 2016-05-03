package org.hackerrank.java.interview;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;

public class TestChapter2_1 {
    private final static Logger LOGGER = Logger.getLogger(TestChapter2.class.getName());
    private final static int LIST_LENGTH = 10000;

    @Test
    public void testPartitioning() throws Exception {
        AtomicReference<DoublyNode<Integer>> reference = new AtomicReference<>();
        new Random().ints(20, 0, 10).forEach(value -> {
            DoublyNode<Integer> node = reference.get();
            if (reference.get() == null) {
                reference.set(new DoublyNode<>(value));
            } else {
                node.appendToTail(value);
            }
        });
        LOGGER.info(reference.get().toString());
        partitionAround(reference.get(), 5);
        LOGGER.info(reference.get().toString());
    }

    private <T extends Comparable<? super T>> void partitionAround(DoublyNode<T> start, T x) {
        if (start == null || start.getNext() == null) {
            return;
        }
        DoublyNode<T> fl = null;
        DoublyNode<T> fe = null;
        DoublyNode<T> fg = null;
        DoublyNode<T> current = start;
        while (current != null){
            if(current.getData().compareTo(x) < 0){
               if(fl == null) {
                   fl = new DoublyNode<>(current.getData());
               } else {
                   fl.appendToTail(current.getData());
               }
            } else if (current.getData().compareTo(x) == 0){
                if(fe == null) {
                    fe = new DoublyNode<>(current.getData());;
                } else {
                    fe.appendToTail(current.getData());
                }
            } else if (current.getData().compareTo(x) > 0){
                if(fg == null) {
                    fg = new DoublyNode<>(current.getData());;
                } else {
                    fg.appendToTail(current.getData());
                }
            }
            current = current.getNext();
        }
        int i = 10;
    }

    private <T extends Comparable<? super T>> void swapNodes(DoublyNode<T> from, DoublyNode<T> to) {
        T from_to = to.getData();
        to.setData(from.getData());
        from.setData(from_to);
    }
}
