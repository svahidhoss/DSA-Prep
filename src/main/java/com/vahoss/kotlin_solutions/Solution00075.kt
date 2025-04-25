package com.vahoss.kotlin_solutions

class Solution00075 {
    fun sortColors(nums: IntArray) {
        var zeroCount = 0
        var oneCount = 0
        nums.forEachIndexed { i, v ->
            when (v) {
                0 -> zeroCount++
                1 -> oneCount++
            }
        }

        for (i in nums.indices) {
            if (i < zeroCount) nums[i] = 0
            else if (i < oneCount + zeroCount) nums[i] = 1
            else nums[i] = 2
        }
    }

    fun sortColors2(nums: IntArray): IntArray {
        var p = 0
        var q = nums.size - 1
        var i = 0
        while (i <= q) {
            val v = nums[i]
            when (v) {
                0 -> swap(p++, i++, nums)
                // Do not increment i here because we need to check the new value at index i
                2 -> swap(q--, i, nums)
                1 -> i++
            }
        }

        return nums
    }

    private fun swap(p: Int, q: Int, a: IntArray) {
        val temp = a[p]
        a[p] = a[q]
        a[q] = temp
    }

    fun sortColors3(nums: IntArray) {
        var p = 0
        for (i in 0..2) {
            nums.forEachIndexed { j, v ->
                if (v == i) {
                    swap(p, j, nums)
                    p++
                }
            }
        }
    }
}

fun main() {
    val s = Solution00075()
    println(s.sortColors2(intArrayOf(2, 0, 2, 1, 1, 0)).contentToString())
    println(s.sortColors2(intArrayOf(2, 0, 1)).contentToString())
    println(s.sortColors2(intArrayOf(1, 2, 0)).contentToString())
}