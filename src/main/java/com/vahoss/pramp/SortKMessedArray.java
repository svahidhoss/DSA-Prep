package com.vahoss.pramp;

import java.util.*;

class SortKMessedArray {

    static int[] sortKMessedArray(int[] arr, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < Math.min(arr.length, k + 1); i++) {
            minHeap.add(arr[i]);
        }
        int index = 0;
        for (int i = k + 1; i < arr.length; i++) {
            arr[index++] = minHeap.poll();
            minHeap.add(arr[i]);
        }
        while (!minHeap.isEmpty()) arr[index++] = minHeap.poll();
        return arr;
    }

    static int[] sortKMessedArrayInsertionSort(int[] arr, int k) {
        // your code goes here
        for (int i = 0; i < arr.length; i++) {
            for (int j = 1; j <= k; j++) {
                var index = i - j;
                if (index < 0) break;
                if (arr[index] > arr[index + 1])
                    swap(arr, index, index + 1);
            }
        }

        return arr;
    }

    static void swap(int[] arr, int p, int q) {
        int temp = arr[p];
        arr[p] = arr[q];
        arr[q] = temp;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(
                sortKMessedArray(new int[]{1, 4, 5, 2, 3, 7, 8, 6, 10, 9}, 2)));
        System.out.println(Arrays.toString(
                sortKMessedArray(new int[]{6, 1, 4, 11, 2, 0, 3, 7, 10, 5, 8, 9}, 6)));
    }

}
