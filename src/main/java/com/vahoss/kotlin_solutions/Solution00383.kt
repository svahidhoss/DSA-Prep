package com.vahoss.kotlin_solutions

class Solution00383 {
    fun canConstructOptimized(ransomNote: String, magazine: String): Boolean {
        val magCount = magazine.groupingBy { it }.eachCount().toMutableMap()
        return ransomNote.all { char ->
            magCount[char]?.let { count ->
                if (count > 0) {
                    magCount[char] = count - 1
                    true
                } else false
            } ?: false
        }
    }

    fun canConstruct(ransomNote: String, magazine: String): Boolean {
        // 26 is enough for this question (lower case letters)
        val ransom = IntArray(26)
        magazine.forEach {
            ransom[it - 'a']++
        }
        ransomNote.forEach {
            if (--ransom[it - 'a'] < 0) return false
        }
        return true
    }

    /**
     * Same counting idea as [canConstruct], but indexed by the character's ASCII code
     * (it.code) in an IntArray(128), so there is no offset arithmetic and it works for any
     * ASCII character (digits, spaces, punctuation, upper and lower case), not just 'a'-'z'.
     * Count every letter of the magazine up, then subtract for each letter of the ransom
     * note; if any count goes below 0, the magazine doesn't have enough of that letter.
     * (This question only needs 26 slots, since the input is lowercase letters only.)
     *
     * Time: O(m + n), m = magazine length, n = ransomNote length.
     * Space: O(1), a fixed 128-slot array regardless of input size.
     * A character with code >= 128 (e.g. an accented letter or emoji) would throw.
     */
    fun canConstructAscii(ransomNote: String, magazine: String): Boolean {
        val counts = IntArray(128)
        magazine.forEach { counts[it.code]++ }
        for (c in ransomNote) {
            if (--counts[c.code] < 0) return false
        }
        return true
    }

    fun canConstruct0(ransomNote: String, magazine: String): Boolean {
        // Handle edge cases explicitly
        if (ransomNote.isEmpty()) return true
        if (magazine.isEmpty()) return false

        val magazineMap = mutableMapOf<Char, Int>()
        magazine.forEach {
            magazineMap[it] = magazineMap.getOrDefault(it, 0) + 1
        }

        ransomNote.forEach { char ->
            magazineMap[char]?.let {
                if (it <= 0) return false
                magazineMap[char] = it - 1
            } ?: return false
        }

        return true
    }

    fun canConstruct2(ransomNote: String, magazine: String): Boolean {
        val ransomMap = mutableMapOf<Char, Int>()
        ransomNote.forEach {
            ransomMap[it] = ransomMap.getOrDefault(it, 0) + 1
        }

        val magazineMap = mutableMapOf<Char, Int>()
        magazine.forEach {
            magazineMap[it] = magazineMap.getOrDefault(it, 0) + 1
        }

        ransomMap.forEach { (key, value) ->
            if (magazineMap.getOrDefault(key, 0) < value) return false
        }

        return true
    }
}

fun main(args: Array<String>) {
    data class TestCase(val ransomNote: String, val magazine: String, val expected: Boolean)

    val sol = Solution00383()
    val testCases = listOf(
        TestCase("a", "b", false),
        TestCase("aa", "ab", false),
        TestCase("aa", "aab", true),
        // Some additional test cases
        TestCase("", "abc", true),                          // empty ransom note
        TestCase("abc", "", false),                         // empty magazine
        TestCase("aaabbc", "abcabcabc", true),              // repeated chars
    )

    for ((ransomNote, magazine, expected) in testCases) {
        print("ransomNote=\"$ransomNote\" magazine=\"$magazine\" ")
        val result = sol.canConstruct(ransomNote, magazine)
        println(
            "Result=$result Expected=$expected ${if (result == expected) "OK" else "MISMATCH"}"
        )
    }
}