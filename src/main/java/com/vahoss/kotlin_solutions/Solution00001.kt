package com.vahoss.kotlin_solutions

import java.lang.IllegalStateException
import java.util.*

class Solution00001 {

    /**
     *  One-pass Hash Table solution
     */
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val map = mutableMapOf<Int, Int>()
        nums.forEachIndexed { i, num ->
            map[target - num]?.let {
                return intArrayOf(i, it)
            }
            map[num] = i
        }

        throw IllegalStateException("No valid answer was found!")
    }

    /**
     * Two-pass solution
     * --> Handles duplicates naturally (map stores the later index)
     */
    fun twoSum1(nums: IntArray, target: Int): IntArray {
        val map = mutableMapOf<Int, Int>()
        // First pass: build the hash map
        nums.forEachIndexed { i, num ->
            map[num] = i
        }

        nums.forEachIndexed { i, num ->
            val complement = target - num
            map[complement]?.let { j ->
                if (j != i) return intArrayOf(i, j)
            }
        }

        throw IllegalStateException("No valid answer was found!")
    }
}

fun main() {
    val sol = Solution00001()
    var nums = intArrayOf(2, 7, 11, 15)
    println(Arrays.toString(sol.twoSum(nums, 9)))
    println(Arrays.toString(sol.twoSum1(nums, 9)))

    nums = intArrayOf(2, 3, 4)
    println(Arrays.toString(sol.twoSum(nums, 6)))
    println(Arrays.toString(sol.twoSum1(nums, 6)))

    nums = intArrayOf(3, 3)
    println(Arrays.toString(sol.twoSum(nums, 6)))
    println(Arrays.toString(sol.twoSum1(nums, 6)))

    nums = intArrayOf(3, 2, 4)
    println(Arrays.toString(sol.twoSum(nums, 6)))
    println(Arrays.toString(sol.twoSum1(nums, 6)))

    nums = intArrayOf(-1, 0)
    println(Arrays.toString(sol.twoSum(nums, -1)))
    println(Arrays.toString(sol.twoSum1(nums, -1)))

    nums = intArrayOf(5, 25, 75)
    println(sol.twoSum(nums, 100).contentToString())
    println(sol.twoSum1(nums, 100).contentToString())
}
