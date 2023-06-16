package org.hackerrank.java.interview.zillow;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class ShortagePathInGraphTests {

    @Test
    public void beautifulPath1Test() {


        List<List<Integer>> edges = new ArrayList<>();
        edges.add(List.of(1, 2, 1));
        edges.add(List.of(1, 2, 1000));
        edges.add(List.of(2, 3, 3));
        edges.add(List.of(1, 3, 100));
        edges.add(List.of(4, 5, 1));


        Assert.assertEquals(3, beautifulPath(edges, 1, 3));
        Assert.assertEquals(-1, beautifulPath(edges, 1, 5));
    }


    @Test
    public void beautifulPath2Test() {
        List<List<Integer>> edges = new ArrayList<>();
        edges.add(List.of(1, 2, 1));

        Assert.assertEquals(-1, beautifulPath(edges, 1, 3));
        Assert.assertEquals(-1, beautifulPath(edges, 1, 5));
    }

    @Test
    public void beautifulPath3Test() {
        List<List<Integer>> edges = new ArrayList<>();
        edges.add(List.of(1, 1, 1));

        Assert.assertEquals(0, beautifulPath(edges, 1, 1));
        Assert.assertEquals(-1, beautifulPath(edges, 1, 5));
    }

    @Test
    public void beautifulPath4Test() {
        List<List<Integer>> edges = new ArrayList<>();
        edges.add(List.of(1, 1, 1));

        Assert.assertEquals(0, beautifulPath(edges, 1, 1));
        Assert.assertEquals(-1, beautifulPath(edges, 1, 5));
    }
    public static int beautifulPath(List<List<Integer>> edges, int A, int B) {
        // Write your code here
        Map<Integer, Set<GFG.Edge>> adjacents = GFG.makeAdjacencyList(edges);

        boolean[][] dp = new boolean[1001][1024];

        GFG.dfs(A, 0, adjacents, dp);

        for(int i=0; i<1024; ++i) {
            if(dp[B][i]) return i;
        }

        return -1;
    }

    private static class GFG {

        private static void dfs(int from, int cost, Map<Integer, Set<Edge>> adjacents, boolean dp[][]) {
            dp[from][cost]=true;
            Set<Edge> nextNodes = adjacents.get(from);
            if(nextNodes != null) {
                for(Edge e : nextNodes) {
                    int newCost = cost | e.cost;
                    if(!dp[e.target][newCost]) {
                        dfs(e.target, newCost, adjacents, dp);
                    }
                }
            }

        }

        private static Map<Integer, Set<Edge>> makeAdjacencyList(List<List<Integer>> edges) {
            Map<Integer, Set<Edge>> adjacents = new HashMap<>();
            for (List<Integer> edge : edges) {
                int u = edge.get(0);
                int v = edge.get(1);
                int weight = edge.get(2);
                //System.err.format("adding %s,%s = %s\n", u, v, weight);
                if (!adjacents.containsKey(u)) adjacents.put(u, new HashSet<>());
                adjacents.get(u).add(new Edge(v, weight));
                if (!adjacents.containsKey(v)) adjacents.put(v, new HashSet<>());
                adjacents.get(v).add(new Edge(u, weight));
            }
            return adjacents;
        }

        private static class Edge {
            Edge(int target, int cost) {
                this.target = target;
                this.cost = cost;
            }

            int target;
            int cost;
        }
    }

}
