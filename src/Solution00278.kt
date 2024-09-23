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
    fun firstBadVersion(n: Int): Int {
        var left = 0
        var right = n
        while (left < right) {
            val mid = left + (right - left) / 2
            if (isBadVersion(mid)) right = mid
            else left = mid + 1
        }
        return left
    }

    fun firstBadVersionRec(n: Int): Int {
        return binSearch(1, n)
    }

    private fun binSearch(beg: Int, end: Int): Int {
        val mid = beg + (end - beg) / 2
        if (isBadVersion(mid)) {
            return if (!isBadVersion(mid - 1)) mid
            else binSearch(beg, mid - 1)
        }

        return binSearch(mid + 1, end)
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
        Pair(10, 4),
        Pair(20, 1),
        Pair(100, 100),
        Pair(1, 1),
        Pair(50, 25)
    )

    for ((n, badVersion) in testCases) {
        s.setBadVersion(badVersion)
        val result = s.firstBadVersionRec(n)
        println("n = $n, bad version = $badVersion, first bad version found: $result")
        assert(result == badVersion) { "Test case failed: n = $n, expected $badVersion, got $result" }
    }

    // Random test
    val randomN = Random.nextInt(1, 1000)
    val randomBadVersion = Random.nextInt(1, randomN + 1)
    s.setBadVersion(randomBadVersion)
    val randomResult = s.firstBadVersion(randomN)
    println("Random test: n = $randomN, bad version = $randomBadVersion, first bad version found: $randomResult")
    assert(randomResult == randomBadVersion) { "Random test failed: n = $randomN, expected $randomBadVersion, got $randomResult" }

    println("All tests passed!")
}
