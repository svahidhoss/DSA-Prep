package com.vahoss.kotlin_solutions


class Solution00139Optimized {
    fun wordBreak(s: String, wordDict: List<String>): Boolean {
        val wordSet = wordDict.toHashSet()
        val memo = mutableMapOf<Int, Boolean>()
        return canBreakFrom(0, s, wordSet, memo)
    }

    private fun canBreakFrom(
        start: Int,
        s: String,
        wordSet: Set<String>,
        memo: MutableMap<Int, Boolean>
    ): Boolean {
        if (start == s.length) return true
        if (start in memo) return memo[start]!!

        for (end in start + 1..s.length) {
            if (s.substring(start, end) in wordSet) {
                if (canBreakFrom(end, s, wordSet, memo)) {
                    memo[start] = true
                    return true
                }
            }
        }

        memo[start] = false
        return false
    }
}
class Solution00139 {
    private val memo = mutableMapOf<String, Boolean>()

    /**
     * Each unique substring of s is evaluated at most once → O(n)
     *
     * For each substring, we try up to k words and startsWith(word) costs O(w) in the worst case
     *
     * So:
     * Time = O(n * k * w)
     *
     * n = length of string s
     * k = number of words in the dictionary
     * w = average length of words in wordDict
     *
     * In practice, it’s usually much faster, because:
     * The recursion ends early on invalid paths
     * Dictionary lookups can be optimized using a Set
     */
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