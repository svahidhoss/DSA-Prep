package main.java.java_solutions;

import java.util.*;

public class CSEMockTest02 {

    private void challenge1() {
        // Ch1. Write a program that reads a string and
        // returns the string with characters reversed.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please provide a text or a word:");
        String input = scanner.nextLine();
        scanner.close();

        // Your code here
        int beg = 0, end = input.length() - 1;
        var stringBuilder = new StringBuilder(input);
        while (beg < end) {
            var begChar = stringBuilder.charAt(beg);
            stringBuilder.setCharAt(beg, stringBuilder.charAt(end));
            stringBuilder.setCharAt(end, begChar);
            beg++;
            end--;
        }
        input = stringBuilder.toString();
        System.out.println(input);

        // Alternative:
        stringBuilder = new StringBuilder(input);
        stringBuilder.reverse();
        input = stringBuilder.toString();
        System.out.println(input);
    }

    /**
     * Write a program that reads an integer n followed
     * by n integers and outputs the second largest
     * number in the list.
     */
    private void challenge2() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please provide the value of n:");
        int n = scanner.nextInt();

        // Your code here
        System.out.println("Please provide n numbers separated by space:");
//        int[] numbers = new int[n];
        var minHeap = new PriorityQueue<Integer>(2);
        for (int i = 0; i < n; i++) {
            var number = scanner.nextInt();
            if (minHeap.size() < 2) minHeap.add(number);
            else if (number > minHeap.peek()) {
                minHeap.poll();
                minHeap.add(number);
            }
        }

        System.out.println(minHeap.peek());
    }

    /**
     * Implement a method to check if a string is a valid
     * palindrome considering only alphanumeric characters
     * and ignoring case.
     */
    private void challenge3() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please provide the string to see if it's a valid palindrome:");
        String input = scanner.nextLine();
        scanner.close();

        System.out.println(isPalindrome(input));
    }

    public static boolean isPalindrome(String s) {
        // Your code here
        var stringBuilder = new StringBuilder(s.toLowerCase().replaceAll("[^a-zA-Z0-9]", ""));
        return (stringBuilder.toString().contentEquals(stringBuilder.reverse()));
    }

    /**
     * Implement a thread-safe singleton class that
     * demonstrates the double-checked locking pattern.
     */
    private void challenge4() {
        // Test your singleton implementation here
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();

        System.out.println(instance1 == instance2);
    }

    /**
     * Implement a simple in-memory key-value database with
     * transaction support. The program should handle the following commands:
     * <p>
     * SET key value - Set the value of a key
     * GET key - Get the value of a key
     * DELETE key - Delete a key
     * BEGIN - Start a transaction
     * COMMIT - Commit the current transaction
     * ROLLBACK - Rollback the current transaction
     */
    private void challenge5() {
        Scanner scanner = new Scanner(System.in);
        Database db = new Database();

        while (scanner.hasNext()) {
            String command = scanner.next();

            // Your code here to handle commands
            // (Implement your Database class)

        }

        scanner.close();
    }

    public static void main(String[] args) {
        var mockTest = new CSEMockTest02();

        int x = 10;
        System.out.println(x++ + ++x);
        System.out.println("5" + 2 + 3);
        String s1 = "Hello";
        String s2 = s1.intern();
        System.out.println(s2.intern());

        mockTest.challenge1();
        mockTest.challenge2();
        mockTest.challenge3();
        mockTest.challenge4();
    }
}

class Singleton {
    private static Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) instance = new Singleton();
        return instance;
    }
}

class DoubleLockedSingleton {
    /**
     * volatile: ensures that changes to the instance
     * variable are immediately visible to other threads.
     **/
    private static volatile DoubleLockedSingleton instance;

    private DoubleLockedSingleton() {
    }

    /**
     * The first null check is outside the synchronized block
     * for performance reasons.
     * <p>
     * The synchronized block ensures that only one thread can
     * create an instance at a time.
     * <p>
     * The second null check inside the synchronized block prevents
     * multiple instance creation if two threads enter the first
     * if statement simultaneously.
     *
     * @return
     */
    public static DoubleLockedSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleLockedSingleton.class) {
                if (instance == null) {
                    instance = new DoubleLockedSingleton();
                }
            }
        }
        return instance;
    }
}
