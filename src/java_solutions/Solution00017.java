package java_solutions;

public class Solution00017 {
    public int maxAreaBruteForce(int[] height) {
        var maxArea = 0;

        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                maxArea = Math.max(maxArea, (j - i) * Math.min(height[i], height[j]));
            }
        }

        return maxArea;
    }

    public int maxArea(int[] height) {
        var maxArea = 0;
        var left = 0;
        var right = height.length - 1;

        while (left < right) {
            maxArea = Math.max(maxArea, (right - left) * Math.min(height[left], height[right]));
            if (height[left] <= height[right]) left++;
            else right--;
        }

        return maxArea;
    }

    public static void main(String[] args) {
        Solution00017 solution = new Solution00017();

        // Test cases
        int[][] testCases = {
                {1, 8, 6, 2, 5, 4, 8, 3, 7}, // Expected: 49
                {1, 1},                      // Expected: 1
                {4, 3, 2, 1, 4},             // Expected: 16
                {1, 2, 1},                   // Expected: 2
                {1, 3, 2, 5, 25, 24, 5}      // Expected: 24
        };

        int[] expectedResults = {49, 1, 16, 2, 24};

        // Run test cases and print results
        for (int i = 0; i < testCases.length; i++) {
            int result = solution.maxArea(testCases[i]);
            System.out.println("Test " + (i + 1) + ": Input: " + java.util.Arrays.toString(testCases[i]));
            System.out.println("Expected: " + expectedResults[i] + ", Got: " + result);
            System.out.println(result == expectedResults[i] ? "PASS" : "FAIL");
            System.out.println();
        }
    }
}
