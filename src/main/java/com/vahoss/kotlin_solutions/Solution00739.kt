package com.vahoss.kotlin_solutions

import java.util.Arrays

class Solution00739 {
    /**

    Input: temperatures = [73,74,75,71,69,72,76,73]
    p  q
    O(n^2)

    Hashmap:
    73 0,7
    74 1
    75 2
    71 3
    69 4
    72 5
    76 6
    space: O(n)
    time:O((max - min) * n)
    time:O((70) * n)
    time:O(Cn)
    time:O(n)


    Output: [1,1,4,2,1,1,0,0]

    30 <= temperatures[i] <= 100

     */
    fun dailyTemperatures(temperatures: IntArray): IntArray {
        val result = IntArray(temperatures.size)
        if (temperatures.size <= 1) return result

        val stack = ArrayDeque<Int>()

        for ((i, currTemp) in temperatures.withIndex()) {
            // Keep popping while current temp is warmer than stack top
            while (stack.isNotEmpty() && temperatures[stack.last()] < currTemp) {
                result[stack.last()] = i - stack.removeLast()
            }
            stack.addLast(i)
        }

        return result
    }

    /**
     * Times out: O(n^2)
     */
    fun dailyTemperatures2(temperatures: IntArray): IntArray {
        val result = IntArray(temperatures.size)
        for ((i, temp) in temperatures.withIndex()) {
            for (j in i + 1..temperatures.lastIndex) {
                if (temp < temperatures[j]) {
                    result[i] = j - i
                    break
                }
            }
        }

        return result
    }
}

fun main() {
    val s = Solution00739()

    println(
        Arrays.toString(
            s.dailyTemperatures(intArrayOf(55, 38, 53, 81, 61, 93, 97, 32, 43, 78))
        )
    ) // Expected: [3,1,1,2,1,1,0,1,1,0]

    println(
        Arrays.toString(
            s.dailyTemperatures(
                intArrayOf(
                    73,
                    74,
                    75,
                    71,
                    69,
                    72,
                    76,
                    73
                )
            )
        )
    ) // Expected: [1,1,4,2,1,1,0,0]

    println(
        Arrays.toString(
            s.dailyTemperatures(intArrayOf(30, 60, 90))
        )
    ) // Expected: [1,1,0]

    println(
        Arrays.toString(
            s.dailyTemperatures(intArrayOf(30, 40, 50, 60))
        )
    ) // Expected: [1,1,1,0]
}
