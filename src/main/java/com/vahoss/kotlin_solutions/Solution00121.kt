package com.vahoss.kotlin_solutions

import kotlin.math.max
import kotlin.math.min

class Solution00121 {

    /**
     * Single pass solution: the most optimized.
     * Time complexity: O(n) - single pass
     * Space complexity: O(1) - only two variables
     */
    fun maxProfit0(prices: IntArray): Int {
        var min = Int.MAX_VALUE
        var profit = 0

        for (price in prices) {
            min = min(min, price)
            profit = max(profit, price - min)
        }

        return profit
    }

    /**
     * O(n) solution. At each step:
     *
     * You’ve seen all prices up to this point.
     * You’ve tracked the best buying opportunity so far (minPrice).
     */
    fun maxProfit(prices: IntArray): Int {
        var minPrice = Int.MAX_VALUE
        var maxProfit = Int.MIN_VALUE

        prices.forEach { price ->
            if (price < minPrice) minPrice = price
            val minPriceDiff = price - minPrice
            if (minPriceDiff > maxProfit) maxProfit = minPriceDiff
        }

        return maxProfit
    }

    /**
     * This is not going to work for the problem
     */
    fun maxProfitTwoPointer(prices: IntArray): Int {
        var maxValue = prices.last()
        var maxIndex = prices.lastIndex
        var minValue = prices.first()
        var minIndex = 0

        var profit = 0
        var end = prices.lastIndex
        var beg = 0

        while (beg < prices.lastIndex && end > 0) {
            if (prices[beg] <= minValue) {
                minIndex = beg
                minValue = prices[beg]
            }
            if (prices[end] >= maxValue) {
                maxIndex = end
                maxValue = prices[end]
            }
            if (maxIndex > minIndex) profit = max(profit, maxValue - minValue)
            else break
            beg++
            end--
        }

        return profit
    }

    /**
     * This is still a O(n^2) solution:
     * Not optimal.
     */
    fun maxProfit1(prices: IntArray): Int {
        // starting from prices index, find the 1st minimum
        return maxProfit(prices, 0, prices.lastIndex, 0)
    }

    private fun maxProfit(prices: IntArray, beg: Int, end: Int, result: Int): Int {
        if (end <= beg) return result

        val begElement = prices[beg]
        val maxElement = prices[end]
        val diff = maxElement - begElement
        val updatedResult = maxOf(result, diff)
        return if (diff > result) return updatedResult
        else maxOf(
            maxProfit(prices, beg + 1, end, updatedResult),
            maxProfit(prices, beg, end - 1, updatedResult)
        )
    }

    fun maxProfit2(prices: IntArray): Int {
        val map = mutableMapOf<Int, Int>()
        prices.forEachIndexed { i, v ->
            map[v] = i
        }

        // Now sort the list and start checking max and min
        prices.sort()
        var p = 0
        var q = prices.size - 1
        while (p < q) {
            val minPrice = prices[p]
            val maxPrice = prices[q]

            if (map[minPrice]!! < map[maxPrice]!!) return maxPrice - minPrice
            else if (map[minPrice]!! > map[maxPrice]!!) q--
            else p++
        }

        // No profit achieved
        return 0
    }

    fun maxProfitBruteForce(prices: IntArray): Int {
        var maxProfit = 0
        for (i in prices.indices) {
            for (j in i until prices.size) {
                val currentProfit = prices[j] - prices[i]
                maxProfit = max(currentProfit, maxProfit)
            }
        }
        return maxProfit
    }
}

fun main() {
    val sol = Solution00121()
    println(sol.maxProfit(intArrayOf(7, 1, 5, 3, 6, 4)))
    println(sol.maxProfit0(intArrayOf(7, 1, 5, 3, 6, 4)))

    println(sol.maxProfit(intArrayOf(7, 6, 4, 3, 1)))
    println(sol.maxProfit0(intArrayOf(7, 6, 4, 3, 1)))

    println(sol.maxProfit(intArrayOf(2, 4, 1)))
    println(sol.maxProfit0(intArrayOf(2, 4, 1)))

    println(sol.maxProfit(intArrayOf(2, 4, 1, 7)))
    println(sol.maxProfit0(intArrayOf(2, 4, 1, 7)))

    println(sol.maxProfit(intArrayOf(7, 1, 5, 3, 6, 4)))
    println(sol.maxProfit0(intArrayOf(7, 1, 5, 3, 6, 4)))

    println(sol.maxProfit(intArrayOf(2, 1, 2, 1, 0, 0, 1)))
    println(sol.maxProfit0(intArrayOf(2, 1, 2, 1, 0, 0, 1)))
    println(sol.maxProfitTwoPointer(intArrayOf(2, 1, 2, 1, 0, 0, 1)))

    println(sol.maxProfit(intArrayOf(3, 3, 5, 0, 0, 3, 1, 4)))
    println(sol.maxProfit0(intArrayOf(3, 3, 5, 0, 0, 3, 1, 4)))
    println(sol.maxProfit(intArrayOf(5, 1, 5, 6, 3, 1, 8)))
    println(sol.maxProfit0(intArrayOf(5, 1, 5, 6, 3, 1, 8)))
    println(sol.maxProfit(intArrayOf(5, 10, 1, 9, 3)))
    println(sol.maxProfit0(intArrayOf(5, 10, 1, 9, 3)))
}