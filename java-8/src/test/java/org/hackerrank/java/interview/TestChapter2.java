package org.hackerrank.java.interview;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;
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
        reference.get().removeDuplicates();
        LOGGER.info(reference.get().toString());
        Assert.assertTrue(reference.get().checkNoDuplicates());

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
        reference.get().removeDuplicatesWithSet();
        LOGGER.info(reference.get().toString());
        Assert.assertTrue(reference.get().checkNoDuplicates());
    }

    @Test
    public void testFindKthToLast() throws Exception {
        SinglyNode<Integer> node = new SinglyNode<>(0);
        for (int i = 1; i < 20; i++) {
            node.appendToTail(i);
        }
        LOGGER.info(node.toString());
        // find k(th) from the end
        Assert.assertEquals(node.findFromEnd(3).getData().intValue(), 17);
        Assert.assertEquals(node.findFromEnd(18).getData().intValue(), 2);
        try {
            node.findFromEnd(23);
            Assert.assertFalse(true);
        } catch (Exception e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDeleteInMiddle() throws Exception {
        SinglyNode<Integer> node = new SinglyNode<>(0);
        Assert.assertNull(SinglyNode.deleteInMiddle(node, node));
        Assert.assertEquals(node, SinglyNode.deleteInMiddle(node, null));
        for (int i = 1; i < 2; i++) {
            node.appendToTail(i);
        }
        LOGGER.info(String.format("%15s", "="));
        LOGGER.info(node.toString());
        LOGGER.info(SinglyNode.deleteInMiddle(node, new SinglyNode<Integer>(1)).toString());

        node = new SinglyNode<>(0);
        for (int i = 1; i < 20; i++) {
            node.appendToTail(i);
        }
        LOGGER.info(String.format("%15s", "="));
        LOGGER.info(node.toString());
        LOGGER.info(SinglyNode.deleteInMiddle(node, new SinglyNode<Integer>(15)).toString());

        node = new SinglyNode<>(0);
        for (int i = 1; i < 20; i++) {
            node.appendToTail(i);
        }
        LOGGER.info(String.format("%15s", "="));
        LOGGER.info(node.toString());
        LOGGER.info(SinglyNode.deleteInMiddle(node, new SinglyNode<Integer>(19)).toString());

        node = new SinglyNode<>(0);
        for (int i = 1; i < 20; i++) {
            node.appendToTail(i);
        }
        LOGGER.info(String.format("%15s", "="));
        LOGGER.info(node.toString());
        LOGGER.info(SinglyNode.deleteInMiddle(node, new SinglyNode<Integer>(100)).toString());
    }
}
