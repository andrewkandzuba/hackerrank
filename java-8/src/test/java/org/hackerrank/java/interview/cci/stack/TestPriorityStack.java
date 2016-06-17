package org.hackerrank.java.interview.cci.stack;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class TestPriorityStack {

    @Test
    public void testPriorityStack() throws Exception {
        Stack<Integer> stack = new PriorityStack<>();
        stack.push(10);
        stack.push(2);
        stack.push(11);
        stack.push(3);

        Assert.assertEquals(11, stack.pop().intValue());
        Assert.assertEquals(10, stack.pop().intValue());
        Assert.assertEquals(3, stack.pop().intValue());
        Assert.assertEquals(2, stack.pop().intValue());
    }

    @Test
    public void testLinkedList() throws Exception {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.push(2);
        linkedList.push(1);
        linkedList.push(5);
        linkedList.push(3);

        Iterator<Integer> it = linkedList.descendingIterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }
}
