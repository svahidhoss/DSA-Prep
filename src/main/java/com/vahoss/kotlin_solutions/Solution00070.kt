package com.vahoss.kotlin_solutions

class Solution00070 {
    fun climbStairs(n: Int): Int {
        val memo = mutableMapOf(1 to 1, 2 to 2)
        memo[n] = dp(n, memo)
        return memo[n]!!
    }

    private fun dp(n: Int, memo: MutableMap<Int, Int>): Int {
        if (memo.contains(n)) return memo[n]!!
        memo[n] = dp(n - 1, memo) + dp(n - 2, memo)
        return memo[n]!!
    }
}

fun main() {
    val s = Solution00070()
    println(s.climbStairs(2))
    println(s.climbStairs(3))
    println(s.climbStairs(10))
}