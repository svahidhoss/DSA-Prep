import kotlin.math.sqrt

class Solution00204 {
    /**
     * Times out.
     */
    fun countPrimes2(n: Int): Int {
        if (n < 2) return 0
        val set = mutableSetOf<Int>()

        val upperBound = sqrt(n.toDouble()).toInt()
        for (i in 2..upperBound) {
            var j = 2
            while (j * i < n) set.add(j++ * i)
        }

        return n - 1 - set.size
    }

    fun countPrimes(n: Int): Int {
        if (n < 2) return 0
        // strictly smaller than n, so we go based on each index
        // in the array to make sure the boolean array is working
        val isPrime = BooleanArray(n) { true }
        // represents 0 and 1
        isPrime[0] = false
        isPrime[1] = false

        val upperBound = sqrt(n.toDouble()).toInt()
        for (i in 2..upperBound) {
            var j = 2
            while (j * i < n) isPrime[j++ * i] = false
        }

        return isPrime.count { it }
    }
}

fun main() {
    val sol = Solution00204()

    println(sol.countPrimes(2))
    println(sol.countPrimes(10))
    println(sol.countPrimes(1))
    println(sol.countPrimes(0))
    println(sol.countPrimes(25))
    println(sol.countPrimes(100))
    println(sol.countPrimes(5000000))
}
