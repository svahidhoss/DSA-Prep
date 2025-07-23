package com.vahoss.kotlin_solutions

class Solution00242 {
    fun isAnagram2(s: String, t: String): Boolean {
        val charCountArray = IntArray(26)
        s.forEach {
            charCountArray[it - 'a']++
        }
        t.forEach {
            charCountArray[it - 'a']--
        }
        return charCountArray.all { it == 0 }
    }

    fun isAnagramOptimized(s: String, t: String): Boolean {
        if (s.length != t.length) return false
        val charCount = IntArray(26)
        for (i in s.indices) {
            charCount[s[i] - 'a']++
            charCount[t[i] - 'a']--
        }
        return charCount.all { it == 0 }
    }

    /**
     * Universal solution that works for all characters.
     */
    fun isAnagram(s: String, t: String): Boolean {
        if (s.length != t.length) return false
        val map = mutableMapOf<Char, Int>()

        for (i in s.indices) {
            map[s[i]] = map.getOrDefault(s[i], 0) + 1
            map[t[i]] = map.getOrDefault(t[i], 0) - 1
        }
        return map.values.all { it == 0 }
    }
}
