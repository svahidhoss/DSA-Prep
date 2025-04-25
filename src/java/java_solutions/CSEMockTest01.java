package java.java_solutions;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;


public class CSEMockTest01 {
    private int findSecondLargest(List<Integer> list) {
        if (list == null || list.isEmpty()) return -1;

        if (list.size() == 1) return list.get(0);

        int largest = list.get(0);
        int secondLargest = list.get(1);

        if (largest < secondLargest) {
            int temp = largest;
            largest = secondLargest;
            secondLargest = temp;
        }

        for (Integer temp : list) {
            if (temp >= largest) {
                secondLargest = largest;
                largest = temp;
            } else if (temp > secondLargest) {
                secondLargest = temp;
            }
        }

        return secondLargest;
    }

    private boolean isPalindrome(String s) {
        s = s.toLowerCase();
        s = s.replaceAll("[^a-zA-Z0-9]", "");
        int p = 0;
        int q = s.length() - 1;
        while (p < q) {
            if (s.charAt(p) != s.charAt(q)) return false;
            p++;
            q--;
        }
        return true;
    }

    /**
     * \\s+ matches one or more whitespace characters
     * trim() removes leading/trailing spaces
     *
     * @param filePath path to the file
     * @return The number of occurrences of each word in the file
     */
    private Map<String, Integer> getFileWordOccurrencesCount(String filePath) {
        var wordCount = new HashMap<String, Integer>();

        try {
            String fileContent = Files.readString(Path.of(filePath));
            if (fileContent.trim().isEmpty()) return wordCount;

            String[] words = fileContent.trim().split("\\s+");
            for (String word : words) {
                if (wordCount.containsKey(word)) wordCount.put(word, wordCount.get(word) + 1);
                else wordCount.put(word, 1);
            }
        } catch (OutOfMemoryError e) {
            System.out.println("The file is extremely large");
        } catch (IOException e) {
            System.out.println("Something went wrong when reading the file");
        }
        return wordCount;
    }

    /**
     * Challenge 4 (10 points): Multithreading
     * Implement a producer-consumer pattern using Java's
     * concurrent utilities. The producer should generate
     * random numbers and the consumer should print their square.
     */
    private void producerConsumerFun() {
        var blockingQueue = new LinkedBlockingDeque<Integer>(10);

        var producer = new Thread(() -> {
            while (true) {
                var randomNumber = (int) (Math.random() * 1001); // 0 to 1000
                try {
                    blockingQueue.put(randomNumber);
                    System.out.println(randomNumber + " PRODUCED and sent to the blocking queue");
                    Thread.sleep(100); // faster producer
                } catch (InterruptedException e) {
                    System.out.println(randomNumber + " was blocked from being sent to the blocking queue");
                }
            }
        });

        var consumer = new Thread(() -> {
            while (true) {
                try {
                    var num = blockingQueue.take();
                    var square = num * num;
                    System.out.println("CONSUMER taken " + num + " with square of " + square + " from the the blocking queue");
                    Thread.sleep(1000); // slower consumer
                } catch (InterruptedException e) {
                    System.out.println("consumer was blocked from taking iterm from the blocking queue");
                }
            }
        });

        producer.start();
        consumer.start();
    }

    /**
     * Implement a method to merge k sorted arrays into a single sorted
     * array in the most efficient way possible. Your solution should:
     * <p>
     * Take an array of sorted integer arrays as input
     * Return a single array containing all elements in sorted order
     * Have the optimal time complexity (explain your approach)
     * Handle edge cases such as empty arrays
     * <p>
     * This is a classic problem that tests your understanding of priority queues,
     * efficient sorting algorithms, and time/space complexity analysis.
     *
     * @param sortedArrays
     * @return
     */
    private int[] mergeKSortedArrays(int[][] sortedArrays) {
        // check for emptiness or being null
        if (sortedArrays == null || sortedArrays.length == 0) return new int[0];

        // 1.Let's find the total number of elements first
        var totalElements = 0;
        for (int[] array : sortedArrays) {
            totalElements += array.length;
        }

        // 2. Then we start putting the minimum element of each array in the heap
        var minHeap = new PriorityQueue<Element>(sortedArrays.length);
        for (int i = 0; i < sortedArrays.length; i++) {
            minHeap.offer(new Element(sortedArrays[i][0], 0, i));
        }

        // 3. Now let's go thorough all the elements of the array
        // 3. Process elements
        int[] result = new int[totalElements];
        int resultIndex = 0;

        for (int i = 0; i < totalElements; i++) {
            Element polledElement = minHeap.poll();
            if (polledElement == null) break;

            result[resultIndex++] = polledElement.value;
            // add the next element from the array its min element was consumed
            minHeap.offer(
                    new Element(sortedArrays[polledElement.arrayNumber][polledElement.origIndexInArray + 1],
                            polledElement.arrayNumber,
                            polledElement.origIndexInArray + 1));
        }

        return result;
    }

    class Element implements Comparable<Element> {
        int value;
        int origIndexInArray;
        int arrayNumber;

        public Element(int value, int origIndexInArray, int arrayNumber) {
            this.value = value;
            this.origIndexInArray = origIndexInArray;
            this.arrayNumber = arrayNumber;
        }


        @Override
        public int compareTo(@NotNull Element other) {
            return this.value - other.value;
        }
    }

    public static void main(String[] args) {
        var mockTest = new CSEMockTest01();
        // Test cases for Palindrome Checker (Challenge 1)
        String[] palindromeTestCases = {
                "A man, a plan, a canal: Panama",
                "Race a car",
                "Was it a car or a cat I saw?",
                "No 'x' in Nixon",
                "hello",
                "",
                ".,;:!@#$%^&*()",
                "Madam, I'm Adam",
                "Racecar"
        };

        System.out.println("===== PALINDROME CHECKER TESTS =====");
        for (String test : palindromeTestCases) {
            boolean result = mockTest.isPalindrome(test);
            System.out.println("\"" + test + "\"" + " -> " + result);
        }

        // Test Challenge 2: Second Largest Element
        List<Integer[]> numberLists = new ArrayList<>();
        numberLists.add(new Integer[]{5, 9, 3, 1, 7});
        numberLists.add(new Integer[]{10, 10, 9, 8, 8});
        numberLists.add(new Integer[]{1, 1, 1, 1, 1});
        numberLists.add(new Integer[]{42});
        numberLists.add(new Integer[]{});

        System.out.println("\n===== SECOND LARGEST ELEMENT TESTS =====");
        for (Integer[] numbers : numberLists) {
            List<Integer> list = Arrays.asList(numbers);
            int result = mockTest.findSecondLargest(list);
            System.out.println(list + " -> " + result);
        }

        mockTest.producerConsumerFun();
    }
}
