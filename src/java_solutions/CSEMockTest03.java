package java_solutions;

import java.util.Scanner;

public class CSEMockTest03 {

    /**
     * Write a program that reads a string and checks
     * if it is a valid integer.
     */
    private void challenge1(Scanner scanner) {
        System.out.println("Please enter a valid number: ");
        String input = scanner.nextLine();
        try {
            var num = Integer.parseInt(input);
            System.out.println("The number is " + num);
        } catch (NumberFormatException e) {
            System.out.println("Not a valid number");
        }
    }

    /**
     * Implement a method to find the longest word
     * in a given sentence.
     */
    private void challenge2(Scanner scanner) {
        System.out.println("Please enter a sentence to evaluate: ");
        String sentence = scanner.nextLine();

        System.out.println(findLongestWord(sentence));
    }

    public static String findLongestWord(String sentence) {
        // Your implementation here
        String[] words = sentence.split("\\s+");
        if (words.length == 0) return null;

        var ans = words[0];
        for (String word : words) {
            if (word.length() > ans.length()) ans = word;
        }
        return ans;
    }

    /**
     * Implement a thread-safe singleton class that
     * demonstrates the double-checked locking pattern.
     */
    private void challenge3() {
        // Test your singleton implementation here
        DoubleLockedSingleton instance1 = DoubleLockedSingleton.getInstance();
        DoubleLockedSingleton instance2 = DoubleLockedSingleton.getInstance();

        System.out.println(instance1 == instance2);
    }

    /**
     * Implement a method to perform basic compression of
     * a string where consecutive repeated characters are
     * replaced by the character and the count.
     */
    private void challenge4(Scanner scanner) {
        System.out.println("Please enter a string to compress: ");
        String input = scanner.nextLine();
        System.out.println(compressString(input));
    }

    public static String compressString(String str) {
        // Your implementation here
        if (str == null || str.length() <= 1) return str;
        var sb = new StringBuilder();
        int beg = 0;
        int end = 0;
        while (end < str.length()) {
            char begCh = str.charAt(beg);
            char endCh = str.charAt(end);
            while (end < str.length() && endCh == begCh) {
                endCh = str.charAt(end++);
            }
            int comp = end - beg;

            sb.append(begCh);
            if (comp > 1) sb.append(comp);

            beg = end;
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        var mockTest = new CSEMockTest03();
        Scanner scanner = new Scanner(System.in);
        mockTest.challenge1(scanner);
        mockTest.challenge2(scanner);
        mockTest.challenge3();
        mockTest.challenge4(scanner);
        scanner.close();
    }
}
