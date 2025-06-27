package com.vahoss.kotlin_solutions

import kotlin.math.max
import kotlin.math.min

class Solution00042 {

    /**
     * This is a constant space solution
     */
    fun trap(height: IntArray): Int {
        if (height.size < 2) return 0
        var maxSinceBeg = height.first()
        var maxFromEnd = height.last()

        var result = 0
        var left = 0
        var right = height.lastIndex
        while (left < right) {
            if (maxSinceBeg <= maxFromEnd) {
                val diff = maxSinceBeg - height[left]
                result += maxOf(0, diff)
                maxSinceBeg = max(maxSinceBeg, height[left])
                left++
            } else {
                val diff = maxFromEnd - height[right]
                result += maxOf(0, diff)
                maxFromEnd = max(maxFromEnd, height[right])
                right--
            }
        }

        return result
    }

    /**
     * Resource	Cost
     * Time	    O(n)
     * Space	O(n)
     */
    fun trap2(height: IntArray): Int {
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
    println(sol.trap(intArrayOf(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1)))   // expected 6
    println(sol.trap(intArrayOf(4, 2, 0, 3, 2, 5)))                     // expected 9
    println(sol.trap(intArrayOf(2, 0, 2)))                              // expected 2
}
