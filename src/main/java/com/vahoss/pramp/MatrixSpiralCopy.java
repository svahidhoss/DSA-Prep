package com.vahoss.pramp;

class MatrixSpiralCopy {

    static int[] spiralCopy(int[][] inputMatrix) {
        // Cover edge cases
        if (inputMatrix == null || inputMatrix.length == 0 || inputMatrix[0].length == 0) {
            return new int[0];
        }

        int n = inputMatrix.length;
        int m = inputMatrix[0].length;
        int[] result = new int[m * n];

        int count = 0;
        int begRow = 0, endRow = n - 1;
        int begCol = 0, endCol = m - 1;

        // alternate between four directions
        while (begRow <= endRow && begCol <= endCol) {
            // Traverse Right
            for (int i = begCol; i <= endCol; i++) {
                result[count++] = inputMatrix[begRow][i];
            }
            begRow++; // 0 1 2

            // Traverse Down
            for (int i = begRow; i <= endRow; i++) {
                result[count++] = inputMatrix[i][endCol];
            }
            endCol--;  // 4 3 2

            if (begRow <= endRow) {
                // Traverse Left
                for (int i = endCol; i >= begCol; i--) {
                    result[count++] = inputMatrix[endRow][i]; // if end == beg => duplicate
                }
                endRow--; // 3 2 1
            }

            if (begCol <= endCol) {
                // Traverse Up
                for (int i = endRow; i >= begRow; i--) {
                    result[count++] = inputMatrix[i][begCol];
                }
                begCol++;  // 0 1
            }
        }

        return result;
    }

    public static void main(String[] args) {
        testEmptyMatrix();
        testSingleElementMatrix();
        testSingleRowMatrix();
        testSingleColumnMatrix();
        testSquareMatrix();
        testRectangularMatrix();
    }

    private static void testEmptyMatrix() {
        int[][] matrix = {};
        int[] expected = {};
        System.out.println("Test Empty Matrix: " + (compareArrays(spiralCopy(matrix), expected) ? "PASS" : "FAIL"));
    }

    private static void testSingleElementMatrix() {
        int[][] matrix = {{1}};
        int[] expected = {1};
        System.out.println("Test Single Element Matrix: " + (compareArrays(spiralCopy(matrix), expected) ? "PASS" : "FAIL"));
    }

    private static void testSingleRowMatrix() {
        int[][] matrix = {{1, 2, 3, 4}};
        int[] expected = {1, 2, 3, 4};
        System.out.println("Test Single Row Matrix: " + (compareArrays(spiralCopy(matrix), expected) ? "PASS" : "FAIL"));
    }

    private static void testSingleColumnMatrix() {
        int[][] matrix = {{1}, {2}, {3}, {4}};
        int[] expected = {1, 2, 3, 4};
        System.out.println("Test Single Column Matrix: " + (compareArrays(spiralCopy(matrix), expected) ? "PASS" : "FAIL"));
    }

    private static void testSquareMatrix() {
        int[][] matrix = {
                {1, 2, 3},
                {8, 9, 4},
                {7, 6, 5}
        };
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println("Test Square Matrix: " + (compareArrays(spiralCopy(matrix), expected) ? "PASS" : "FAIL"));
    }

    private static void testRectangularMatrix() {
        int[][] matrix = {
                {1, 2, 3, 4},
                {10, 11, 12, 5},
                {9, 8, 7, 6}
        };
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        System.out.println("Test Rectangular Matrix: " + (compareArrays(spiralCopy(matrix), expected) ? "PASS" : "FAIL"));
    }

    private static boolean compareArrays(int[] actual, int[] expected) {
        if (actual.length != expected.length) {
            return false;
        }
        for (int i = 0; i < actual.length; i++) {
            if (actual[i] != expected[i]) {
                return false;
            }
        }
        return true;
    }

}