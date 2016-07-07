package org.hackerrank.java.interview.cci.graph;

import org.hackerrank.java.interview.cci.trees.BTree;
import org.junit.Test;

public class TestBinaryTree {

    @Test
    public void testBST() throws Exception {
        BTree tree1 = BTree.builder().of(1)
                .left(BTree.builder().of(2).build())
                .right(BTree.builder().of(3).build())
                .build();

        BTree tree2 = BTree.builder().of(1)
                .left(null)
                .right(BTree.builder().of(3).build())
                .build();

        BTree tree3 = BTree.builder().of(1)
                .left(BTree.builder().of(2).build())
                .right(null)
                .build();
    }
}
