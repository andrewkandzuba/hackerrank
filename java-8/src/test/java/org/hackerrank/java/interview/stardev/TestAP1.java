package org.hackerrank.java.interview.stardev;

import org.junit.Assert;
import org.junit.Test;

import static org.hackerrank.java.interview.stardev.AP1.scoresIncreasing;
import static org.hackerrank.java.interview.stardev.AP1.scores100;
import static org.hackerrank.java.interview.stardev.AP1.scoresClump;
import static org.hackerrank.java.interview.stardev.AP1.scoresAverage;

public class TestAP1 {

    @Test
    public void testScoresIncreasing() throws Exception {
        Assert.assertTrue(scoresIncreasing(new int[]{1, 3, 4}));
        Assert.assertFalse(scoresIncreasing(new int[]{1, 3, 2}));
        Assert.assertTrue(scoresIncreasing(new int[]{1, 1, 4}));

        Assert.assertFalse(scoresIncreasing(new int[]{1, 1, 2, 3, 4, 0}));
        Assert.assertFalse(scoresIncreasing(new int[]{1, 0}));
        Assert.assertFalse(scoresIncreasing(new int[]{1, 2, 3, 4, 5, 6, 7, 0, 0, 1, 0}));
    }

    @Test
    public void testScores100() throws Exception {
        Assert.assertTrue(scores100(new int[]{1, 100, 100}));
        Assert.assertFalse(scores100(new int[]{1, 100, 99, 100}));
        Assert.assertTrue(scores100(new int[]{100, 1, 100, 100}));
    }

    @Test
    public void testScoresClump() throws Exception {
        Assert.assertTrue(scoresClump(new int[]{3, 4, 5}));
        Assert.assertFalse(scoresClump(new int[]{3, 4, 6}));
        Assert.assertTrue(scoresClump(new int[]{1, 3, 5, 5}));
    }

    //@Test
    public void testScoresAverage() throws Exception {
        Assert.assertTrue(scoresAverage(new int[]{2, 2, 4, 4}) == 4);
        Assert.assertTrue(scoresAverage(new int[]{4, 4, 4, 2, 2, 2}) == 4);
        Assert.assertTrue(scoresAverage(new int[]{3, 4, 5, 1, 2, 3}) == 4);
    }
}
