package main.java.kotlin_solutions

class Solution02272 {
    fun largestVariance(s: String): Int {
        val map = mutableMapOf<Char, Int>()
        s.forEach {
            // Increment the value for the key, or initialize it to 1 if it doesn't exist
            map[it] = map.getOrPut(it) { 0 } + 1
        }

        var len = s.length
        var largestVariance = 0
        while (len > 2) {
            val counts = map.values.filter { it <= len }
            if (counts.isNotEmpty()) {
                val minCount = counts.minOrNull()!!
                val maxCount = counts.maxOrNull()!!
                val variance = maxCount - minCount
                largestVariance = Math.max(largestVariance, variance)
            }
            len--
        }

        return largestVariance
    }
}

fun main() {
    val sol = Solution02272()
    println(sol.largestVariance("aababbb"))
    println(sol.largestVariance("abcde"))
}