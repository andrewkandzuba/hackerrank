package org.hackerrank.java.interview.cci.trees;

import org.junit.Assert;
import org.junit.Test;

public class TestBTrees {

    @Test
    public void testTreeHeight() throws Exception {
        Node<Integer> root = new Node<>(0,
                new Node<>(1, new Node<>(2, null, null), new Node<>(3, null, null)),
                new Node<>(4, new Node<>(5, null, null), new Node<>(6, null, null)));
        root.print();
        System.out.println("height=" + root.height());
        Assert.assertTrue(root.isBalanced());

        root = new Node<>(0,
                new Node<>(1, new Node<>(2, null, null), new Node<>(3, null, null)),
                new Node<>(4, null, null));
        root.print();
        System.out.println("height=" + root.height());
        Assert.assertTrue(root.isBalanced());

        root = new Node<>(0,
                new Node<>(1, new Node<>(2, new Node<>(7, new Node<>(8, null, null), null), null), new Node<>(3, null, null)),
                new Node<>(4, new Node<>(5, null, null), new Node<>(6, null, null)));
        root.print();
        System.out.println("height=" + root.height());
        Assert.assertFalse(root.isBalanced());


        root = new Node<>(0,
                new Node<>(1, new Node<>(2, null, null), new Node<>(3, null, null)),
                new Node<>(4, new Node<>(5, null, null), null));
        root.print();
        System.out.println("height=" + root.height());
        Assert.assertTrue(root.isBalanced());

    }
}
