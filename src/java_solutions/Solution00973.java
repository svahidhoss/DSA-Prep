package java_solutions;

import java.util.*;

public class Solution00973 {
    public int[][] kClosest(int[][] points, int k) {
        // keep track of k minimum distances using max heap (replacing the max value if necessary)
        var maxHeap = new PriorityQueue<Point>(k, (a, b) -> b.distance - a.distance);

        // Calculate the distance from the origin for every point
        for (int[] point : points) {
            // No need to use sqrt() as comparing squared distances is sufficient and more efficient
            int distance = (int) (Math.pow(point[0], 2) + Math.pow(point[1], 2));
            if (maxHeap.size() >= k) {
                // add the point to max heap replacing the maximum value if necessary
                if (distance < maxHeap.peek().distance) {
                    maxHeap.poll();
                    maxHeap.offer(new Point(point, distance));
                }
            } else {
                maxHeap.offer(new Point(point, distance));
            }
        }

        var result = new int[k][2];
        // return the contents of the max heap
        int counter = 0;
        for (Point point : maxHeap) {
            result[counter++] = point.coords;
        }

        return result;
    }

    public static void main(String[] args) {
        var solution = new Solution00973();

    }
}

class Point {
    int[] coords;
    int distance;

    public Point(int[] coords, int distance) {
        this.coords = coords;
        this.distance = distance;
    }
}
