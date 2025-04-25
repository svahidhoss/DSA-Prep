package com.vahoss.java_solutions;

public class Solution00125 {
    public boolean isPalindrome(String s) {
        // Two pointer approach:
        var beg = 0;
        var end = s.length() - 1;

        while (beg < end) {
            while (beg < end && !Character.isLetterOrDigit(s.charAt(beg))) beg++;
            while (beg < end && !Character.isLetterOrDigit(s.charAt(end))) end--;
            if (Character.toLowerCase(s.charAt(beg)) != Character.toLowerCase(s.charAt(end))) return false;
            beg++;
            end--;
        }

        return true;
    }

    public boolean isPalindrome2(String s) {
        // 1. prepare the s
        var sb = new StringBuilder(s.toLowerCase().replaceAll("[^a-zA-Z0-9]", ""));
        return (sb.toString().contentEquals(sb.reverse()));
    }

    public static void main(String[] args) {
        var solution = new Solution00125();

        TestCase[] testCases = {
                new TestCase("racecar", true),
                new TestCase("race a car", false),
                new TestCase("A man, a plan, a canal, Panama", true),
                new TestCase("hello", false),
                new TestCase("Madam, I'm Adam", true),
                new TestCase("12321", true),
                new TestCase("OpenAI", false),
                new TestCase("Was it a car or a cat I saw?", true),
                new TestCase("Java", false),
                new TestCase("", true),
                new TestCase("a", true),
                new TestCase("Able was I ere I saw Elba", true)
        };

        int passedTests = 0;
        int totalTests = testCases.length;

        for (TestCase test : testCases) {
            boolean result = solution.isPalindrome(test.input);
            boolean passed = (result == test.expected);
            passedTests += passed ? 1 : 0;

            System.out.printf("Test: \"%s\"%n", test.input);
            System.out.printf("Expected: %b, Got: %b, Result: %s%n",
                    test.expected, result, passed ? "PASS" : "FAIL");
            System.out.println();
        }

        System.out.printf("Passed %d out of %d tests%n", passedTests, totalTests);

    }

    static class TestCase {
        String input;
        boolean expected;

        TestCase(String input, boolean expected) {
            this.input = input;
            this.expected = expected;
        }
    }
}