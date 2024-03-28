package pramp;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class AbsoluteValueSort {
    static int[] absSort2(int[] arr) {
        // Convert int[] to Integer[] to use custom Comparator
        Integer[] arrObj = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arrObj[i] = arr[i];
        }

        // Sort using custom Comparator for absolute values
        Arrays.sort(arrObj, Comparator.comparingInt(Math::abs));

        // Convert back to int[]
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arrObj[i];
        }

        return arr;
    }

    static int[] absSort(int[] arr) {
        // your code goes here
        if (arr.length <= 1) return arr;

        // 1
        Map<Integer, Integer> negMap = new HashMap<>();
        for (int v : arr) {
            if (v < 0)
                negMap.put(-v, negMap.getOrDefault(-v, 0) + 1);
        }

        System.out.println(negMap);

        // 2.make every element abs
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0)
                arr[i] = Math.abs(arr[i]);
        }

        // 3.
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));

        // 4.
        // 0 2 2 2 7
        int newVal = arr[0];
        int i = 1;

        while (i < arr.length) { //i 2->  3
            // check if the value has changed
            if (arr[i] != newVal) { // i: 0 2 -> 4
                newVal = arr[i]; // 2 -> 7

                if (negMap.containsKey(newVal)) {
                    int negCount = negMap.get(arr[i]); // count = 2 -> 1
                    while (negCount > 0) {
                        arr[i] = -arr[i];
                        negCount--; // 1 -> 0
                        i++; // 2 -> 3 -> 5
                    }
                }
            } else {
                i++;  // i 3 -> 4
            }

        }

        return arr;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(absSort2 (new int[]{-2, -2, 2, 5, -7})));
        System.out.println(Arrays.toString(absSort2 (new int[]{-2, -2, 2, 5, -7})));
    }
}
