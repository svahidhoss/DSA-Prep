package com.vahoss.kotlin_solutions

import java.lang.IllegalStateException
import java.util.*

class Solutions00167 {

    /**
     * Uses a two pointer approach.
     */
    fun twoSum(numbers: IntArray, target: Int): IntArray {
        var p = 0
        var q = numbers.size - 1

        while (p < q) {
            val sum = numbers[p] + numbers[q]
            when {
                sum == target -> return intArrayOf(p + 1, q + 1)
                sum < target -> p++
                else -> q--
            }
        }

        throw IllegalStateException("No valid answer was found!")
    }

    fun twoSum2(numbers: IntArray, target: Int): IntArray {
        var i2: Int
        for ((i, v) in numbers.withIndex()) {
            i2 = numbers.binarySearch(target - v, i + 1)
            if (i2 > 0) return intArrayOf(i + 1, i2 + 1)
        }

        throw IllegalStateException("No valid answer was found!")
    }
}

fun main() {
    val sol = Solutions00167()
    var nums = intArrayOf(2, 7, 11, 15)
    println(Arrays.toString(sol.twoSum(nums, 9)))
    nums = intArrayOf(2, 3, 4)
    println(Arrays.toString(sol.twoSum(nums, 6)))
    nums = intArrayOf(5, 25, 75)
    println(Arrays.toString(sol.twoSum(nums, 100)))
    nums = intArrayOf(3, 3)
    println(sol.twoSum(nums, 6).contentToString())
    nums = intArrayOf(-1, 0)
    println(sol.twoSum(nums, -1).contentToString())
}