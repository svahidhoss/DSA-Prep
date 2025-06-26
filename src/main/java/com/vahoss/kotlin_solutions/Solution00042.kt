package com.vahoss.kotlin_solutions

import kotlin.math.min

class Solution00042 {

    /**
     * Resource	Cost
     * Time	    O(n)
     * Space	O(n)
     */
    fun trap(height: IntArray): Int {
        val maxSinceBeg = IntArray(height.size)
        val maxFromEnd = IntArray(height.size)

        var max = 0
        for ((i, value) in height.withIndex()) {
            maxSinceBeg[i] = maxOf(value, max)
            max = maxSinceBeg[i]
        }

        max = 0
        for (i in height.lastIndex downTo 0) {
            maxFromEnd[i] = maxOf(height[i], max)
            max = maxFromEnd[i]
        }

        var result = 0
        // calculate the result:
        for (i in 1 until height.size - 1) {
            val minOfMax = min(maxSinceBeg[i - 1], maxFromEnd[i + 1])
            if (minOfMax > height[i]) result += minOfMax - height[i]
        }

        return result
    }
}

fun main() {
    val sol = Solution00042()
    println(sol.trap(intArrayOf(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1)))
    println(sol.trap(intArrayOf(4, 2, 0, 3, 2, 5)))
}
