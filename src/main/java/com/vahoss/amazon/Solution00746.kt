package com.vahoss.amazon

import kotlin.math.min

class Solution00746 {
    fun minCostClimbingStairs(cost: IntArray): Int {
        val minCostArray = IntArray(cost.size + 1)

        for (i in 2 until minCostArray.size) {
            minCostArray[i] = min(minCostArray[i - 1] + cost[i - 1], minCostArray[i - 2] + cost[i - 2])
        }

        return minCostArray.last()
    }

    fun minCostClimbingStairs2(cost: IntArray): Int {
        return dp(cost.size, cost)
    }

    val memo = mutableMapOf<Int, Int>()

    private fun dp(n: Int, cost: IntArray): Int {
        if (n == 0 || n == 1) return 0
        return memo.getOrPut(n) {
            val dp1 = dp(n - 1, cost) + cost[n - 1]
            val dp2 = dp(n - 2, cost) + cost[n - 2]
            min(dp1, dp2)
        }
    }
}

fun main() {
    val s = Solution00746()
    println(s.minCostClimbingStairs(intArrayOf(10, 15, 20)))
    println(s.minCostClimbingStairs(intArrayOf(0, 10, 5, 2)))
    println(s.minCostClimbingStairs(intArrayOf(1, 100, 1, 1, 1, 100, 1, 1, 100, 1)))
}