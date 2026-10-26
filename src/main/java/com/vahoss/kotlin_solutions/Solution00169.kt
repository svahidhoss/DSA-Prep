package com.vahoss.kotlin_solutions

class Solution00169 {

    /**
     * Boyer-Moore voting: keep a candidate and a vote count. An element equal to the
     * candidate adds a vote, and a different element cancels one vote. The majority element
     * appears more than n/2 times, so it has more votes than all the other elements combined
     * and survives the cancelling. Only correct because a majority element is guaranteed.
     *
     * Time Complexity: O(n), one pass.
     * Space Complexity: O(1), only the candidate and the counter.
     */
    fun majorityElementBoyerMoore(nums: IntArray): Int {
        require(nums.isNotEmpty())
        var result = nums.first()
        var counter = 0
        for (num in nums) {
            // No votes left: the current number becomes the new candidate.
            if (counter == 0) result = num

            // Same as the candidate: one more vote. Different: it cancels one vote.
            if (num == result) counter++ else counter--
        }
        return result
    }

    /**
     * O(n) time and space complexity solution using a freq map for each
     */
    fun majorityElement(nums: IntArray): Int {
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
            val resultBoyerMoore = solution.majorityElementBoyerMoore(nums)
            val resultHashMap = solution.majorityElement(nums)
            println("Boyer-Moore Result: $resultBoyerMoore (Expected: $expected)")
            println("HashMap Result: $resultHashMap (Expected: $expected)")
            println("Boyer-Moore Pass: ${resultBoyerMoore == expected}")
            println("HashMap Pass: ${resultHashMap == expected}")
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
}
