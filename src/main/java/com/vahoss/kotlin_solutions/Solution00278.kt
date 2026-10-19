package com.vahoss.kotlin_solutions

import com.vahoss.assert
import kotlin.random.Random

/* The isBadVersion API is defined in the parent class VersionControl.
      fun isBadVersion(version: Int) : Boolean {} */

/**

[1, 2, ..., n]

bool isBadVersion(version)

bad = 4
1 2 3 4 5
b e

mid = beg + (end - beg) / 2 = 1+2 = 3


 */
class Solution00278 {
    /**
     * Binary search on [left, right]. If mid is bad, the first bad version is mid or
     * earlier, so keep mid (right = mid); otherwise it is after mid (left = mid + 1).
     * The range shrinks until left == right, which is the first bad version.
     *
     * Time: O(log n), the range halves each iteration (about log n isBadVersion calls).
     * Space: O(1), only left, right and mid are kept.
     */
    fun firstBadVersion(n: Int): Int {
        var left = 1
        var right = n
        while (left < right) {
            val mid = left + (right - left) / 2
            if (isBadVersion(mid)) right = mid
            else left = mid + 1
        }
        return left
    }

    /**
     * Same binary search as [firstBadVersion], written recursively: each call searches
     * one half of the range and the other half is never visited.
     *
     * Time: O(log n), O(1) work per call and about log n calls.
     * Space: O(log n) for the call stack, one frame per halving of the range.
     */
    fun firstBadVersionRec(n: Int): Int {
        return binSearch(1, n)
    }

    private fun binSearch(beg: Int, end: Int): Int {
        if (beg == end) return beg

        val mid = beg + (end - beg) / 2
        return if (isBadVersion(mid)) binSearch(beg, mid) else binSearch(mid + 1, end)
    }


    /**
     * Added for the purpose of testing.
     */
    private var badVersion = 0

    fun setBadVersion(version: Int) {
        badVersion = version
    }

    private fun isBadVersion(version: Int): Boolean {
        return version >= badVersion
    }
}

fun main() {
    val s = Solution00278()
    val testCases = listOf(
        Pair(5, 4),
        Pair(10, 4),
        Pair(20, 1),
        Pair(100, 100),
        Pair(1, 1),
        Pair(50, 25)
    )

    for ((n, badVersion) in testCases) {
        s.setBadVersion(badVersion)
        val result = s.firstBadVersion(n)
        val resultRec = s.firstBadVersionRec(n)
        println("n = $n, bad version = $badVersion, first bad version found: $result, recursive: $resultRec")
        assert(result == badVersion) { "Test case failed: n = $n, expected $badVersion, got $result" }
        assert(resultRec == badVersion) { "Recursive test case failed: n = $n, expected $badVersion, got $resultRec" }
    }

    // Random test
    val randomN = Random.nextInt(1, 1000)
    val randomBadVersion = Random.nextInt(1, randomN + 1)
    s.setBadVersion(randomBadVersion)

    val randomResult = s.firstBadVersion(randomN)
    val randomResultRec = s.firstBadVersionRec(randomN)
    println("Random test: n = $randomN, bad version = $randomBadVersion, first bad version found: $randomResult, recursive: $randomResultRec")
    assert(randomResult == randomBadVersion) { "Random test failed: n = $randomN, expected $randomBadVersion, got $randomResult" }
    assert(randomResultRec == randomBadVersion) { "Random recursive test failed: n = $randomN, expected $randomBadVersion, got $randomResultRec" }

    println("All tests passed!")
}
