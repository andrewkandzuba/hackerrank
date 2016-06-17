package org.hackerrank.java.interview.cci.stack;

import org.junit.Assert;
import org.junit.Test;

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
}
