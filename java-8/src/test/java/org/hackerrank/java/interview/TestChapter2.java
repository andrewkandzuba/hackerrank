package org.hackerrank.java.interview;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;

public class TestChapter2 {
    private final static Logger LOGGER = Logger.getLogger(TestChapter2.class.getName());
    private final static int LIST_LENGTH = 10000;

    @Test
    public void testRemoveDuplicatesFromUnsortedLinkedList() throws Exception {
        AtomicReference<SinglyNode<Integer>> reference = new AtomicReference<>();
        new Random().ints(LIST_LENGTH, 0, 10).forEach(value -> {
            SinglyNode<Integer> node = reference.get();
            if (reference.get() == null) {
                reference.set(new SinglyNode<>(value));
            } else {
                node.appendToTail(value);
            }
        });
        LOGGER.info(reference.get().toString());
        removeDuplicates(reference.get());
        LOGGER.info(reference.get().toString());
        Assert.assertTrue(checkNoDuplicates(reference.get()));

        reference.set(null);
        new Random().ints(LIST_LENGTH, 0, 10).forEach(value -> {
            SinglyNode<Integer> node = reference.get();
            if (reference.get() == null) {
                reference.set(new SinglyNode<>(value));
            } else {
                node.appendToTail(value);
            }
        });
        LOGGER.info(reference.get().toString());
        removeDuplicatesWithSet(reference.get());
        LOGGER.info(reference.get().toString());
        Assert.assertTrue(checkNoDuplicates(reference.get()));
    }

    @Test
    public void testFindKthToLast() throws Exception {
        SinglyNode<Integer> node = new SinglyNode<>(0);
        for(int i = 1; i < 20; i++){
            node.appendToTail(i);
        }
        LOGGER.info(node.toString());
    }

    private void removeDuplicates(SinglyNode<Integer> node) {
        long timeStamp = System.currentTimeMillis();
        SinglyNode<Integer> current = node;
        while (current != null) {
            SinglyNode<Integer> runner = current;
            while (runner.getNext() != null) {
                if (current.getData().equals(runner.getNext().getData())) {
                    runner.setNext(runner.getNext().getNext());
                } else {
                    runner = runner.getNext();
                }
            }
            current = current.getNext();
        }
        long execTime = System.currentTimeMillis() - timeStamp;
        LOGGER.info(String.format("execTime: %s ms", execTime));
    }

    private void removeDuplicatesWithSet(SinglyNode<Integer> node) {
        long timeStamp = System.currentTimeMillis();
        Set<Integer> set = new HashSet<>();
        set.add(node.getData());
        SinglyNode<Integer> runner = node;
        while (runner.getNext() != null) {
            if (set.contains(runner.getNext().getData())) {
                runner.setNext(runner.getNext().getNext());
            } else {
                set.add(runner.getNext().getData());
                runner = runner.getNext();
            }
        }
        long execTime = System.currentTimeMillis() - timeStamp;
        LOGGER.info(String.format("execTime: %s ms", execTime));
    }

    private boolean checkNoDuplicates(SinglyNode<Integer> node){
        Set<Integer> set = new HashSet<>();
        SinglyNode<Integer> runner = node;
        while (runner != null) {
            if (!set.contains(runner.getData())) {
                set.add(runner.getData());
                runner = runner.getNext();
            } else {
                return false;
            }
        }
        return true;
    }
}
