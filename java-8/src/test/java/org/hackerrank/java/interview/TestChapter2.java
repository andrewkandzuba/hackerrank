package org.hackerrank.java.interview;

import org.junit.Assert;
import org.junit.Test;

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
        for (int i = 1; i < 20; i++) {
            node.appendToTail(i);
        }
        LOGGER.info(node.toString());
        // find k(th) from the end
        Assert.assertEquals(findFromEnd(node, 3).getData().intValue(), 17);
        Assert.assertEquals(findFromEnd(node, 18).getData().intValue(), 2);
        try {
            findFromEnd(node, 23);
            Assert.assertFalse(true);
        } catch (Exception e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDeleteInMiddle() throws Exception {
        SinglyNode<Integer> node = new SinglyNode<>(0);
        Assert.assertNull(deleteInMiddle(node, node));
        Assert.assertEquals(node, deleteInMiddle(node, null));
        for (int i = 1; i < 2; i++) {
            node.appendToTail(i);
        }
        LOGGER.info(deleteInMiddle(node, new SinglyNode<>(1)).toString());

        node = new SinglyNode<>(0);
        for (int i = 1; i < 20; i++) {
            node.appendToTail(i);
        }
        LOGGER.info(node.toString());
        LOGGER.info(deleteInMiddle(node, new SinglyNode<>(15)).toString());

        node = new SinglyNode<>(0);
        for (int i = 1; i < 20; i++) {
            node.appendToTail(i);
        }
        LOGGER.info(node.toString());
        LOGGER.info(deleteInMiddle(node, new SinglyNode<>(19)).toString());

        node = new SinglyNode<>(0);
        for (int i = 1; i < 20; i++) {
            node.appendToTail(i);
        }
        LOGGER.info(node.toString());
        LOGGER.info(deleteInMiddle(node, new SinglyNode<>(100)).toString());
    }

    // Utility methods
    private <T> void removeDuplicates(SinglyNode<T> node) {
        long timeStamp = System.currentTimeMillis();
        SinglyNode<T> current = node;
        while (current != null) {
            SinglyNode<T> runner = current;
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

    private <T> void removeDuplicatesWithSet(SinglyNode<T> node) {
        long timeStamp = System.currentTimeMillis();
        Set<T> set = new HashSet<>();
        set.add(node.getData());
        SinglyNode<T> runner = node;
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

    private <T> boolean checkNoDuplicates(SinglyNode<T> node) {
        Set<T> set = new HashSet<>();
        SinglyNode<T> runner = node;
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

    private <T> SinglyNode<T> findFromEnd(SinglyNode<T> node, int k) throws Exception {
        SinglyNode<T> current = node;
        SinglyNode<T> runner = current;
        for (int i = 0; i < k; i++) {
            if (runner.getNext() != null) {
                runner = runner.getNext();
            } else {
                throw new Exception(String.format("%s node from the end not found!!!", k));
            }
        }
        while (runner != null) {
            current = current.getNext();
            runner = runner.getNext();
        }
        return current;
    }

    private <T> SinglyNode<T> deleteInMiddle(SinglyNode<T> start, SinglyNode<T> delete) throws Exception {
        if (delete == null || start == null) {
            return start;
        }
        if (start == delete) {
            return start.getNext();
        }
        SinglyNode<T> current = start;
        SinglyNode<T> runner = start.getNext();
        while (runner != null && !runner.getData().equals(delete.getData())) {
            current = runner;
            runner = runner.getNext();
        }
        if (runner != null) {
            current.setNext(runner.getNext());
        }
        return start;
    }
}
