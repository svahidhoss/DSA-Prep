package com.vahoss.kotlin_solutions

import java.util.*

class Solution00977 {
    fun sortedSquares(nums: IntArray): IntArray {
        val numsSquared = nums.map { it * it }
        val result = IntArray(nums.size)

        var p = 0
        var q = nums.size - 1
        var i = nums.size - 1

        while (p <= q) {
            if (numsSquared[p] > numsSquared[q]) result[i] = numsSquared[p++]
            else result[i] = numsSquared[q--]
            i--
        }
        return result
    }

    fun sortedSquaresSort(nums: IntArray): IntArray {
        return nums.map { n -> n * n }.sorted().toIntArray()
    }
}

class Solution00360 {
    fun sortTransformedArray3(nums: IntArray, a: Int, b: Int, c: Int): IntArray {
        val result = IntArray(nums.size)

        var index = if (a < 0) 0 else nums.size - 1
        var p = 0
        var q = nums.size - 1
        while (p <= q) {
            val transformP = transform(nums[p], a, b, c)
            val transformQ = transform(nums[q], a, b, c)
            if (a < 0) {
                if (transformP <= transformQ) {
                    result[index] = transformP
                    p++
                } else {
                    result[index] = transformQ
                    q--
                }
                index++
            } else {
                if (transformP >= transformQ) {
                    result[index] = transformP
                    p++
                } else {
                    result[index] = transformQ
                    q--
                }
                index--
            }
        }


        return result
    }

    fun sortTransformedArray2(nums: IntArray, a: Int, b: Int, c: Int): IntArray {
        return nums.map { transform(it, a, b, c) }.sorted().toIntArray()
    }

    fun sortTransformedArray(nums: IntArray, a: Int, b: Int, c: Int): IntArray {
        val result = IntArray(nums.size)

        var p = 0
        var q = nums.size - 1
        var i = if (a >= 0) nums.size - 1 else 0 // Determine the starting index based on the parabola opening direction

        while (p <= q) {
            val transformP = transform(nums[p], a, b, c)
            val transformQ = transform(nums[q], a, b, c)

            if (a >= 0) {
                // For a parabola that opens upwards, the largest values are on the ends
                if (transformP >= transformQ) {
                    result[i--] = transformP
                    p++
                } else {
                    result[i--] = transformQ
                    q--
                }
            } else {
                // For a parabola that opens downwards, the smallest values are on the ends
                if (transformP <= transformQ) {
                    result[i++] = transformP
                    p++
                } else {
                    result[i++] = transformQ
                    q--
                }
            }
        }

        return result
    }

    private fun transform(num: Int, a: Int, b: Int, c: Int): Int {
        return a * num * num + b * num + c
    }
}

fun main() {
    /*val s = Solution00977()
    println(Arrays.toString(s.sortedSquares(intArrayOf(-4, -1, 0, 3, 10))))
    println(Arrays.toString(s.sortedSquares(intArrayOf(-7, -3, 2, 3, 11))))*/
    val sol = Solution00360()
    println(Arrays.toString(sol.sortTransformedArray3(intArrayOf(-4, -2, 2, 4), a = 1, b = 3, c = 5)))
    println(Arrays.toString(sol.sortTransformedArray3(intArrayOf(-4, -2, 2, 4), a = -1, b = 3, c = 5)))
}
