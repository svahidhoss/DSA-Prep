package com.vahoss.kotlin_solutions

class Solution0435 {
    /**
     * Greedy Work here because:
     *
     * You're always choosing the next interval that ends earliest
     * This gives you maximum space to add future intervals
     *
     * This same greedy technique shows up in:
     * Activity selection problems
     * Scheduling without conflicts
     */
    fun eraseOverlapIntervals(intervals: Array<IntArray>): Int {
        // sort by end time
        intervals.sortBy { it[1] }
        var result = 0
        var prevEnd = Int.MIN_VALUE
        for (interval in intervals) {
            // no overlap wanted: remove any interval that starts before the prev-end
            if (interval[0] >= prevEnd) prevEnd = interval[1]
            else result++
        }

        return result
    }
}

fun main() {
    val testCases = listOf(
        Triple(
            arrayOf(
                intArrayOf(1, 2),
                intArrayOf(2, 3),
                intArrayOf(3, 4),
                intArrayOf(1, 3)
            ),
            1,
            "Remove [1,3]"
        ),
        Triple(
            arrayOf(
                intArrayOf(1, 2),
                intArrayOf(1, 2),
                intArrayOf(1, 2)
            ),
            2,
            "Keep only one [1,2]"
        ),
        Triple(
            arrayOf(
                intArrayOf(1, 2),
                intArrayOf(2, 3)
            ),
            0,
            "No overlaps"
        ),
        Triple(
            arrayOf(
                intArrayOf(1, 100),
                intArrayOf(11, 22),
                intArrayOf(1, 11),
                intArrayOf(2, 12)
            ),
            2,
            "Remove 2 to avoid overlap with [1,100]"
        )
    )
    val s = Solution0435()
    for ((i, testCase) in testCases.withIndex()) {
        val (intervals, expected, desc) = testCase
        val result = s.eraseOverlapIntervals(intervals)
        println("Test Case $i: $desc")
        println("Expected: $expected, Got: $result")
        println("------------")
    }
}