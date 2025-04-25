package main.java.amazon

import java.io.File
import kotlin.math.max

class Solution02781 {
    fun longestValidSubstring(word: String, forbidden: List<String>): Int {
        var result = 0

        var p = 0
        for (i in word.indices) {
            val subString = word.substring(p, i+1) // end index is exclusive
//            val contains = forbidden.any { it in subString }
            // Since the max length of any forbidden word is 10
            val newSubString = subString.takeLast(10)
            val contains = forbidden.any { it in newSubString }

            if (contains)
//                p++
                p = max(p + 1, i - 10)
            else
                result = max(subString.length, result)
        }

        return result
    }
}

fun main() {
    // This will print the number of sub-arrays where max is repeated at least k times
    val sol = Solution02781()
//    println(sol.longestValidSubstring("cbaaaabc", listOf("aaa", "cb")))
//    println(sol.longestValidSubstring("leetcode", listOf("de", "le", "e")))
//    println(sol.longestValidSubstring("bcac", listOf("bcac", "caca", "bcac", "bca")))
    println(
        sol.longestValidSubstring(
            "aaabccccacacacaabcbaaabacbbbcabcbcaacbabccbababcabaacaacbbcbaabc",
            listOf(
                "bbbacbcb",
                "bcbaaabacb",
                "abbbbcb",
                "bcbcbac",
                "cbaabbbbbb",
                "bbbbaabcb",
                "cccaaaacaa",
                "cbabaaca",
                "baaabacbb",
                "abcabaacaa"
            )
        )
    )
    // The one with very long string
    val word = File("././Solution02781.txt").readText()
    println(
        sol.longestValidSubstring(
            word,
            listOf("bc", "de", "b")
        )
    )
}