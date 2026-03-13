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

    /**
     * if lowercase English letter can be assumed.
     *                      HashMap                 IntArray(26)
     * Time                 O(n)                    O(n)
     * Space                O(k) unique chars       O(1) fixed 26
     * Constant factor      Higher(boxing, hashing) Lower(direct index)
     *
     * The IntArray(26) trick is O(1) space because the array
     * size never grows with input — it's always exactly 26
     * integers regardless of string length.
     */
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
     *
     * // Prefer this pattern:
     * collection.all { condition }       // "every element satisfies X"
     * collection.none { condition }      // "no element satisfies X"
     * collection.any { condition }       // "at least one satisfies X"
     *
     * // Avoid:
     * !collection.any { !condition }     // harder to read, same as all {}
     * !collection.all { condition }      // harder to read, same as none {}
     */
    fun isAnagram(s: String, t: String): Boolean {
        if (s.length != t.length) return false
        val map = mutableMapOf<Char, Int>()

        for (i in s.indices) {
            map[s[i]] = map.getOrDefault(s[i], 0) + 1
            map[t[i]] = map.getOrDefault(t[i], 0) - 1
        }

        // Always prefer positive assertion over negated negatives
        return map.values.all { it == 0 }
        // return !map.values.any { it != 0 }
    }
}

fun main() {
    val solution = Solution00242()
    println("${solution.isAnagram("anagram", "nagaram")} should be true")
    println("${solution.isAnagramOptimized("anagram", "nagaram")} should be true")
    println("${solution.isAnagram2("anagram", "nagaram")} should be true")

    println("${solution.isAnagram("rat", "car")} should be false")
    println("${solution.isAnagramOptimized("rat", "car")} should be false")
    println("${solution.isAnagram2("rat", "car")} should be false")
}
