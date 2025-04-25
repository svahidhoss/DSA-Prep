package com.vahoss.amazon

import java.lang.IllegalStateException

class Solution00169 {
    fun majorityElement(nums: IntArray): Int {
        var count = 0
        var candidate = nums[0]
        nums.forEach { n ->
            if (count == 0) candidate = n
            if (n == candidate) count++ else count--
        }
        return candidate
    }

    fun majorityElement2(nums: IntArray): Int {
        nums.forEachIndexed { i, n ->
            var count = 0
            for (j in i until nums.size) {
                if (n == nums[j]) count++
                if (count > nums.size / 2) return n
            }
        }
        throw IllegalStateException("No results were found.")
    }
}

fun main() {

}