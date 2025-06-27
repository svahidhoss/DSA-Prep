package com.vahoss.kotlin_solutions

data class Schedule(val startTime: Int, val endTime: Int, val profit: Int)

class Solution01235 {
    fun jobScheduling(startTime: IntArray, endTime: IntArray, profit: IntArray): Int {
        val memo = mutableMapOf<Int, Int>()
        val schedules = ArrayList<Schedule>()
        for (i in startTime.indices) {
            schedules.add(Schedule(startTime[i], endTime[i], profit[i]))
        }
        schedules.sortBy { it.startTime }
        return jobScheduling(schedules, 0, memo)
    }

    private fun jobScheduling(schedules: ArrayList<Schedule>, startTimeIndex: Int, memo: MutableMap<Int, Int>): Int {

        if (memo.containsKey(startTimeIndex)) return memo[startTimeIndex]!!

        var result = 0
        for (i in startTimeIndex until schedules.size) {
            // index of first element >= current end time
            val idx = schedules.binarySearch {it.startTime.compareTo(schedules[i].endTime)}
            val nextStartTimeIndex = if (idx >= 0) idx else -idx - 1

            val current = schedules[i].profit + jobScheduling(schedules, nextStartTimeIndex, memo)
            result = maxOf(result, current)
        }

        memo[startTimeIndex] = result
        return result
    }
}

fun main() {
    val sol = Solution01235()
    println(
        sol.jobScheduling(
            startTime = intArrayOf(1, 2, 3, 3),
            endTime = intArrayOf(3, 4, 5, 6),
            profit = intArrayOf(50, 10, 40, 70)
        )
    ) // Output: 120

    println(
        sol.jobScheduling(
            intArrayOf(1, 2, 3, 4, 6),
            endTime = intArrayOf(3, 5, 10, 6, 9),
            profit = intArrayOf(20, 20, 100, 70, 60)
        )
    ) //Output: 150

    println(
        sol.jobScheduling(
            intArrayOf(1, 1, 1),
            intArrayOf(2, 3, 4),
            intArrayOf(5, 6, 4)
        )
    ) // Output: 6

    println(
        sol.jobScheduling(
            intArrayOf(4, 2, 4, 8, 2),
            intArrayOf(5, 5, 5, 10, 8),
            intArrayOf(5, 5, 5, 10, 8)
        )
    ) // Output: 6
}
