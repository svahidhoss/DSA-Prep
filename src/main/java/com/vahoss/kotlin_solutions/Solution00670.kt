package com.vahoss.kotlin_solutions

import kotlin.math.max

class Solution00670 {
    fun maximumSwap(num: Int): Int {
        val digits = num.toString().map { it.toString().toInt() }.toMutableList()
        // counter array
        val digitIndexArray = IntArray(10) { -1 }

        // Store rightmost index of each digit
        digits.forEachIndexed { i, v ->
            digitIndexArray[v] = max(digitIndexArray[v], i)
        }

        // first index to change
        var left = 0
        for (i in digitIndexArray.size - 1 downTo 1) { // do not include first index: 0
            // Do the swap if the digit exists
            val indexToSwap = digitIndexArray[i]
            while (left < indexToSwap) {
                val temp = digits[left]
                if (temp < i) {
                    digits[left] = digits[indexToSwap]
                    digits[indexToSwap] = temp
                    // once a swap is done, exit
                    // covert digits back to the result
                    var result = 0
                    for (digit in digits) {
                        result = result * 10 + digit
                    }
                    return result
                }
                left++
            }
            // reset left index of swap
            left = 0
        }

        // no swap happened
        return num
    }

    fun maximumSwap2(num: Int): Int {
        val digits = num.toString().map { it.toString().toInt() }.toMutableList()
        val digitIndexArray = IntArray(10) { -1 }

        // Store rightmost index of each digit
        digits.forEachIndexed { i, v ->
            digitIndexArray[v] = i
        }

        // Check each position from left to right
        for (i in digits.indices) {
            // Look for the largest digit that appears after position i
            for (digit in 9 downTo digits[i] + 1) {
                val indexToSwap = digitIndexArray[digit]
                if (indexToSwap > i) {
                    // Swap and return immediately
                    val temp = digits[i]
                    digits[i] = digits[indexToSwap]
                    digits[indexToSwap] = temp

                    return digits.joinToString("").toInt()
                }
            }
        }

        return num // No beneficial swap found
    }
}

fun main() {
    val sol = Solution00670()
    println(sol.maximumSwap(2736))  // expected: 7236
    println(sol.maximumSwap(9973))
    println(sol.maximumSwap(0))
    println(sol.maximumSwap(100000000))
    println(sol.maximumSwap(98368)) // expected: 98863
    println(sol.maximumSwap(1993)) // expected: 98863
}
