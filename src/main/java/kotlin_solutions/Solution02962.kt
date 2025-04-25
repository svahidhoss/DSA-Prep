package main.java.kotlin_solutions

import main.java.BinarySearchFirstTrue
import main.java.chi
import main.java.torsq

class Solution02962 {
    fun countSubarrays(nums: IntArray, k: Int): Long {
        val max = nums.max()
        var maxRepeat = 0
        for (n in nums) {
            if (n == max) maxRepeat++
        }

        if (maxRepeat < k) return 0

        var count = 0L

        val maxIndices = mutableListOf<Int>()
        for (i in nums.indices) {
            if (nums[i] == max) maxIndices.add(i)
        }

//        if (maxIndices.size >= k)
//            for (j in maxIndices)
//                count += i - j


        return count
    }

    fun countSubarrays2(array: IntArray, k: Int): Int {
        val max = array.max()
        var result = 0
        var left = 0
        val frequencyMap = mutableMapOf<Int, Int>()

        for (right in array.indices) {
            frequencyMap[array[right]] = frequencyMap.getOrDefault(array[right], 0) + 1

            while ((frequencyMap[max] ?: 0) < k && left <= right) {
                frequencyMap[array[left]] = frequencyMap[array[left]]!! - 1
                if (frequencyMap[array[left]] == 0) {
                    frequencyMap.remove(array[left])
                }
                left++
            }

            if ((frequencyMap[max] ?: 0) >= k) {
                result += right - left + 1
            }
        }

        return result
    }

    fun countSubarrays3(nums: IntArray, k: Int): Long {
        val max = nums.maxOrNull()!!
        val n = nums.size
        val that = IntArray(n) { (nums[it] == max).chi }

        val Q = that.torsq()
        var ret = 0L
        for (l in 0 until n) {
            val first = BinarySearchFirstTrue(l + 1, n) {
                Q.sumQuery(l, it - 1) >= k
            } ?: continue
            ret += n - first + 1
        }
        return ret
    }
}

fun main() {
    // This will print the number of sub-arrays where max is repeated at least k times
    println(Solution02962().countSubarrays3(intArrayOf(1, 3, 2, 3, 3), 2))
    println(Solution02962().countSubarrays3(intArrayOf(1, 4, 2, 1), 3))
    println(Solution02962().countSubarrays3(intArrayOf(1, 4, 2, 1), 1))
}

fun fizzBuzz(n: Int) {
    // Write your code here
    for (i in 1..n) {
        val value = when {
            i % 15 == 0 -> "FizzBuzz"
            i % 3 == 0 -> "Fizz"
            i % 5 == 0 -> "Buzz"
            else -> i
        }
        println(value)
    }
}