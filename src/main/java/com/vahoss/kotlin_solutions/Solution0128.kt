package com.vahoss.kotlin_solutions

import kotlin.math.max

class Solution0128 {
    fun longestConsecutive2(nums: IntArray): Int {
        var result = 0

        // create map with false value for visits
        val nMap = mutableMapOf<Int, Boolean>()
        for (num in nums) {
            nMap[num] = false
        }

        for ((num, visited) in nMap) {
            if (!visited) {
                // Make the current num visited
                var d = num
                var len = 1
                nMap[num] = true

                // start upward trend
                while (nMap.contains(--d) && nMap[d]!!) {
                    nMap[d] = true
                    len++
                }
                // start downward trend
                d = num
                while (nMap.contains(++d) && nMap[d]!!) {
                    nMap[d] = true
                    len++
                }

                result = max(result, len)
            }
        }

        return result
    }


    fun longestConsecutive(nums: IntArray): Int {
        var result = 0

        // create map with false value for visits
        val set = nums.toSet()

        for (num in set) {
            // Only start counting at the beg of a sequence
            if (!set.contains(num - 1)) {
                // Make the current num visited
                var d = num
                var len = 1

                // start upward trend
                while (set.contains(++d)) {
                    len++
                }

                result = max(result, len)
            }
        }

        return result
    }
}

fun main() {
    val sol = Solution0128()
    println(sol.longestConsecutive(intArrayOf(100, 4, 200, 1, 3, 2)))
    println(sol.longestConsecutive(intArrayOf(0, 3, 7, 2, 5, 8, 4, 6, 0, 1)))

}