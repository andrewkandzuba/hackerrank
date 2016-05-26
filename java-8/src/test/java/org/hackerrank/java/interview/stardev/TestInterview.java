package org.hackerrank.java.interview.stardev;


import org.junit.Assert;
import org.junit.Test;

import static org.hackerrank.java.interview.stardev.Interview.*;

public class TestInterview {

    @Test
    public void testStringSplosion() throws Exception {
        Assert.assertEquals(stringSplosion("Code"), "CCoCodCode");
        Assert.assertEquals(stringSplosion("abc"), "aababc");
        Assert.assertEquals(stringSplosion("ab"), "aab");
    }


    @Test
    public void testMaxMirror() throws Exception {
        Assert.assertEquals(maxMirror(new int[]{1, 2, 3, 8, 9, 3, 2, 1}), 3);
        Assert.assertEquals(maxMirror(new int[]{1, 2, 1, 4}), 3);
        Assert.assertEquals(maxMirror(new int[]{7, 1, 2, 9, 7, 2, 1}), 2);
        Assert.assertEquals(maxMirror(new int[]{}), 0);
        Assert.assertEquals(maxMirror(new int[]{1}), 1);
        Assert.assertEquals(maxMirror(new int[]{0,1,0,0,0,0,1,0}), 8);
    }
}
