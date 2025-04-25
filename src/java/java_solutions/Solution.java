package java.java_solutions;

import java.util.*;

public class Solution {

    public int hardestWorker(int n, int[][] logs) {
        int[] taskUsers = new int[logs.length];
        int[] taskTimes = new int[logs.length];

        for (int i = 0; i < logs.length; i++) {
            int[] currentLog = logs[i];
            taskUsers[i] = currentLog[0];
            if (i - 1 < 0) {
                taskTimes[i] = currentLog[1];
            } else {
                taskTimes[i] = currentLog[1] - logs[i - 1][1];
            }
        }

        int maxTaskTimeValue = 0;
        int maxTaskTimeId = 0;
        int result = 0;
        for (int i = 0; i < taskTimes.length; i++) {
            if (taskTimes[i] > maxTaskTimeValue) {
                maxTaskTimeValue = taskTimes[i];
                maxTaskTimeId = i;
                result = taskUsers[i];
            } else if (taskTimes[i] == maxTaskTimeValue) {
                result = Math.min(taskUsers[maxTaskTimeId], taskUsers[i]);
            }
        }

        return result;
    }

    public int hardestWorker2(int n, int[][] logs) {
        int current = 0;
        int maxLength = 0, id = -1;
        for (int[] log : logs) {
            int taskLength = log[1] - current;
            if (taskLength > maxLength || taskLength == maxLength && log[0] < id) {
                maxLength = taskLength;
                id = log[0];
            }
            current = log[1];
        }
        return id;
    }

    static int[] sortKMessedArray(int[] arr, int k) {
        // your code goes here
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j <= i + k; j++) {
                if (j >= n) continue;
                if (arr[j] < arr[i]) swap(arr, j, i);
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
        System.out.println(Arrays.toString(sortKMessedArray(new int[]{1, 4, 5, 2, 3, 7, 8, 6, 10, 9}, 2)));
        System.out.println(Arrays.toString(sortKMessedArray(new int[]{6, 1, 4, 11, 2, 0, 3, 7, 10, 5, 8, 9}, 6)));
    }
}

