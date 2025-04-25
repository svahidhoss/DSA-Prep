package java.java_solutions;

import java.util.ArrayList;
import java.util.Arrays;

public class Solution00075 {

    /**
     * O(n) time and space complexity
     *
     * @param nums input array with 3 colors
     */
    public void sortColors2(int[] nums) {
        var redList = new ArrayList<Integer>();
        var whiteList = new ArrayList<Integer>();
        var blueList = new ArrayList<Integer>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) redList.add(i);
            else if (nums[i] == 1) whiteList.add(i);
            else blueList.add(i);
        }

        var counter = 0;
        for (int i : redList) nums[counter++] = 0;
        for (int i : whiteList) nums[counter++] = 1;
        for (int i : blueList) nums[counter++] = 2;
    }

    /**
     * O(n) time and O(1) space complexity
     *
     * @param nums input array with 3 colors
     */
    public void sortColors(int[] nums) {
        if (nums.length < 2) return;

        int i = 0;
        int red = 0;
        int blue = nums.length - 1;
        while (i <= blue) {
            if (nums[i] == 0) {
                swap(nums, i++, red++);
            } else if (nums[i] == 2) {
                // value at i will need to be checked after this
                swap(nums, i, blue--);
            } else {
                i++;
            }
        }
    }

    static void swap(int[] arr, int p, int q) {
        var temp = arr[p];
        arr[p] = arr[q];
        arr[q] = temp;
    }

    public static void main(String[] args) {
        var solution = new Solution00075();

        // Test case 1: Standard case
        int[] nums1 = {2, 0, 2, 1, 1, 0};
        solution.sortColors(nums1);
        System.out.println("Test 1: " + Arrays.toString(nums1));
        System.out.println("Expected: [0, 0, 1, 1, 2, 2]");

        // Test case 2: Already sorted
        int[] nums2 = {0, 0, 1, 1, 2, 2};
        solution.sortColors(nums2);
        System.out.println("Test 2: " + Arrays.toString(nums2));
        System.out.println("Expected: [0, 0, 1, 1, 2, 2]");

        // Test case 3: Reverse sorted
        int[] nums3 = {2, 2, 1, 1, 0, 0};
        solution.sortColors(nums3);
        System.out.println("Test 3: " + Arrays.toString(nums3));
        System.out.println("Expected: [0, 0, 1, 1, 2, 2]");

        // Test case 4: All same color
        int[] nums4 = {1, 1, 1, 1};
        solution.sortColors(nums4);
        System.out.println("Test 4: " + Arrays.toString(nums4));
        System.out.println("Expected: [1, 1, 1, 1]");

        // Test case 5: Two colors only
        int[] nums5 = {2, 0, 2, 0, 2, 0};
        solution.sortColors(nums5);
        System.out.println("Test 5: " + Arrays.toString(nums5));
        System.out.println("Expected: [0, 0, 0, 2, 2, 2]");

        // Test case 6: Empty array
        int[] nums6 = {};
        solution.sortColors(nums6);
        System.out.println("Test 6: " + Arrays.toString(nums6));
        System.out.println("Expected: []");
    }
}
