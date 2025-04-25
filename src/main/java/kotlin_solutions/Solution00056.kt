package main.java.kotlin_solutions

import kotlin.math.max

class Solution0056 {
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        // sort by first element
        intervals.sortBy { it[0] }

        val results = mutableListOf<IntArray>()
        intervals.forEach {
            // compare the end of the 2nd interval with the beg of the 1st
            if (results.isEmpty() || results.last()[1] < it[0]) results.add(it)
            else results.last()[1] = max(results.last()[1], it[1])
        }

        return results.toTypedArray()
    }
}

fun main() {
    val sol = Solution0056()
    var intervals = arrayOf(intArrayOf(1, 3), intArrayOf(2, 6), intArrayOf(8, 10), intArrayOf(15, 18))
    println(sol.merge(intervals).contentDeepToString())
    intervals = arrayOf(intArrayOf(1, 4), intArrayOf(4, 5))
    println(sol.merge(intervals).contentDeepToString())
    intervals = arrayOf(intArrayOf(1, 4), intArrayOf(2, 3))
    println(sol.merge(intervals).contentDeepToString())
}
