package com.vahoss.kotlin_solutions

class Solution00169 {

    /**
     * It works because each pair of different elements can cancel out,
     * and a true majority has more copies than everything else combined,
     * so it survives.
     *
     * Solution with O(1) space and O(n) time complexity.
     */
    fun majorityElementBoyerMoore(nums: IntArray): Int {
        // Handle edge case: empty array
        if (nums.isEmpty()) throw IllegalArgumentException("Array cannot be empty")

        // Boyer-Moore voting: every element different from the candidate cancels one vote
        // for it. The majority element appears more than n/2 times, so it has more votes
        // than all the other elements combined and survives the cancelling.
        var candidate = nums[0]
        var count = 1

        for (i in 1 until nums.size) {
            // No votes left: the next element becomes the new candidate.
            if (count == 0) candidate = nums[i]
            // Same as the candidate: one more vote. Different: it cancels one vote.
            if (candidate == nums[i]) count++
            else count--
        }

        // Only correct because the problem guarantees a majority element exists.
        return candidate
    }

    /**
     * O(n) time and space complexity solution using a freq map for each
     */
    fun majorityElement(nums: IntArray): Int {
        // Handle edge case: empty array (though problem guarantees non-empty)
        if (nums.isEmpty()) throw IllegalArgumentException("Array cannot be empty")

        // Build frequency map and track majority element
        // frequencyMap: number -> how many times it has appeared so far
        val frequencyMap = mutableMapOf<Int, Int>()
        // Element with the highest count seen so far, and that count.
        var majorityElement = nums[0]
        var maxCount = 1

        nums.forEach { num ->
            // Count this occurrence (starting from 0 for a number not seen before).
            frequencyMap[num] = frequencyMap.getOrDefault(num, 0) + 1
            // This number now has more occurrences than the previous leader: it takes over.
            if (frequencyMap[num]!! > maxCount) {
                majorityElement = num
                maxCount = frequencyMap[num]!!
            }
        }

        return majorityElement
    }

    /**
     * O(n) time and space complexity solution using a freq map for each
     */
    fun majorityElement2(nums: IntArray): Int {
        // map: number -> how many times it has appeared so far
        val map = mutableMapOf<Int, Int>()
        // Tracks the current majority element and its freq. count
        var maxRepeat = Pair(nums[0], 1)
        nums.forEach {
            // Count this occurrence.
            map[it] = map.getOrDefault(it, 0) + 1
            // A new leader: this number has appeared more often than the previous leader.
            if (map[it]!! > maxRepeat.second) {
                if (map[it]!! > nums.size / 2) return it
                maxRepeat = Pair(it, map[it]!!)
            }
        }

        return maxRepeat.first
    }
}

fun main() {
    val solution = Solution00169()

    // Helper function to print test results
    fun runTest(testName: String, nums: IntArray, expected: Int) {
        println("Running $testName")
        println("Input: ${nums.joinToString(", ")}")
        try {
            val resultHashMap = solution.majorityElement(nums)
            val resultHashMap2 = solution.majorityElement2(nums)
            val resultBoyerMoore = solution.majorityElementBoyerMoore(nums)
            println("HashMap Result: $resultHashMap (Expected: $expected)")
            println("HashMap2 Result: $resultHashMap2 (Expected: $expected)")
            println("Boyer-Moore Result: $resultBoyerMoore (Expected: $expected)")
            println("HashMap Pass: ${resultHashMap == expected}")
            println("HashMap2 Pass: ${resultHashMap2 == expected}")
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
    // 6 appears 4 times out of 7, which is more than n/2, so it is a valid majority.
    // (With 8 elements and only four 6s there would be no majority, and the input would be invalid.)
    runTest("Test Case 4 - Mixed Order", intArrayOf(1, 6, 2, 6, 3, 6, 6), 6)

    // Test Case 6: Majority appears after a different leader (exposed a bug in majorityElement2)
    runTest("Test Case 6 - Majority After Another Leader", intArrayOf(1, 2, 2), 2)

    // Test Case 7: Majority is the whole second half
    runTest("Test Case 7 - Majority at the End", intArrayOf(1, 1, 2, 2, 2), 2)

    // Test Case 8: Two equal elements
    runTest("Test Case 8 - Two Elements", intArrayOf(2, 2), 2)

    // Test Case 9: Negative numbers
    runTest("Test Case 9 - Negative Numbers", intArrayOf(-1, -1, 2), -1)

    // Test Case 5: Edge Case - Empty Array
    runTest("Test Case 5 - Empty Array", intArrayOf(), -1) // -1 as placeholder since exception expected
}
