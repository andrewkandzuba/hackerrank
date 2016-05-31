package org.hackerrank.java.interview.stardev;

import org.junit.Assert;
import org.junit.Test;

import static org.hackerrank.java.interview.stardev.Array3.*;

public class TestArray3 {
    @Test
    public void testMaxSpan() throws Exception {
        Assert.assertEquals(4, maxSpan(new int[]{1, 2, 1, 1, 3}));
        Assert.assertEquals(6, maxSpan(new int[]{1, 4, 2, 1, 4, 1, 4}));
        Assert.assertEquals(6, maxSpan(new int[]{1, 4, 2, 1, 4, 4, 4}));
    }

    @Test
    public void testFix34() throws Exception {
        Assert.assertArrayEquals(fix34(new int[]{1, 3, 1, 4}), new int[]{1, 3, 4, 1});
        Assert.assertArrayEquals(fix34(new int[]{1, 3, 1, 4, 4, 3, 1}), new int[]{1, 3, 4, 1, 1, 3, 4});
        Assert.assertArrayEquals(fix34(new int[]{3, 2, 2, 4}),new int[]{3, 4, 2, 2});

        Assert.assertArrayEquals(fix34(new int[]{3, 2, 3, 2, 4, 4}), new int[]{3, 4, 3, 4, 2, 2});
        Assert.assertArrayEquals(fix34(new int[]{2, 3, 2, 3, 2, 4, 4}), new int[]{2, 3, 4, 3, 4, 2, 2});
        Assert.assertArrayEquals(fix34(new int[]{3, 1, 1, 3, 4, 4}), new int[]{3, 4, 1, 3, 4, 1});
    }
}
