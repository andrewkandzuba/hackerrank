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
    public void testFindMinimalDistance() throws Exception {
        Point[] points = {
                new Point(0, 1),
                new Point(2, 2),
                new Point(2, 4),
                new Point(3, 3),
                new Point(1, 2)
        };

        Assert.assertEquals(2.0, findMinimalDistance(points), 0.0);
    }
}
