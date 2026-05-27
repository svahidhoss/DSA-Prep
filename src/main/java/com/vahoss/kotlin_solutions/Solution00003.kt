package com.vahoss.kotlin_solutions

class Solution00003 {

    /**
     * Sliding window with a HashSet.
     * Shrinks from the left one char at a time until the old duplicate is evicted.
     * Time: O(n) — each char enters and leaves the set at most once.
     * Space: O(min(n, |charset|))
     */
    fun lengthOfLongestSubstring(s: String): Int {
        if (s.length <= 1) return s.length

        var beg = 0
        var end = 1
        val set = mutableSetOf<Char>().apply { add(s[beg]) }
        var result = 1

        while (end < s.length) {
            if (set.contains(s[end])) {
                // evict from the left until the old occurrence is removed
                while (s[beg] != s[end]) set.remove(s[beg++])
                set.remove(s[beg++])   // remove the old occurrence, advance past it
            }
            set.add(s[end])
            result = maxOf(result, end - beg + 1)
            end++
        }

        return result
    }

    /**
     * Sliding window with a HashMap (char → last seen index).
     * Jumps the left boundary directly past the previous occurrence — no inner loop.
     * Time: O(n) — single pass.
     * Space: O(min(n, |charset|))
     */
    fun lengthOfLongestSubstringHashMap(s: String): Int {
        if (s.length < 2) return s.length

        val charMap = mutableMapOf<Char, Int>()
        var answer = 0
        // left boundary of the current window
        var left = 0

        for (i in s.indices) {
            if (charMap.contains(s[i])) {
                // jump left past the previous occurrence of s[i];
                // maxOf keeps left from moving backwards if the duplicate is behind the window
                left = maxOf(charMap[s[i]]!! + 1, left)
            }
            // Record/update the index of this char
            charMap[s[i]] = i
            answer = maxOf(answer, i - left + 1)
        }

        return answer
    }
}

fun main() {
    val sol = Solution00003()

    data class Case(val s: String, val expected: Int)

    val cases = listOf(
        Case("a123hdfl", 8),
        Case("bbbbb", 1),
        Case("pwwkew", 3),
        Case("abcabcbb", 3),
        Case(" ", 1),
        Case("", 0),
        Case("bbabc", 3),
        Case("abba", 2),
    )
    for ((s, expected) in cases) {
        val r1 = sol.lengthOfLongestSubstring(s)
        val r2 = sol.lengthOfLongestSubstringHashMap(s)
        val status1 = if (r1 == expected) "PASS" else "FAIL (got $r1)"
        val status2 = if (r2 == expected) "PASS" else "FAIL (got $r2)"
        println("\"$s\" → set:$status1  map:$status2")
    }
}
