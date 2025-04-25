package com.vahoss.java_solutions;

public class Solution00121 {
    public int maxProfit(int[] prices) {

        int min = Integer.MAX_VALUE, diff = 0;
        for (int price : prices) {
            min = Math.min(min, price);
            diff = Math.max(diff, price - min);
        }

        return diff;
    }

    public static void main(String[] args) {
        var solution = new Solution00121();


        // Test case 1: Standard case
        int[] prices1 = {7, 1, 5, 3, 6, 4};
        System.out.println("Test case 1: " + solution.maxProfit(prices1) + " (Expected: 5)");

        // Test case 2: Decreasing prices
        int[] prices2 = {7, 6, 4, 3, 1};
        System.out.println("Test case 2: " + solution.maxProfit(prices2) + " (Expected: 0)");

        // Test case 3: Single day
        int[] prices3 = {1};
        System.out.println("Test case 3: " + solution.maxProfit(prices3) + " (Expected: 0)");

        // Test case 4: Two days with profit
        int[] prices4 = {1, 2};
        System.out.println("Test case 4: " + solution.maxProfit(prices4) + " (Expected: 1)");

        // Test case 5: Larger numbers
        int[] prices5 = {1000, 2000, 3000, 4000, 5000};
        System.out.println("Test case 5: " + solution.maxProfit(prices5) + " (Expected: 4000)");

        // Test case 6: Profit at the end
        int[] prices6 = {3, 2, 6, 5, 0, 3};
        System.out.println("Test case 6: " + solution.maxProfit(prices6) + " (Expected: 4)");

        // Test case 7: Empty array
        int[] prices7 = {};
        System.out.println("Test case 7: " + solution.maxProfit(prices7) + " (Expected: 0)");
    }
}