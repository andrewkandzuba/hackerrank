package org.hackerrank.java.interview.cci.graph;

import java.util.*;

class GraphUtils {
    static boolean pathExists(GraphPoint start, GraphPoint dest) {
        Set<GraphPoint> visited = new HashSet<>();
        Queue<GraphPoint> queue = new LinkedList<>();
        visited.add(start);
        queue.offer(start);

        while (!queue.isEmpty()) {
            GraphPoint gp = queue.poll();
            if (gp.equals(dest)) {
                return true;
            }
            gp.targets().stream().filter(o -> !visited.contains(o)).forEach(graphPoint -> {
                visited.add(graphPoint);
                queue.offer(graphPoint);
            });
        }
        return false;
    }

    static double findMinimalDistance(Point[] points) {
        if (points.length <= 1) return 0;

        Arrays.sort(points, (o1, o2) -> (o1.getX() != o2.getX()) ? o1.getX() - o2.getX() : o1.getY() - o2.getY());
        return findMinimalDistanceInSubset(points, 0, points.length - 1);
    }

    private static double findMinimalDistanceInSubset(Point[] points, int li, int ri) {
        if (ri - li == 1) return distance(points[li], points[ri]);

        int m = (ri - li + 1) / 2;
        int mx = points[m].getX();

        double h1 = findMinimalDistanceInSubset(points, li, li + m);
        double h2 = findMinimalDistanceInSubset(points, li + m, ri);
        double h = Math.min(h1, h2);

        int l = m - 1;
        while (l >= li && Math.abs(mx - points[l].getX()) < h) l--;
        int r = m + 1;
        while (r <= ri && Math.abs(mx - points[r].getX()) < h) r++;

        //Point[] bSet = new Point[bSize];
        //System.arraycopy(points, l, bSet, 0, r - );
        Arrays.sort(points, l, r, (o1, o2) -> o1.getX() - o2.getY());

        return 0;
    }

    private static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
    }
}
