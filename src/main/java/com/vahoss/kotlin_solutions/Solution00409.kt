package com.vahoss.kotlin_solutions

class Solution00409 {
    fun longestPalindrome(s: String): Int {
        val map = mutableMapOf<Char, Int>()

        for (c in s) {
            map[c] = map.getOrDefault(c, 0) + 1
        }

        var result = 0
        var oddExists = false
        map.forEach { (c, count) ->
            if (count % 2 == 0) result += count
            else {
                result += count - 1
                oddExists = true
            }
        }

        if (oddExists) result++
        return result
    }
}
