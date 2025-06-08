package com.vahoss.kotlin_solutions

class Solution00875 {

    /**
     * No Sorting; instead, uses piles.maxOrNull() which is O(n)
     *
     * Binary Search Loop:
     *
     * Same as above: O(n log m)
     *
     * Total time complexity:
     * O(n + n log m)
     * Or simply:
     * O(n log m)
     *
     * ==> Strictly better, especially for large n, because it eliminates the unnecessary sorting step:
     * Both are optimal in space, but the revised version is optimal in time.
     */
    fun minEatingSpeed(piles: IntArray, h: Int): Int {
        // find the smallest possible k
        // min is always 1 banana
        var left = 1
        // max or it is always 10^9
        var right = piles.max()
        while (left < right) {
            val mid = left + (right - left) / 2
            if (doesSpeedWork(piles, h, mid)) {
                // mid is a possible result
                // search for possible minimum value
                right = mid
            } else left = mid + 1
        }
        return left
    }

    /**
    3,6,7,11 --> sorted
    sum: 27
    8 hours
    4

    h-> 5
    sorted -> 4 11 20 23 30
    |
    sum: 88
    30
    mid: 4 + 26/2 = 18 -> 8 hours
    beg: 4  18 18 22
    end: 30 30 24 24
    mid: 18 + 3 = 21
    18 + 6 = 24 works

    h-> 6     0  1  2  3  4
    sorted -> 4 11 20 23 30
    |  +1 +1
    sum: 88
    23
     */

    private fun doesSpeedWork(piles: IntArray, h: Int, speed: Int): Boolean {
        var hours = 0

        for (pile in piles) {
            hours += Math.ceil(pile.toDouble() / speed).toInt()
        }

        return hours <= h
    }

    /**
     * Sorting: O(n log n)
     *
     * Binary Search Loop:
     * Each iteration:
     *
     * Calls doesSpeedWork, which is O(n) (since it scans all piles)
     *
     * Number of iterations:
     * O(log m) (since range is [1, m])
     * Total for binary search: O(n log m)
     *
     * Total time complexity:
     * O(n log n + n log m)
     */
    fun minEatingSpeed2(piles: IntArray, h: Int): Int {
        // impossible
        if (h < piles.size) throw IllegalArgumentException("H must be greater than or equal to ${piles.size}")
        piles.sort()
        if (h == piles.size && h >= piles.last()) return piles.last()

        // the smallest should always be 1 not piles.first()
        // piles = [5, 10, 3] - h = 100 won't work
        var beg = 1
        var end = piles.last()

        while (beg < end) {
            val mid = beg + (end - beg) / 2
            if (doesSpeedWork(piles, h, mid)) {
                end = mid
            } else {
                beg = mid + 1
            }
        }

        return beg
    }
}

fun main() {
    val solution = Solution00875()

    val testCases = listOf(
        Triple(intArrayOf(3, 6, 7, 11), 8, 4),
        Triple(intArrayOf(30, 11, 23, 4, 20), 5, 30),
        Triple(intArrayOf(30, 11, 23, 4, 20), 6, 23),
        Triple(intArrayOf(1, 1, 1, 1), 4, 1),
        Triple(intArrayOf(1000000000), 2, 500000000),
        Triple(intArrayOf(1000000000), 1, 1000000000),
        Triple(intArrayOf(312884470), 968709470, 1)
    )

    for ((i, testCase) in testCases.withIndex()) {
        val (piles, h, expected) = testCase
        val result = solution.minEatingSpeed(piles, h)
        println("Test Case ${i + 1}: Expected = $expected, Got = $result -> ${if (result == expected) "✅ PASS" else "❌ FAIL"}")
    }
}
