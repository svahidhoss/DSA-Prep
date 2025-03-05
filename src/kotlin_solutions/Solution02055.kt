package kotlin_solutions

import java.util.*

class Solution02055 {
    fun platesBetweenCandles(s: String, queries: Array<IntArray>): IntArray {
        val prefixSum = IntArray(s.length + 1)

        // Preprocess the string to calculate prefix sum of plates
        for (i in s.indices) {
            prefixSum[i + 1] = prefixSum[i] + if (s[i] == '*') 1 else 0
        }

        // Process queries using the prefix sum
        return queries.map { query ->
            val leftCandle = query[0]
            val rightCandle = query[1]
            prefixSum[rightCandle] - prefixSum[leftCandle]
        }.toIntArray()
    }

    fun platesBetweenCandlesTreeMap(s: String, queries: Array<IntArray>): IntArray {
        val candleIndicesMap = TreeMap<Int, Int>()
        var platesCount = 0

        // Preprocess the string to store candle indices and plate counts
        for (i in s.indices) {
            if (s[i] == '|') candleIndicesMap[i] = platesCount
            else platesCount++
        }

        // Process queries using the TreeMap
        return queries.map { query ->
            val leftCandle = candleIndicesMap.ceilingEntry(query[0])?.key
            val rightCandle = candleIndicesMap.floorEntry(query[1])?.key
            if (leftCandle != null && rightCandle != null && leftCandle <= rightCandle) {
                candleIndicesMap[rightCandle]!! - candleIndicesMap[leftCandle]!!
            } else 0
        }.toIntArray()
    }


    fun platesBetweenCandlesBinarySearch(s: String, queries: Array<IntArray>): IntArray {
        val result = IntArray(queries.size)
        val candlesIndexes = mutableListOf<Int>()
        val platesBetweenCandles = mutableListOf<Int>()

        var plateCount = 0
        s.forEachIndexed { i, c ->
            if (c == candle) {
                platesBetweenCandles.add(plateCount)
                plateCount = 0
                candlesIndexes.add(i)
            } else plateCount++
        }

        queries.forEachIndexed { index, query ->
            // Find the next location where a candle occurs in the beginning
            val beg = candlesIndexes.binarySearch(query[0]).let {
                if (it < 0) it.inv() else it
            }
            // Find the previous location of a candle in the end
            val end = candlesIndexes.binarySearch(query[1]).let {
                if (it < 0) it.inv() - 1 else it
            }
            // if the end candle is ahead of the beg candle
            result[index] = if (end - beg > 0) platesBetweenCandles.subList(beg + 1, end + 1).sum() else 0
        }

        return result
    }

    private val candle = '|'
    private val plate = '*'
    fun platesBetweenCandles2(s: String, queries: Array<IntArray>): IntArray {
        val result = IntArray(queries.size)

        queries.forEachIndexed { index, query ->
            var count = 0
            val a = query[0]
            val b = query[1]

            var foundA = false
            var actualB = -1
            println(s.substring(a, b + 1))
            for (i in a..b) {
                when {
                    !foundA && s[i] == candle -> foundA = true
                    foundA && s[i] == plate -> count++
                    foundA && s[i] == candle -> actualB = i
                }
            }
            if (actualB == -1) result[index] = 0
            else result[index] = count - (b - actualB)
        }

        return result
    }
}

fun main() {
    val sol = Solution02055()
//    println(
//        Arrays.toString(
//            sol.platesBetweenCandles
//                ("**|**|***|", arrayOf(intArrayOf(2, 5), intArrayOf(5, 9)))
//        )
//    )
    println(
        Arrays.toString(
            sol.platesBetweenCandles(
                "***|**|*****|**||**|*",
                arrayOf(
                    intArrayOf(1, 17),
                    intArrayOf(4, 5),
                    intArrayOf(14, 17),
                    intArrayOf(5, 11),
                    intArrayOf(15, 16)
                )
            )
        )
    )
}