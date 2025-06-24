package com.vahoss.kotlin_solutions

/**
 *
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 *                      p q
 * Output: [[1,5],[6,9]]
 *
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 *                             p           q
 * Output: [[1,2],[3,10],[12,16]]
 *
 */
class Solution00057 {
    fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
        if (intervals.isEmpty()) return arrayOf(newInterval)

        var p = 0
        var q = intervals.lastIndex
        val newIntervalStart = newInterval.first()
        val newIntervalEnd = newInterval.last()

        val result = mutableListOf<IntArray>()

        while (p < intervals.size && newIntervalStart > intervals[p][1]) {
            p++
        }
        while (q >= 0 && newIntervalEnd < intervals[q][0]) {
            q--
        }

        // Add intervals before the merge range
        for (i in 0 until p) {
            result.add(intervals[i])
        }

        // Handle the case where newInterval doesn't overlap with any existing interval
        if (p > q) {
            result.add(newInterval)
        } else {
            // Create the actual merge interval
            val mergedInterval = IntArray(2)
            mergedInterval[0] = Math.min(intervals[p][0], newIntervalStart)
            mergedInterval[1] = Math.max(intervals[q][1], newIntervalEnd)
            result.add(mergedInterval)
        }

        // Add intervals after the merge range
        for (i in q + 1 until intervals.size) {
            result.add(intervals[i])
        }
        return result.toTypedArray()
    }
}
