package com.vahoss.amazon

import java.util.Arrays

class Amazon20241005Prep {
    fun fizzBuzz(n: Int) {
        // Write your code here
        val sb = StringBuilder()
        for (i in 1..n) {
            sb.clear()
            if (i % 3 == 0) sb.append("Fizz")
            if (i % 5 == 0) sb.append("Buzz")

            if (sb.isEmpty()) println(i)
            else println(sb.toString())
        }
    }

    fun minimalHeaviestSetA(arr: Array<Int>): Array<Int> {
        var remSum = arr.sum()
        arr.sort()

        var currSum = 0
        var i = arr.lastIndex
        while (i >= 0 && currSum < remSum) {
            val currVal = arr[i--]
            currSum += currVal
            remSum -= currVal
        }

        return if (currSum > remSum) arr.sliceArray(i + 1..arr.lastIndex)
        else emptyArray()
    }

    /**
     *  1234567
     *  |**|*|*
     *  0002233
     * 00002233
     */
    fun numberOfItems(s: String, startIndices: Array<Int>, endIndices: Array<Int>): Array<Int> {
        // Write your code here

        // create a prefix sum array to efficiently calculate the number of items
        val preSum = IntArray(s.length)
        var currentCount = 0

        for (i in 1..<s.length) {
            if (s[i] == '|') preSum[i] = currentCount
            else if (s[i] == '*') {
                currentCount++
                preSum[i] = preSum[i - 1]
            }
        }

        // Calculate the number of items for each query
        val result = Array(startIndices.size) { 0 }
        for (i in startIndices.indices) {
            // adjust indices (1-based to 0-based)
            val start = startIndices[i] - 1
            val end = endIndices[i] - 1

            // search for the first and last compartment within the range
            val firstCompartment = s.indexOf('|', start)
            val lastCompartment = s.lastIndexOf('|', end)

            // Calculate the number of items between the compartments
            if (firstCompartment < lastCompartment) {
                result[i] = preSum[lastCompartment] - preSum[firstCompartment]
            }
        }

        return result
    }
}

fun main() {
    val a = Amazon20241005Prep()
    println(a.fizzBuzz(15))

    println(Arrays.toString(a.minimalHeaviestSetA(arrayOf(5,3,2,4,1,2))))

    println(Arrays.toString(a.numberOfItems("*|*|*|", arrayOf(1, 1), arrayOf(1, 6))))
    println(Arrays.toString(a.numberOfItems("*|*|", arrayOf(1), arrayOf(3))))
    println(Arrays.toString(a.numberOfItems("|**|*|*", arrayOf(1, 1), arrayOf(5, 6))))
}