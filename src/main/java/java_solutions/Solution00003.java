package main.java.java_solutions;

import java.util.HashSet;

public class Solution00003 {
    public int lengthOfLongestSubstring(String s) {
        if (s == null) return 0;
        if (s.length() <= 1) return s.length();

        var set = new HashSet<Character>();
        // we have at least 2 chars
        int start = 0, end = 1;
        set.add(s.charAt(start));

        var ans = 1;
        while (end < s.length()) {
            var ch = s.charAt(end);
            if (!set.contains(ch)) {
                set.add(ch);
                ans = Math.max(ans, set.size());
                end++;
            } else {
                set.remove(s.charAt(start));
                start++;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        Solution00003 solution = new Solution00003();

        // Test case 1: Basic case with repeating characters
        System.out.println("Test 1: " + solution.lengthOfLongestSubstring("abcabcbb"));
        System.out.println("Expected: 3");

        // Test case 2: All repeating characters
        System.out.println("Test 2: " + solution.lengthOfLongestSubstring("bbbbb"));
        System.out.println("Expected: 1");

        // Test case 3: No repeating characters
        System.out.println("Test 3: " + solution.lengthOfLongestSubstring("abcdefg"));
        System.out.println("Expected: 7");

        // Test case 4: Empty string
        System.out.println("Test 4: " + solution.lengthOfLongestSubstring(""));
        System.out.println("Expected: 0");

        // Test case 5: String with spaces
        System.out.println("Test 5: " + solution.lengthOfLongestSubstring("ab c d ef"));
        System.out.println("Expected: 6");

        // Test case 6: String with special characters
        System.out.println("Test 6: " + solution.lengthOfLongestSubstring("!@#$%^&*()"));
        System.out.println("Expected: 10");

        // Test case 7: Repeating substring
        System.out.println("Test 7: " + solution.lengthOfLongestSubstring("abcabcabc"));
        System.out.println("Expected: 3");
    }
}
