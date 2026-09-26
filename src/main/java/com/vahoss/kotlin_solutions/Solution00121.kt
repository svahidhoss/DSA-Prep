package com.vahoss.kotlin_solutions

import kotlin.math.max
import kotlin.math.min

class Solution00121 {

    /**
     * Single pass solution: the most optimized.
     *
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
     *
     * Time: O(n) - single pass. Space: O(1).
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
     * Attempted approach: two pointers converging from both ends, tracking a running min
     * from the left frontier and a running max from the right frontier, crediting profit
     * when maxIndex > minIndex.
     *
     * BROKEN. Confirmed failing, e.g. [5, 20, 1, 10] -> returns 5, correct answer is 15.
     * Root cause: the `else break` aborts the whole scan the instant one snapshot's
     * maxIndex/minIndex ordering fails, discarding pairs it hasn't reached yet. More
     * fundamentally, pairing "min of the left frontier" with "max of the right frontier"
     * isn't a valid search over all buy/sell index pairs - same class of bug as the
     * sorted two-pointer approach that failed on problem 416.
     *
     * Intended: Time O(n) - single pass. Space O(1). Moot, since it's wrong.
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
     * Attempted approach: recursively shrink from both ends (beg+1 or end-1), branching
     * both ways and taking the max, with an early return when the current pair already
     * beats the incoming best.
     *
     * BROKEN, and the complexity claim above was also wrong. This isn't O(n^2) - with no
     * memoization it's exponential (the same (beg,end) state gets recomputed via many
     * different call paths). It's also incorrect: e.g. [2, 4, 1, 7] -> returns 5, correct
     * answer is 6. Root cause: `if (diff > result) return updatedResult` stops recursing
     * down that branch as soon as it finds ANY improvement, assuming nothing better exists
     * further down - that assumption is false, and cuts off the search too early.
     *
     * Time: O(2^n) worst case - exponential, no memoization. Space: O(n) recursion depth.
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

    /**
     * Brute force: every (i, j) pair with i < j, no pruning.
     * Correct baseline. O(n^2) time, O(1) space.
     */
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

    data class TestCase(val prices: IntArray, val expected: Int)

    val testCases = listOf(
        TestCase(intArrayOf(7, 1, 5, 3, 6, 4), 5),
        TestCase(intArrayOf(7, 6, 4, 3, 1), 0),
        TestCase(intArrayOf(2, 4, 1), 2),
        TestCase(intArrayOf(2, 4, 1, 7), 6),
        TestCase(intArrayOf(2, 1, 2, 1, 0, 0, 1), 1),
        TestCase(intArrayOf(3, 3, 5, 0, 0, 3, 1, 4), 4),
        TestCase(intArrayOf(5, 1, 5, 6, 3, 1, 8), 7),
        TestCase(intArrayOf(5, 10, 1, 9, 3), 8),
    )

    for (tc in testCases) {
        println(
            "maxProfit: ${sol.maxProfit(tc.prices)}, maxProfit0: ${sol.maxProfit0(tc.prices)}" +
                    ", Expected: ${tc.expected}"
        )
    }
}
