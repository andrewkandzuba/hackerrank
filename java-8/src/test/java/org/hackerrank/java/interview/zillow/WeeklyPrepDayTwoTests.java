package org.hackerrank.java.interview.zillow;

import org.junit.Test;

import java.util.*;

public class WeeklyPrepDayTwoTests {

    private final static Solution solution = new Solution();

    @Test
    public void BFS() {
        solution.bfs(5, 3, List.of(List.of(1,2), List.of(1, 3), List.of(3, 4)), 1);
    }

    private static class Node {
        private final Set<Node> adjacent = new HashSet<>();
        private final int value;
        private boolean marked = false;
        private int level = 0;

        public Node(int value) {
            this.value = value;
        }

        public Set<Node> getAdjacent() {
            return adjacent;
        }

        public int getValue() {
            return value;
        }

        public boolean isMarked() {
            return marked;
        }

        public void setMarked() {
            this.marked = true;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }
    }

    static class Solution implements Comparator<Solution> {

        public List<Integer> bfs(int n, int m, List<List<Integer>> edges, int s) {
            // Write your code here

            Map<Integer, Node> roots = new HashMap<>();
            for(int i = 1; i <= n; i++) {
                roots.putIfAbsent(i, new Node(i));
            }
            
            for (List<Integer> edge : edges) {
                int startValue = edge.get(0);
                int endValue = edge.get(1);

                Node startNode = roots.computeIfAbsent(startValue, integer -> new Node(startValue));
                Node endNode = roots.computeIfAbsent(endValue, integer -> new Node(endValue));
                startNode.getAdjacent().add(endNode);
                endNode.getAdjacent().add(startNode);
            }

            int [] weights = new int[n];
            Arrays.fill(weights, -1);

            Node root = roots.get(s);
            root.setMarked();

            Queue<Node> queue = new ArrayDeque<>();
            queue.offer(root);

            while (!queue.isEmpty()) {
                Node r = queue.remove();
                weights[r.getValue()-1] = 6 * r.getLevel();
                for (Node c: r.getAdjacent()) {
                    if (!c.isMarked()) {
                        c.setMarked();
                        c.setLevel(r.getLevel() + 1);
                        queue.offer(c);
                    }
                }
            }

            List<Integer> result = new ArrayList<>();
            for(int w : weights) {
                if(w!=0) result.add(w);
            }

            return result;
        }

        @Override
        public int compare(Solution o1, Solution o2) {
            return 0;
        }
    }
}
