package org.hackerrank.java.interview.cci.stack;

import org.junit.Assert;
import org.junit.Test;

public class TestMinStack {

    @Test
    public void testMinStackOperations() throws Exception {
        MinStack<Integer> stack = new MinStack<>();
        Assert.assertNull(stack.min());

        stack.push(3);
        Assert.assertEquals(3, stack.min().intValue());

        stack.push(2);
        Assert.assertEquals(2, stack.min().intValue());

        stack.push(10);
        Assert.assertEquals(2, stack.min().intValue());

        stack.push(5);
        Assert.assertEquals(2, stack.min().intValue());

        stack.push(1);
        Assert.assertEquals(1, stack.min().intValue());

        stack.push(4);
        Assert.assertEquals(1, stack.min().intValue());

        stack.push(2);
        Assert.assertEquals(1, stack.min().intValue());

        stack.push(1);
        Assert.assertEquals(1, stack.min().intValue());

        Assert.assertEquals(1, stack.pop().intValue());
        Assert.assertEquals(1, stack.min().intValue());

        Assert.assertEquals(2, stack.pop().intValue());
        Assert.assertEquals(1, stack.min().intValue());

        Assert.assertEquals(4, stack.pop().intValue());
        Assert.assertEquals(1, stack.min().intValue());

        Assert.assertEquals(1, stack.pop().intValue());
        Assert.assertEquals(2, stack.min().intValue());

        Assert.assertEquals(5, stack.pop().intValue());
        Assert.assertEquals(2, stack.min().intValue());

        Assert.assertEquals(10, stack.pop().intValue());
        Assert.assertEquals(2, stack.min().intValue());

        Assert.assertEquals(2, stack.pop().intValue());
        Assert.assertEquals(3, stack.min().intValue());

        Assert.assertEquals(3, stack.pop().intValue());
        Assert.assertNull(stack.min());

        Assert.assertNull(stack.pop());
        Assert.assertNull(stack.min());
    }
}
