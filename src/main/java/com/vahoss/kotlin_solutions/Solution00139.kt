package com.vahoss.kotlin_solutions


class Solution00139 {
    private val memo = mutableMapOf<String, Boolean>()

    fun wordBreak(s: String, wordDict: List<String>): Boolean {
        if (memo.containsKey(s)) return memo[s]!!

        for (word in wordDict) {
            if (s.startsWith(word)) {
                val rest = s.slice(word.length until s.length)
                // empty rest means we have reached the end of the s successfully
                if (rest.isEmpty() || wordBreak(rest, wordDict)) {
                    memo[rest] = true
                    return true
                }
            }
        }
        memo[s] = false
        return false
    }
}

/**
 * My first attempt without memoization.
 */
fun wordBreak1(s: String, wordDict: List<String>): Boolean {
    if (s.isEmpty()) return false
    for (word in wordDict) {
        if (s.startsWith(word)) {
            val sliced = s.slice(word.length until s.length)
            // means we have reached the end of the s successfully
            if (sliced.isEmpty()) return true
            val wordBroken = wordBreak1(sliced, wordDict)
            if (wordBroken) return true
        }
    }
    return false
}

fun main() {
    val s = Solution00139()
    println(s.wordBreak("leetcode", wordDict = listOf("leet", "code")))
    println(s.wordBreak("applepenapple", wordDict = listOf("apple", "pen")))
    println(s.wordBreak("catsandog", listOf("cats", "dog", "sand", "and", "cat")))
}