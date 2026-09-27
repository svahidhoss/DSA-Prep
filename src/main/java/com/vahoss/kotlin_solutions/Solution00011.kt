package com.vahoss.kotlin_solutions

import kotlin.math.max
import kotlin.math.min

class Solution00011 {
    /**
     * Two pointers converging inward. At each step the shorter side is the
     * bottleneck - keeping it fixed while only shrinking the width can never
     * beat the area already recorded with it as a wall, so it's always safe
     * to discard the shorter side and move it inward.
     *
     * Time: O(n) - each pointer moves inward at most n times.
     * Space: O(1).
     */
    fun maxArea(height: IntArray): Int {
        var left = 0
        var right = height.lastIndex
        var best = 0

        while (left < right) {
            val minHeight = min(height[left], height[right])
            val area = minHeight * (right - left)
            best = max(best, area)

            if (height[left] < height[right]) left++
            else right--
        }

        return best
    }
}

fun main() {
    val sol = Solution00011()
    // Example 1
    var array = intArrayOf(1, 8, 6, 2, 5, 4, 8, 3, 7)
    println(sol.maxArea(array))

    // Example 2
    array = intArrayOf(1, 1)
    println(sol.maxArea(array))

    // Example 3
    array = intArrayOf(2, 3, 4, 5, 18, 17, 6)
    println(sol.maxArea(array))
}
