package org.hackerrank.java.interview.cci.stack;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class TestMyQueue {

    @Test
    public void testTwoStackQueue() throws Exception {
        Queue<Integer> regularQueue = new LinkedList<>();
        MyQueue<Integer> myQueue = new MyQueue<>();

        for(int i = 0; i < 10; i++){
            regularQueue.add(i);
            myQueue.enqueue(i);
        }

        for(int i =0; i < 10; i++){
            Assert.assertEquals(regularQueue.poll(), myQueue.dequeue());
        }
    }
}
