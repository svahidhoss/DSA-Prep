package kotlin_solutions

class Solution00096 {
    private val results = IntArray(20) { 1 }

    /**
     * Recursive approach
     */
    fun numTrees(n: Int): Int {
        // n == 0 (is added for simplifying the calculations)
        // n == 1 result will be 1, or it means we have already found the result
        if (n in 0..1 || results[n] > 1) return results[n]

        results[n] = 0
        // If we fix the node with val i then we divide the problem in half
        for (i in 1..n) {
            results[n] += numTrees(i - 1) * numTrees(n - i)
        }

        return results[n]
    }

    /**
     * Iterative approach:
     */
    fun numTrees2(n: Int): Int {
        val uBSTs = IntArray(n + 1)
        uBSTs[0] = 1
        uBSTs[1] = 1

        // Start calculating the uBSTs from 2 all the way to n
        for (i in 2..n) {
            println("\ni: $i")
            for (j in 1..i) {
                println("j: $j")
                println("uBSTs[$i] += uBSTs[${j - 1}] * uBSTs[${i - j}]")
                // If we fix the node with val i then we divide the problem in half
                uBSTs[i] += uBSTs[j - 1] * uBSTs[i - j]
            }
            println("-> uBSTs[$i] = ${uBSTs[i]}")
        }

        return uBSTs[n]
    }
}

fun main() {
    val sol = Solution00096()
    println("${sol.numTrees(4)} \n")
    println(sol.numTrees2(4))
}
