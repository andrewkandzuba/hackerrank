package org.hackerrank.java.interview.misc;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;

/**
 * Question: 2.4
 */
public class TestChapter2_1 {
    private final static Logger LOGGER = Logger.getLogger(TestChapter2.class.getName());
    private final static int LIST_LENGTH = 10000;

    @Test
    public void testPartitioning() throws Exception {
        for(int i = 0; i < 1000; i++) {
            AtomicReference<DoublyNode<Integer>> reference = new AtomicReference<>();
            new Random().ints(20, 0, 10).forEach(value -> {
                DoublyNode<Integer> node = reference.get();
                if (reference.get() == null) {
                    reference.set(new DoublyNode<>(value));
                } else {
                    node.appendToTail(value);
                }
            });
            //LOGGER.info(reference.get().toString());
            partitionAround(reference.get(), 5);
        }
    }

    private <T extends Comparable<? super T>> void partitionAround(DoublyNode<T> start, T x) {
        if (start == null || start.getNext() == null) {
            return;
        }
        DoublyNode<T> fl = null;
        DoublyNode<T> fe = null;
        DoublyNode<T> fg = null;

        DoublyNode<T> current = start;
        DoublyNode<T> runner = start.getNext();
        while (current != null) {
            if (current.getData().compareTo(x) < 0) {
                if (fl == null) {
                    fl = current;
                    current.setPrev(null);
                    current.setNext(null);
                } else {
                    fl.appendToTail(current);
                    current.setPrev(fl);
                    current.setNext(null);
                }
            } else if (current.getData().compareTo(x) == 0) {
                if (fe == null) {
                    fe = current;
                    current.setPrev(null);
                    current.setNext(null);
                } else {
                    fe.appendToTail(current);
                    current.setPrev(fe);
                    current.setNext(null);
                }
            } else if (current.getData().compareTo(x) > 0) {
                if (fg == null) {
                    fg = current;
                    current.setPrev(null);
                    current.setNext(null);
                } else {
                    fg.appendToTail(current);
                    current.setPrev(fg);
                    current.setNext(null);
                }
            }
            current = runner;
            if (runner != null) {
                runner = runner.getNext();
            }
        }
        start = fl;
        if(start!=null){
            start.appendToTail(fe);
        } else {
            start = fe;
        }
        if(start!=null){
            start.appendToTail(fg);
        } else {
            start = fg;
        }
        if(start != null) {
            LOGGER.info(start.toString());
        }
    }

    private <T extends Comparable<? super T>> void swapNodes(DoublyNode<T> from, DoublyNode<T> to) {
        T from_to = to.getData();
        to.setData(from.getData());
        from.setData(from_to);
    }
}
