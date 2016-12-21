package org.hackerrank.java.interview.cci.graph;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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

        Assert.assertTrue(GraphUtils.pathExists(all.get(0), all.get(3)));
        Assert.assertTrue(GraphUtils.pathExists(all.get(0), all.get(2)));
        Assert.assertFalse(GraphUtils.pathExists(all.get(3), all.get(2)));
        Assert.assertTrue(GraphUtils.pathExists(all.get(2), all.get(3)));
    }
}
