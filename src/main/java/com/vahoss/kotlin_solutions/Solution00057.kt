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
    /**
     * My last attempt.
     * Single-pass linear scan: walk forward to find the first overlapping interval,
     * merge greedily while overlap continues, then copy the rest.
     * Time: O(n)
     * Space: O(n) for the result list
     */
    fun insert0(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
        if (intervals.isEmpty()) return arrayOf(newInterval)
        val result = mutableListOf<IntArray>()
        var firstNotFound = true
        var i = 0
        while (i < intervals.size) {
            val s = intervals[i][0]
            val e = intervals[i][1]

            // Find the first matching interval
            if (e >= newInterval.first() && firstNotFound) {
                firstNotFound = false
                if (s > newInterval.last()) {
                    // newInterval ends before this interval — no overlap, insert before
                    result.add(newInterval)
                    result.add(intArrayOf(s, e))
                } else {
                    // overlap — merge
                    val newS = minOf(s, newInterval.first())
                    var newE = maxOf(e, newInterval.last())
                    i++
                    while (i < intervals.size && intervals[i][0] <= newInterval.last()) {
                        newE = maxOf(newE, intervals[i][1])
                        i++
                    }
                    result.add(intArrayOf(newS, newE))
                    continue
                }
            } else {
                // add the other intervals as is
                result.add(intArrayOf(s, e))
            }
            i++
        }
        if (firstNotFound) result.add(newInterval)
        return result.toTypedArray()
    }


    /**
     * Two-pointer approach: find the merge range [p, q] then reconstruct in three parts.
     * p = first interval newInterval can overlap (newIntervalStart <= intervals\[p].end)
     * q = last interval newInterval can overlap (newIntervalEnd >= intervals\[q].start)
     * If p > q, newInterval fits in a gap with no overlaps.
     * Time: O(n)
     * Space: O(n) for the result list
     */
    fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
        if (intervals.isEmpty()) return arrayOf(newInterval)

        var p = 0
        var q = intervals.lastIndex
        val newIntervalStart = newInterval.first()
        val newIntervalEnd = newInterval.last()

        val result = mutableListOf<IntArray>()

        // advance p past all intervals that end before newInterval starts
        while (p < intervals.size && newIntervalStart > intervals[p][1]) p++
        // retreat q past all intervals that start after newInterval ends
        while (q >= 0 && newIntervalEnd < intervals[q][0]) q--

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
            mergedInterval[0] = minOf(intervals[p][0], newIntervalStart)
            mergedInterval[1] = maxOf(intervals[q][1], newIntervalEnd)
            result.add(mergedInterval)
        }

        // Add intervals after the merge range
        for (i in q + 1 until intervals.size) {
            result.add(intervals[i])
        }
        return result.toTypedArray()
    }
}

fun main() {
    val sol = Solution00057()

    fun check(label: String, result: Array<IntArray>, expected: Array<IntArray>) {
        val pass = result.size == expected.size && result.zip(expected).all { (a, b) -> a.contentEquals(b) }
        val resultStr = result.joinToString { it.contentToString() }
        println("${if (pass) "PASS" else "FAIL"} [$label] got: $resultStr")
    }

    // Basic overlap in the middle
    check(
        "ex1 insert0", sol.insert0(arrayOf(intArrayOf(1, 3), intArrayOf(6, 9)), intArrayOf(2, 5)),
        arrayOf(intArrayOf(1, 5), intArrayOf(6, 9))
    )
    check(
        "ex1 insert ", sol.insert(arrayOf(intArrayOf(1, 3), intArrayOf(6, 9)), intArrayOf(2, 5)),
        arrayOf(intArrayOf(1, 5), intArrayOf(6, 9))
    )

    // Overlap spans multiple intervals
    check(
        "ex2 insert0",
        sol.insert0(
            arrayOf(
                intArrayOf(1, 2),
                intArrayOf(3, 5),
                intArrayOf(6, 7),
                intArrayOf(8, 10),
                intArrayOf(12, 16)
            ), intArrayOf(4, 8)
        ),
        arrayOf(intArrayOf(1, 2), intArrayOf(3, 10), intArrayOf(12, 16))
    )
    check(
        "ex2 insert ",
        sol.insert(
            arrayOf(intArrayOf(1, 2), intArrayOf(3, 5), intArrayOf(6, 7), intArrayOf(8, 10), intArrayOf(12, 16)),
            intArrayOf(4, 8)
        ),
        arrayOf(intArrayOf(1, 2), intArrayOf(3, 10), intArrayOf(12, 16))
    )

    // No overlap — insert before all
    check(
        "before insert0", sol.insert0(arrayOf(intArrayOf(3, 5), intArrayOf(7, 9)), intArrayOf(1, 2)),
        arrayOf(intArrayOf(1, 2), intArrayOf(3, 5), intArrayOf(7, 9))
    )
    check(
        "before insert ", sol.insert(arrayOf(intArrayOf(3, 5), intArrayOf(7, 9)), intArrayOf(1, 2)),
        arrayOf(intArrayOf(1, 2), intArrayOf(3, 5), intArrayOf(7, 9))
    )

    // No overlap — insert after all
    check(
        "after insert0", sol.insert0(arrayOf(intArrayOf(1, 2), intArrayOf(3, 5)), intArrayOf(7, 9)),
        arrayOf(intArrayOf(1, 2), intArrayOf(3, 5), intArrayOf(7, 9))
    )
    check(
        "after insert ", sol.insert(arrayOf(intArrayOf(1, 2), intArrayOf(3, 5)), intArrayOf(7, 9)),
        arrayOf(intArrayOf(1, 2), intArrayOf(3, 5), intArrayOf(7, 9))
    )

    // Empty intervals
    check(
        "empty insert0", sol.insert0(emptyArray(), intArrayOf(1, 5)),
        arrayOf(intArrayOf(1, 5))
    )
    check(
        "empty insert ", sol.insert(emptyArray(), intArrayOf(1, 5)),
        arrayOf(intArrayOf(1, 5))
    )
}
