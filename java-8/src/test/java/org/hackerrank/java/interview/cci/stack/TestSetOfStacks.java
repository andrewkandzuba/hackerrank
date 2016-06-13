package org.hackerrank.java.interview.cci.stack;

import org.junit.Assert;
import org.junit.Test;

import java.security.InvalidParameterException;
import java.util.Stack;

public class TestSetOfStacks {

    @Test
    public void testCompareWithRegularStack() throws Exception {
        Stack<Integer> stack = new Stack<>();
        SetOfStacks<Integer> setOfStacks = new SetOfStacks<>(2);

        for (int i = 0; i < 10; i++) {
            stack.push(i);
            setOfStacks.push(i);
        }

        for(int i = 0; i < 10; i++){
            Assert.assertEquals(stack.pop(), setOfStacks.pop());
        }
    }

    @Test(expected = InvalidParameterException.class)
    public void testPopAt() throws Exception {
        SetOfStacks<Integer> setOfStacks = new SetOfStacks<>(2);
        setOfStacks.push(1);
        setOfStacks.push(2);
        setOfStacks.push(3);
        Assert.assertEquals(2, setOfStacks.popAt(0).intValue());
        setOfStacks.popAt(3);
    }
}
