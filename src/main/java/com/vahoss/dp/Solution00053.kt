package com.vahoss.dp

import kotlin.math.max

class Solution00053 {

    /**
     * Brute force approach
     * O(n^2)
     */
    fun maxSubArrayBruteForce(nums: IntArray): Int {
        var result = Int.MIN_VALUE
        var currentMax: Int
        for (i in nums.indices) {
            currentMax = 0
            for (j in i until nums.size) {
                currentMax += nums[j]
                if (currentMax > result) result = currentMax
            }
        }

        return result
    }

    fun maxSubArray(nums: IntArray): Int {
        var result = Int.MIN_VALUE
        var currentMax = Int.MIN_VALUE
        for (num in nums) {
            // At each, decide whether to start fresh or keep calculating the sum
            currentMax = max(currentMax + num, num)
            result = max(result, currentMax)
        }

        return result
    }
}

fun main() {
    val sol = Solution00053()
    println(sol.maxSubArray(intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4)))
    println(sol.maxSubArray(intArrayOf(1)))
    println(sol.maxSubArray(intArrayOf(5, 4, -1, 7, 8)))
}
