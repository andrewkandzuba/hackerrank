package org.hackerrank.java.interview.stardev;

import org.junit.Assert;
import org.junit.Test;

import static org.hackerrank.java.interview.stardev.Array3.*;

public class TestArray3 {
    @Test
    public void testMaxSpan() throws Exception {
        Assert.assertEquals(maxSpan(new int[]{1, 2, 1, 1, 3}), 4);
        Assert.assertEquals(maxSpan(new int[]{1, 4, 2, 1, 4, 1, 4}), 6);
        Assert.assertEquals(maxSpan(new int[]{1, 4, 2, 1, 4, 4, 4}), 6);
    }
}
