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
        AtomicReference<SinglyNode<Integer>> reference = new AtomicReference<>();
        new Random().ints(20, 0, 10).forEach(value -> {
            SinglyNode<Integer> node = reference.get();
            if (reference.get() == null) {
                reference.set(new SinglyNode<>(value));
            } else {
                node.appendToTail(value);
            }
        });
        LOGGER.info(reference.get().toString());
        partitionAround(reference.get(), 5);
        LOGGER.info(reference.get().toString());
    }

    private <T extends Comparable<? super T>> void partitionAround(SinglyNode<T> start, T x) {
        if (start == null || start.getNext() == null) {
            return;
        }
        SinglyNode<T> runner = start;
        SinglyNode<T> current = start;
        while (runner.getData().compareTo(x) < 0) {
            current = runner;
            runner = runner.getNext();
            if(runner == null){
                break;
            }
        }
        while (runner != null) {
            if(runner.getData().compareTo(x) < 0) {
                SinglyNode<T> exchange = runner;
                current.setNext(runner.getNext());
                runner = runner.getNext();
                exchange.setNext(start.getNext());
                start.setNext(exchange);
            } else {
                runner = runner.getNext();
                current = current.getNext();
            }
        }
    }
}
