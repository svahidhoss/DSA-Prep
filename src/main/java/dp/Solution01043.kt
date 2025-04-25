package main.java.dp

class Solution1043 {
    fun maxSumAfterPartitioning(arr: IntArray, k: Int): Int {
        val maxSum = IntArray(arr.size)
        maxSum[0] = arr[0]
        for (i in 1 until maxSum.size) {
            var partitionCurrentMax = 0
            var partitionMaxSum = 0
            for (j in 1..k) {
                val preIndex = i - j
                if (preIndex < 0) break

                partitionCurrentMax = maxOf(arr[preIndex], partitionCurrentMax)
                partitionMaxSum = maxOf(partitionMaxSum, maxSum[k] + partitionCurrentMax * j)
            }
            maxSum[i] = partitionMaxSum
        }
        return maxSum.last()
    }

    fun maxSumAfterPartitioning2(arr: IntArray, k: Int): Int {
        val memo = mutableMapOf<Int, Int>()
        // base case
        memo[0] = 0
        val dp = dp(arr, k, arr.size, memo)
        return dp
    }


    private fun dp(arr: IntArray, k: Int, i: Int, memo: MutableMap<Int, Int>): Int {
        if (memo.contains(i)) return memo[i]!!

        var maxSum = 0
        var currentKMax = 0
        for (j in 1..k) {
            val preIndex = i - j
            if (preIndex < 0) break

            currentKMax = maxOf(arr[preIndex], currentKMax)
            val currentSum = dp(arr, k, preIndex, memo) + currentKMax * j
            maxSum = maxOf(maxSum, currentSum)

            memo[i] = maxSum
        }
        return maxSum
    }
}

fun main() {
    val s = Solution1043()

    println(s.maxSumAfterPartitioning(intArrayOf(1, 15, 7, 9, 2, 5, 10), 3))
    println(s.maxSumAfterPartitioning(intArrayOf(1, 4, 1, 5, 7, 3, 6, 1, 9, 9, 3), 4))
    println(s.maxSumAfterPartitioning(intArrayOf(1), 1))
}