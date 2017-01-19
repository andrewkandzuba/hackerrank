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
        Arrays.sort(points, (o1, o2) -> (o1.getX() != o2.getX()) ? o1.getX() - o2.getX() : o1.getY() - o2.getY());
        return findMinimalDistanceInSubset(points, 0, points.length, Double.MAX_VALUE);
    }

    private static double findMinimalDistanceInSubset(Point[] points, int li, int ri, double minDist) {
        if (ri - li <= 3) {
            for (int i = li; i < ri; i++) {
                for (int j = i + 1; j < ri; j++) {
                    minDist = Math.min(distance(points[i], points[j]), minDist);
                }
            }
            return minDist;
        }

        int m = (ri - li) >> 1;
        int midX = points[m].getX();

        double h1 = findMinimalDistanceInSubset(points, li, li + m, minDist);
        double h2 = findMinimalDistanceInSubset(points, li + m, ri, minDist);
        double h = Math.min(h1, h2);

        int l = m;
        while (l - 1 >= li && Math.abs(midX - points[l - 1].getX()) < h) l--;
        int r = m;
        while (r + 1 < ri && Math.abs(midX - points[r + 1].getX()) < h) r++;
        if (r - l == 0) return minDist;

        Point[] bSet = new Point[r - l + 1];
        System.arraycopy(points, l, bSet, 0, r - l + 1);
        Arrays.sort(bSet, Comparator.comparingInt(Point::getY));

        Point[] cSet = new Point[r - l + 1];
        int cSetSize = 0;

        for (Point aBSet : bSet) {
            if (Math.abs(aBSet.getX() - midX) < h) {
                for (int j = cSetSize - 1; j >= 0 && aBSet.getY() - cSet[j].getY() < h; --j) {
                    double d = distance(aBSet, cSet[j]);
                    minDist = (d < minDist) ? d : minDist;
                }
                cSet[cSetSize++] = aBSet;
            }
        }

        return minDist;
    }

    private static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
    }
}
