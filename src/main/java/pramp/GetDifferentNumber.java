package main.java.pramp;

import java.util.HashSet;
import java.util.Set;

public class GetDifferentNumber {
    static int getDifferentNumber(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int v = arr[i];
            // if within bounds and v is not already in its supposed place
            if (v < arr.length && v != i) {
                // swap these
                int temp = arr[v];
                arr[v] = arr[i];
                arr[i] = temp;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != i) return i;
        }

        // return the size since arr is ordered starting from 0
        return arr.length;
    }

    static int getDifferentNumber2(int[] arr) {
        // your code goes here
        Set<Integer> set = new HashSet<>();
        int minVal = Integer.MAX_VALUE;
        for (int value : arr) {
            set.add(value);
            minVal = Math.min(minVal, value);
        }

        // For cases where min value is bigger than 0
        if (minVal > 0) return 0;

        int result = 0;
        while (set.contains(result)) result++;

        return result;
    }

    static int getDifferentNumberImproved(int[] arr) {
        int i = 0;
        while (i < arr.length) {
            if (arr[i] < arr.length && arr[i] != i && arr[arr[i]] != arr[i]) {
                // Swap arr[i] and arr[arr[i]]
                int temp = arr[arr[i]];
                arr[arr[i]] = arr[i];
                arr[i] = temp;
            } else {
                // Move to the next index only if the current number is in its correct place
                // or cannot be placed.
                i++;
            }
        }

        // After rearrangement, the first index that does not match its value is the answer.
        for (i = 0; i < arr.length; i++) {
            if (arr[i] != i) {
                return i;
            }
        }

        // If all indices are correct, the answer is arr.length.
        return arr.length;
    }

    public static void main(String[] args) {
        System.out.println(getDifferentNumber2(new int[]{0}));
        System.out.println(getDifferentNumber2(new int[]{0, 1, 2}));
        System.out.println(getDifferentNumber2(new int[]{1, 3, 0, 2}));
        System.out.println(getDifferentNumber2(new int[]{0, 5, 4, 1, 3, 6, 2}));
        System.out.println(getDifferentNumber2(new int[]{1000000}));
        System.out.println();

        System.out.println(getDifferentNumber(new int[]{0}));
        System.out.println(getDifferentNumber(new int[]{0, 1, 2}));
        System.out.println(getDifferentNumber(new int[]{1, 3, 0, 2}));
        System.out.println(getDifferentNumber(new int[]{0, 5, 4, 1, 3, 6, 2}));
        System.out.println(getDifferentNumber(new int[]{1000000}));
        System.out.println();

        System.out.println(getDifferentNumberImproved(new int[]{0}));
        System.out.println(getDifferentNumberImproved(new int[]{0, 1, 2}));
        System.out.println(getDifferentNumberImproved(new int[]{1, 3, 0, 2}));
        System.out.println(getDifferentNumberImproved(new int[]{0, 5, 4, 1, 3, 6, 2}));
        System.out.println(getDifferentNumberImproved(new int[]{1000000}));

    }
}
