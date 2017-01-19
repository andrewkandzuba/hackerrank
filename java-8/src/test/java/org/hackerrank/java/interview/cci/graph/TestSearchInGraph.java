package org.hackerrank.java.interview.cci.graph;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hackerrank.java.interview.cci.graph.GraphUtils.findMinimalDistance;
import static org.hackerrank.java.interview.cci.graph.GraphUtils.pathExists;

public class TestSearchInGraph {

    @Test
    public void testFindPath() throws Exception {
        List<GraphPoint> all = new ArrayList<>();

        all.add(new GraphPoint(0));
        all.add(new GraphPoint(1));
        all.add(new GraphPoint(2));
        all.add(new GraphPoint(3));

        all.get(0).add(all.get(1), all.get(3));
        all.get(1).add(all.get(2));
        all.get(2).add(all.get(0));

        Assert.assertTrue(pathExists(all.get(0), all.get(3)));
        Assert.assertTrue(pathExists(all.get(0), all.get(2)));
        Assert.assertFalse(pathExists(all.get(3), all.get(2)));
        Assert.assertTrue(pathExists(all.get(2), all.get(3)));
    }

    @Test
    public void testFindMinimalDistanceDirect() throws Exception {
        Assert.assertEquals(Double.MAX_VALUE, findMinimalDistance(new Point[0]), 0.0);
        Assert.assertEquals(Double.MAX_VALUE, findMinimalDistance(new Point[]{new Point(0, 1)}), 0.0);
        Assert.assertEquals(1.0, findMinimalDistance(new Point[]{new Point(0, 1), new Point(1, 1)}), 0.0);
        Assert.assertEquals(1.0, findMinimalDistance(new Point[]{new Point(0, 1), new Point(1, 1), new Point(1, 2)}), 0.0);
    }

    @Test
    public void testFindMinimalDistance() throws Exception {
        Assert.assertEquals(Math.sqrt(2.0), findMinimalDistance(new Point[]{
                new Point(0, 1),
                new Point(2, 2),
                new Point(2, 4),
                new Point(3, 3),
                new Point(1, 6)
        }), 0.0);

        Assert.assertEquals(Math.sqrt(5.0), findMinimalDistance(new Point[]{
                new Point(0, 0),
                new Point(1, 3),
                new Point(2, 5),
                new Point(3, 0),
                new Point(4, 3),
                new Point(6, 1),
                new Point(5, 5),
        }), 0.0);
    }
}
