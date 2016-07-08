package org.hackerrank.java.interview.cci.trees;

import org.junit.Assert;
import org.junit.Test;

import static org.hackerrank.java.interview.cci.trees.BTree.root;

public class TestBTrees {

    @Test
    public void testTreeHeight() throws Exception {
        BTree t = root(0).left(root(1).left(2).right(3)).right(root(4).left(5).right(6));
        t.print();
        System.out.println("height=" + t.height());
        Assert.assertTrue(t.balanced());
        Assert.assertFalse(t.bst());

        t = root(0).left(root(1).left(2).right(3)).right(4);
        t.print();
        System.out.println("height=" + t.height());
        Assert.assertTrue(t.balanced());
        Assert.assertFalse(t.bst());

        t = root(0)
                .left(root(1)
                        .left(root(2)
                                .left(root(7)
                                        .left(8)))
                        .right(3))
                .right(root(4).left(5).right(6));
        t.print();
        System.out.println("height=" + t.height());
        Assert.assertFalse(t.balanced());
        Assert.assertFalse(t.bst());

        t = root(0)
                .left(root(1)
                        .left(2).right(3))
                .right(root(4)
                        .left(5));
        t.print();
        System.out.println("height=" + t.height());
        Assert.assertTrue(t.balanced());
        Assert.assertFalse(t.bst());
    }

    @Test
    public void testBST() throws Exception {
        BTree t = root(10).left(7).right(11);
        Assert.assertTrue(t.bst());

        t = root(10).left(7);
        Assert.assertTrue(t.bst());

        t = root(13).left(12).right(11);
        Assert.assertFalse(t.bst());

        t = root(13).left(12).right(15);
        Assert.assertTrue(t.bst());

        t = root(13).left(12).right(root(15).left(11).right(22));
        Assert.assertFalse(t.bst());

        t = root(100).left(root(80).left(60).right(120)).right(120);
        Assert.assertFalse(t.bst());

        t = root(100).left(root(80).left(60).right(90)).right(120);
        Assert.assertTrue(t.bst());
    }
}
