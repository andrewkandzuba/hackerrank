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

    @Test
    public void testFindInorderSuccessor() throws Exception {
        BTree t = root(15)
                .left(root(10).left(root(5).left(1).right(root(7).right(8))))
                .right(root(20).left(17).right(25));

        Assert.assertTrue(t.bst());

        Assert.assertEquals(15, t.find(15).v());
        Assert.assertEquals(10, t.find(10).v());
        Assert.assertEquals(25, t.find(25).v());
        Assert.assertNull(t.find(27));
        Assert.assertNull(t.find(0));

        Assert.assertEquals(5, t.inorderSuccessorOf(1).v());
        Assert.assertEquals(10, t.inorderSuccessorOf(8).v());
        Assert.assertEquals(17, t.inorderSuccessorOf(15).v());
        Assert.assertNull(t.inorderSuccessorOf(25));
        Assert.assertNull(t.inorderSuccessorOf(100));
        Assert.assertNull(t.inorderSuccessorOf(-1));


        t = root(15);
        Assert.assertNull(t.inorderSuccessorOf());
        t.left(10);
        Assert.assertNull(t.inorderSuccessorOf());
        Assert.assertEquals(15, t.inorderSuccessorOf(10).v());
        t.right(root(20).left(17).left(12));
        Assert.assertEquals(12, t.inorderSuccessorOf().v());
    }

    @Test
    public void testFindFirstCommonAccessor() throws Exception {
        BTree t = root(15)
                .left(root(10).left(root(5).left(1).right(root(7).right(8))))
                .right(root(20).left(17).right(25));

        Assert.assertEquals(15, t.fistCommonAncestorOf(8, 17).v());
        Assert.assertEquals(5, t.fistCommonAncestorOf(1, 8).v());
    }

    @Test
    public void testAllPathsOfSum() throws Exception {
        BTree t = root(15)
                .left(root(10).left(root(5).left(1).right(root(7).right(8))))
                .right(root(20).left(17).right(25));

        t.printAllPathsOfSum(15);
        t.printAllPathsOfSum(25);
        t.printAllPathsOfSum(8);
    }
}
