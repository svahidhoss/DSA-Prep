package com.vahoss.kotlin_solutions

class Solutions00169 {

    fun majorityElementBoyerMoore(nums: IntArray): Int {
        // Handle edge case: empty array
        if (nums.isEmpty()) throw IllegalArgumentException("Array cannot be empty")

        var candidate = nums[0]
        var count = 1

        for (i in 1 until nums.size) {
            if (count == 0) candidate = nums[i]
            if (candidate == nums[i]) count++
            else count--
        }

        return candidate
    }

    fun majorityElement(nums: IntArray): Int {
        // Handle edge case: empty array (though problem guarantees non-empty)
        if (nums.isEmpty()) throw IllegalArgumentException("Array cannot be empty")

        // Build frequency map and track majority element
        val frequencyMap = mutableMapOf<Int, Int>()
        var majorityElement = nums[0]
        var maxCount = 1

        nums.forEach { num ->
            frequencyMap[num] = frequencyMap.getOrDefault(num, 0) + 1
            if (frequencyMap[num]!! > maxCount) {
                majorityElement = num
                maxCount = frequencyMap[num]!!
            }
        }

        return majorityElement
    }

    fun majorityElement2(nums: IntArray): Int {
        val map = mutableMapOf<Int, Int>()
        var maxRepeat = Pair(nums[0], 1)
        nums.forEach {
            map[it] = map.getOrDefault(it, 0) + 1
            if (map[it]!! > maxRepeat.second) {
                maxRepeat = Pair(it, map[it]!!)
            }
        }

        return maxRepeat.first
    }
}

fun main() {
    val solution = Solutions00169()

    // Helper function to print test results
    fun runTest(testName: String, nums: IntArray, expected: Int) {
        println("Running $testName")
        println("Input: ${nums.joinToString(", ")}")
        try {
            val resultHashMap = solution.majorityElement(nums)
            val resultBoyerMoore = solution.majorityElementBoyerMoore(nums)
            println("HashMap Result: $resultHashMap (Expected: $expected)")
            println("Boyer-Moore Result: $resultBoyerMoore (Expected: $expected)")
            println("HashMap Pass: ${resultHashMap == expected}")
            println("Boyer-Moore Pass: ${resultBoyerMoore == expected}")
        } catch (e: Exception) {
            println("Error: ${e.message} (Expected: $expected)")
        }
        println("------------------------")
    }

    // Test Case 1: Basic Case with Clear Majority
    runTest("Test Case 1 - Basic Case", intArrayOf(3, 2, 3), 3)

    // Test Case 2: Single Element
    runTest("Test Case 2 - Single Element", intArrayOf(1), 1)

    // Test Case 3: Majority at the End
    runTest("Test Case 3 - Majority at End", intArrayOf(1, 2, 3, 2, 2, 1, 2), 2)

    // Test Case 4: Majority with Mixed Order
    runTest("Test Case 4 - Mixed Order", intArrayOf(1, 6, 2, 6, 3, 6, 4, 6), 6)

    // Test Case 5: Edge Case - Empty Array
    runTest("Test Case 5 - Empty Array", intArrayOf(), -1) // -1 as placeholder since exception expected
}
