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

        // Test 1: Basic functionality
        int[][] points1 = {{1, 3}, {-2, 2}};
        int k1 = 1;
        System.out.println("Expected: [[-2,2]]");
        printResult(solution.kClosest(points1, k1), "Test 1");

        // Test 2: Points in different quadrants
        int[][] points2 = {{3, 3}, {5, -1}, {-2, 4}};
        int k2 = 2;
        System.out.println("Expected: [[3,3],[-2,4]]");
        printResult(solution.kClosest(points2, k2), "Test 2");

        // Test 3: Points with equal distances
        int[][] points3 = {{1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
        int k3 = 3;
        System.out.println("Expected: [[1,1],[-1,1],[1,-1]] or [[-1,-1],[1,-1],[-1,1]]");
        printResult(solution.kClosest(points3, k3), "Test 3");

        // Test 4: k equals number of points
        int[][] points4 = {{1, 3}, {-2, 2}, {2, -2}};
        int k4 = 3;
        System.out.println("Expected: [[1,3],[-2,2],[2,-2]]");
        printResult(solution.kClosest(points4, k4), "Test 4");

        // Test 5: Large number of points
        int[][] points5 = new int[10000][2];
        for (int i = 0; i < 10000; i++) {
            points5[i] = new int[]{i - 5000, 5000 - i};
        }
        int k5 = 100;
        printResult(solution.kClosest(points5, k5), "Test 5");

        // Test 6: Negative coordinates
        System.out.println("Expected: [[0,0],[-1,-1],[1,1]]");
        int[][] points6 = {{-5, -4}, {-3, -2}, {-1, -1}, {0, 0}, {1, 1}, {3, 2}, {5, 4}};
        int k6 = 3;
        printResult(solution.kClosest(points6, k6), "Test 6");

        // Test 7: Points on axes
        int[][] points7 = {{5, 0}, {-5, 0}, {0, 5}, {0, -5}};
        System.out.println("Expected: [[5,0],[-5,0]] or [[0,5],[0,-5]]");
        int k7 = 2;
        printResult(solution.kClosest(points7, k7), "Test 7");
    }

    private static void printResult(int[][] result, String testName) {
        System.out.println(testName + ":");
        for (int[] point : result) {
            System.out.print(Arrays.toString(point) + " ");
        }
        System.out.println();
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
