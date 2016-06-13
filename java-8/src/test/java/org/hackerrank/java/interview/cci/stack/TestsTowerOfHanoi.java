package org.hackerrank.java.interview.cci.stack;

import org.junit.Assert;
import org.junit.Test;

public class TestsTowerOfHanoi {

    @Test
    public void testSolution() throws Exception {
        TowersOfHanoi towersOfHanoi = new TowersOfHanoi(16);
        towersOfHanoi.solve();
        Assert.assertTrue(towersOfHanoi.getTowers().get(0).isEmpty());
        Assert.assertTrue(towersOfHanoi.getTowers().get(1).isEmpty());
        int prev = towersOfHanoi.getTowers().get(2).peek();
        for(int i = 0; i < towersOfHanoi.getTowers().get(2).size(); i++){
            int curr = towersOfHanoi.getTowers().get(2).pop();
            Assert.assertTrue(prev <= curr);
            prev = curr;
        }
    }
}
