package com.vahoss.kotlin_solutions

/**
 * LeetCode 70: Climbing Stairs
 */
class Solution00070 {

    /**
     * Brute-force recursive solution.
     *
     * Time Complexity: O(2^n), because most subproblems are recomputed many times.
     * Space Complexity: O(n), because the deepest recursive call stack can go
     * from n down to 1.
     */
    fun climbStairsNonOptimized(n: Int): Int {
        if (n <= 0) return 0
        if (n == 1) return 1
        if (n == 2) return 2

        // Try taking one step first, plus try taking two steps first.
        // The overlap between these branches is what makes this version slow.
        return climbStairsNonOptimized(n - 1) + climbStairsNonOptimized(n - 2)
    }

    /**
     * Top-down dynamic programming solution using memoization.
     *
     * Time Complexity: O(n), because each step value from 1 through n is solved
     * once.
     * Space Complexity: O(n), because the memo map stores O(n) answers and
     * recursion can go O(n) deep.
     */
    fun climbStairs(n: Int): Int {
        // Seed the base cases so dp can build larger answers from already-known values.
        val map = mutableMapOf(1 to 1, 2 to 2)
        return dp(n, map)
    }

    /**
     * Memoized helper for climbStairs.
     *
     * Time Complexity: O(n) across one climbStairs call, because cached values
     * are reused.
     * Space Complexity: O(n), from the memo map and recursive stack.
     */
    fun dp(n: Int, map: MutableMap<Int, Int>): Int {
        // If this subproblem was already solved, return it instead of branching again.
        if (map.contains(n)) return map[n]!!
        map[n] = dp(n - 1, map) + dp(n - 2, map)
        return map[n]!!
    }

    private fun naiveWithCount(n: Int, counter: IntArray): Int {
        // Mirrors climbStairsNonOptimized, but counts calls for comparison output.
        counter[0]++
        if (n <= 0) return 0
        if (n == 1) return 1
        if (n == 2) return 2
        return naiveWithCount(n - 1, counter) + naiveWithCount(n - 2, counter)
    }

    private fun memoWithCount(n: Int, counter: IntArray): Int {
        // Uses a fresh cache per measured run so the call count is fair for each n.
        val cache = mutableMapOf(1 to 1, 2 to 2)
        fun dp(k: Int): Int {
            counter[0]++
            return cache.getOrPut(k) { dp(k - 1) + dp(k - 2) }
        }
        return dp(n)
    }

    /**
     * Prints runtime and call-count data for the brute-force and memoized versions.
     *
     * This is not a formal benchmark; it is a simple visual comparison that shows why caching
     * changes the solution from exponential work to linear work.
     */
    fun compareApproaches(nValues: List<Int>) {
        val header = "%-4s  %-10s  %-15s  %-12s  %-13s  %-10s  %s"
        val row = "%-4d  %-10d  %-15d  %-12d  %-13d  %-10d  %.1fx"
        println(header.format("n", "answer", "naive calls", "naive µs", "memo calls", "memo µs", "call speedup"))
        println("-".repeat(88))

        for (n in nValues) {
            val naiveCounter = IntArray(1)
            val t0 = System.nanoTime()
            val answer = naiveWithCount(n, naiveCounter)
            val naiveUs = (System.nanoTime() - t0) / 1_000

            val memoCounter = IntArray(1)
            val t1 = System.nanoTime()
            memoWithCount(n, memoCounter)
            val memoUs = (System.nanoTime() - t1) / 1_000

            println(
                row.format(
                    n, answer, naiveCounter[0], naiveUs,
                    memoCounter[0], memoUs, naiveCounter[0].toDouble() / memoCounter[0]
                )
            )
        }
    }
}

fun main() {
    val s = Solution00070()

    // Covers small inputs, a medium input, and n = 45, which is the largest LeetCode constraint.
    val testCases = mapOf(1 to 1, 2 to 2, 3 to 3, 5 to 8, 10 to 89, 45 to 1134903170)

    println("n\texpected\tnaive\t\tmemo\t\tpass?")
    for ((n, expected) in testCases) {
        // Skip the brute-force version for large n because it is intentionally exponential.
        val naive = if (n <= 40) s.climbStairsNonOptimized(n) else -1
        val memo = s.climbStairs(n)
        val naiveMatches = naive == expected || naive == -1
        println("$n\t$expected\t\t$naive\t\t$memo\t\t${naiveMatches && memo == expected}")
    }

    println("\n=== Call count comparison ===")
    s.compareApproaches(listOf(5, 10, 20, 30, 40))
}
