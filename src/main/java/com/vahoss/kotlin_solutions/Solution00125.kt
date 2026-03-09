package com.vahoss.kotlin_solutions

class Solution00125 {

    /**
     * O(n) time complexity using two pointer solution.
     * it's O(n) extra space!
     */
    fun isPalindrome(s: String): Boolean {
        val newS = s.lowercase().filter { it.isLetterOrDigit() }
        var beg = 0
        var end = newS.lastIndex

        while (end > beg) {
            val begCh = newS[beg]
            val endCh = newS[end]
            if (begCh != endCh) return false
            beg++
            end--
        }

        return true
    }

    fun isPalindromeOptimized(s: String): Boolean {
        var start = 0
        var end = s.lastIndex

        while (end > start) {
            while (!s[start].isLetterOrDigit() && end > start) start++
            val startCh = s[start].lowercaseChar()

            while (!s[end].isLetterOrDigit() && end > start) end--
            val endCh = s[end].lowercaseChar()

            if (startCh != endCh) return false
            start++
            end--
        }

        return true
    }
}

fun main() {
    val sol = Solution00125()
    val testCases = mapOf(
        "A man, a plan, a canal: Panama" to true,
        "race a car" to false,
        " " to true
    )

    testCases.forEach {
        val result = sol.isPalindrome(it.key)
        println("result is $result, expected is ${it.value}")
    }
}