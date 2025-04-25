package main.java.kotlin_solutions

import kotlin.math.min

class Solution00322 {
    fun coinChange(coins: IntArray, amount: Int): Int {
        val mem = mutableMapOf(0 to 0)
        val result = dp(coins.sortedArray(), amount, mem)
        return if (result == Int.MAX_VALUE) -1 else result
    }

    fun dp(coins: IntArray, amount: Int, mem: MutableMap<Int, Int>): Int {
        if (mem.contains(amount)) return mem[amount]!!
        if (amount < 0) return Int.MAX_VALUE

        // Use binary search, if found use base case
        var foundIndex = coins.binarySearch(amount)
//        if (foundIndex >= 0) return 1
        if (foundIndex < 0) { // -foundIndex - 1 is where it needs to be inserted
//        else {
            foundIndex = min(-foundIndex - 2, coins.size - 1)
        }

        // if not found, find the next max coin value
        var minCoinCount = Int.MAX_VALUE
        for (i in foundIndex downTo 0) {
            if (coins[i] <= amount) {
                val result = dp(coins, amount - coins[i], mem)
                if (result != Int.MAX_VALUE)
                    minCoinCount = min(minCoinCount, 1 + result)
            }
        }

        return minCoinCount
    }

    fun coinChange2(coins: IntArray, amount: Int): Int {
        if (amount == 0) return 0
        val dp = IntArray(amount + 1) { Int.MAX_VALUE }
        // Base case: 0 coins are needed to make the amount 0
        dp[0] = 0

        // Ensure coins are sorted for efficient access
        coins.sort()

        for (amt in 1..amount) {
            for (coin in coins) {
                // No need to check larger coins
                if (coin > amt) break
                if (dp[amt - coin] != Int.MAX_VALUE) {
                    dp[amt] = minOf(dp[amt], dp[amt - coin] + 1)
                }
            }
        }

        return if (dp[amount] != Int.MAX_VALUE) dp[amount] else -1
    }

    fun coinChangeRecursive(coins: IntArray, amount: Int, memo: IntArray): Int {
        // Base case: If the amount is 0, no coins are needed
        if (amount == 0) return 0
        // If the amount is negative, return "unreachable"
        if (amount < 0) return Int.MAX_VALUE
        // If we have already computed the result for this amount, return it
        if (memo[amount] != -2) return memo[amount]

        // Initialize the minimum number of coins needed to "unreachable"
        var minCoins = Int.MAX_VALUE
        // Try every coin denomination and choose the one that gives the minimum number of coins
        for (coin in coins) {
            val result = coinChangeRecursive(coins, amount - coin, memo)
            // If the result is not "unreachable", update minCoins
            if (result != Int.MAX_VALUE) {
                minCoins = minOf(minCoins, result + 1)
            }
        }

        // Store the computed result in the memoization array
        memo[amount] = minCoins
        // Return the minimum number of coins needed
        return minCoins
    }
}

fun main() {
    val s = Solution00322()
    println(s.coinChange(intArrayOf(186, 419, 83, 408), 6249))
    println(s.coinChange(intArrayOf(1), 0))
    println(s.coinChange(intArrayOf(2), 3))
    println(s.coinChange(intArrayOf(1, 2, 5), 11))
    println(s.coinChange(intArrayOf(2, 5, 10, 1), 27))
}