package com.vahoss.dp

class Solution00740 {
    fun deleteAndEarn(nums: IntArray): Int {
        // calculate the freq map
        val freqMap = mutableMapOf<Int, Int>()
        nums.forEach {
            freqMap[it] = freqMap.getOrDefault(it, 0) + it
        }

        val maxNum = nums.maxOrNull() ?: 0
        val dpMap = mutableMapOf<Int, Int>()

        return dp(maxNum, freqMap, dpMap)
    }

    private fun dp(n: Int, freqMap: MutableMap<Int, Int>, dpMap: MutableMap<Int, Int>): Int {
        if (n < 1) return 0
        if (n == 1) return freqMap.getOrDefault(1, 0)
        return dpMap.getOrPut(n) {
            Math.max(dp(n - 2, freqMap, dpMap) + freqMap.getOrDefault(n, 0), dp(n - 1, freqMap, dpMap))
        }
    }
}

fun main() {
    val s = Solution00740()
    println(s.deleteAndEarn(intArrayOf(2, 2, 3, 3, 3, 4)))
    println(s.deleteAndEarn(intArrayOf(3, 4, 2)))
}