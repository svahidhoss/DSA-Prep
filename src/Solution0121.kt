import java.lang.IllegalStateException
import java.util.*
import kotlin.math.max

class Solution0121 {
    fun maxProfit(prices: IntArray): Int {
        // create a sorted version of the array
        val sorted = prices.sorted()
        // starting from prices index, find the 1st minimum
        prices.forEachIndexed { i, v ->

        }
        return 0
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
    val array = Array(5) { it * 2 }
    println(Arrays.toString(array))

    val sol = Solution0121()
    println(sol.maxProfit(intArrayOf(7, 1, 5, 3, 6, 4)))
    println(sol.maxProfit(intArrayOf(7, 6, 4, 3, 1)))
    println(sol.maxProfit(intArrayOf(2, 4, 1)))
}