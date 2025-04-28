package com.vahoss.kotlin_solutions

class Solution00033 {
    fun search(nums: IntArray, target: Int): Int {
        var left = 0
        var right = nums.size - 1

        while (left <= right) {
            val mid = left + (right - left) / 2
            if (nums[mid] == target) return mid

            // Basically we are doing binary search making use of the fact that
            // at each given point, one part of the array is alwyas sorted

            // left half is sorted
            if (nums[left] <= nums[mid]) {
                if (target < nums[mid] && target >= nums[left]) right = mid - 1
                else left = mid + 1
            }
            // right half is sorted
            if (nums[right] >= nums[mid]) {
                if (target > nums[mid] && target <= nums[right]) left = mid + 1
                else right = mid - 1
            }
        }

        return -1
    }

    /**
     * It has some issues with case #8.
     */
    fun search2(nums: IntArray, target: Int): Int {
        if (nums.size <= 1 || nums.first() < nums.last()) return Math.max(-1, nums.binarySearch(target))

        // let's find rotation index
        val rotationIndex = findRotationIndex(0, nums.size - 1, nums)
        if (rotationIndex == -1) return -1

        // once found do the binary search that is needed.
        if (target > nums.last()) return Math.max(-1, nums.binarySearch(target, 0, rotationIndex))
        else return Math.max(-1, nums.binarySearch(target, rotationIndex + 1))
    }

    private fun findRotationIndex(beg: Int, end: Int, nums: IntArray): Int {
        if (beg == end) return -1

        val mid = beg + (end - beg) / 2
        if (mid + 1 < nums.size && nums[mid] > nums[mid + 1]) return mid
        if (mid - 1 >= 0 && nums[mid] < nums[mid - 1]) return mid - 1

        return if (nums[beg] > nums[mid]) findRotationIndex(beg, mid - 1, nums)
        else findRotationIndex(mid + 1, end, nums)
    }
}

fun main() {
    val sol = Solution00033()

    // Helper function to run and print test results
    fun runTest(testName: String, nums: IntArray, target: Int, expected: Int) {
        println("Running $testName")
        println("Input: nums = [${nums.joinToString(", ")}], target = $target")
        val result = sol.search(nums, target)
        println("Result: $result (Expected: $expected)")
        println("Pass: ${result == expected}")
        println("------------------------")
    }

    // Test Case 1: Standard Rotated Array, Target Exists
    runTest("Test Case 1 - Standard Rotated, Target Exists", intArrayOf(4, 5, 6, 7, 0, 1, 2), 0, 4)

    // Test Case 2: Standard Rotated Array, Target Does Not Exist
    runTest("Test Case 2 - Standard Rotated, Target Missing", intArrayOf(4, 5, 6, 7, 0, 1, 2), 3, -1)

    // Test Case 3: Rotated Array with Duplicates, Target Exists
    runTest("Test Case 3 - Rotated with Duplicates", intArrayOf(4, 5, 6, 7, 0, 1, 2, 2, 3), 2, 6)

    // Test Case 4: Non-Rotated Array, Target Exists
    runTest("Test Case 4 - Non-Rotated, Target Exists", intArrayOf(1, 2, 3, 4, 5), 3, 2)

    // Test Case 5: Non-Rotated Array, Target Missing
    runTest("Test Case 5 - Non-Rotated, Target Missing", intArrayOf(1, 2, 3, 4, 5), 6, -1)

    // Test Case 6: Single Element Array, Target Exists
    runTest("Test Case 6 - Single Element, Target Exists", intArrayOf(1), 1, 0)

    // Test Case 7: Single Element Array, Target Missing
    runTest("Test Case 7 - Single Element, Target Missing", intArrayOf(1), 2, -1)

    runTest("Test Case 7.1 - Single Element, Target Missing negative", intArrayOf(1), -2, -1)

    // Test Case 8: Two Element Array, Rotated
    runTest("Test Case 8 - Two Elements, Rotated", intArrayOf(3, 1), 3, 0)

    // Test Case 9: Empty Array
    runTest("Test Case 9 - Empty Array", intArrayOf(), 5, -1)

    // Test Case 10: Rotated Array, Target at Start
    runTest("Test Case 10 - Rotated, Target at Start", intArrayOf(5, 1, 2, 3, 4), 5, 0)
}

