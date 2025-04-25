package java.java_solutions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution00001 {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalStateException("No answer was found!");
    }

    public static void main(String[] args) {
        Solution00001 solution = new Solution00001();

        // Test case 1: Basic example
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        int[] result1 = solution.twoSum(nums1, target1);
        System.out.println("Test 1: " + Arrays.toString(result1) +
                " (Expected: [0, 1])");

        // Test case 2: Numbers not in order
        int[] nums2 = {3, 2, 4};
        int target2 = 6;
        int[] result2 = solution.twoSum(nums2, target2);
        System.out.println("Test 2: " + Arrays.toString(result2) +
                " (Expected: [1, 2])");

        // Test case 3: Same number used twice
        int[] nums3 = {3, 3};
        int target3 = 6;
        int[] result3 = solution.twoSum(nums3, target3);
        System.out.println("Test 3: " + Arrays.toString(result3) +
                " (Expected: [0, 1])");

        // Test case 4: Negative numbers
        int[] nums4 = {-1, -2, -3, -4, -5};
        int target4 = -8;
        int[] result4 = solution.twoSum(nums4, target4);
        System.out.println("Test 4: " + Arrays.toString(result4));

        // Test case 5: Larger array
        int[] nums5 = {2, 5, 9, 1, 7, 6, 12, 3};
        int target5 = 10;
        int[] result5 = solution.twoSum(nums5, target5);
        System.out.println("Test 5: " + Arrays.toString(result5));
    }
}
