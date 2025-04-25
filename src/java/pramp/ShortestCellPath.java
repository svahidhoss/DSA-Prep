package java.pramp;

import java.util.ArrayList;
import java.util.List;

public class ShortestCellPath {
    static int shortestCellPath(int[][] grid, int sr, int sc, int tr, int tc) {
        // your code goes here
        if (sr == tr && sc == tc) return 0;

        // For each given point, find the adjacent points with value 1
        // Going to call SCP dp fun on all of these acceptable points
        List<int[]> adjPoints = getAdjPoints(grid, sr, sc);
        if (adjPoints.isEmpty())
            return -1;

        // Set the current node to be visited: -1
        grid[sr][sc] = -1;

        // Get the minimum amount of all the adjacent nodes, default is -1
        int minPath = Integer.MAX_VALUE;
        for (int[] point : adjPoints) {
            int pathLength = shortestCellPath(grid, point[0], point[1], tr, tc);
            if (pathLength != -1) {
                minPath = Math.min(minPath, pathLength + 1);
            }
        }

        if (minPath != Integer.MAX_VALUE)
            return minPath;
        else return -1;
    }

    static List<int[]> getAdjPoints(int[][] grid, int r, int c) {
        List<int[]> points = new ArrayList<>();
        if (isValidPoint(grid, r - 1, c)) {
            points.add(new int[]{r - 1, c});
        }
        if (isValidPoint(grid, r + 1, c)) {
            points.add(new int[]{r + 1, c});
        }
        if (isValidPoint(grid, r, c - 1)) {
            points.add(new int[]{r, c - 1});
        }
        if (isValidPoint(grid, r, c + 1)) {
            points.add(new int[]{r, c + 1});
        }
        return points;
    }

    static boolean isValidPoint(int[][] grid, int r, int c) {
        return (r >= 0 && c >= 0 &&
                r < grid.length && c < grid[0].length &&
                grid[r][c] == 1);
    }


    public static void main(String[] args) {
        int[][] grid = {{1, 1, 1, 1}, {0, 0, 0, 1}, {1, 1, 1, 1}};
        // 8
        System.out.println(shortestCellPath(grid, 0, 0, 2, 0));

        grid = new int[][]{{1, 1, 1, 1}, {0, 0, 0, 1}, {1, 0, 1, 1}};
        // -1
        System.out.println(shortestCellPath(grid, 0, 0, 2, 0));

        grid = new int[][]{{0, 1, 0}, {1, 0, 0}, {1, 0, 1}};
        // 1
        System.out.println(shortestCellPath(grid, 2, 0, 1, 0));

        grid = new int[][]{{1, 1, 1}, {0, 0, 0}, {0, 0, 0}};
        // 1
        System.out.println(shortestCellPath(grid, 0, 1, 0, 0));
    }
}
