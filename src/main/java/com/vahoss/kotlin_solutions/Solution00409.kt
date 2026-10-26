package com.vahoss.kotlin_solutions

/**
 * Longest Palindrome.
 *
 * Given a string s which consists of lowercase or uppercase letters, return the
 * length of the longest palindrome that can be built with those letters.
 *
 * Letters are case-sensitive, for example, "Aa" is not considered a palindrome.
 *
 *              "abccccdd"
 *               0 1 2 3 4 5 6
 *              "a b c c c d d"
 *                   l   r
 */
class Solution00409 {

    /**
     * Count every letter, then use all of each letter's pairs: a palindrome is pairs placed
     * symmetrically on both sides, plus at most one single letter in the middle. A letter
     * with an odd count contributes count - 1, and one leftover letter can go in the middle.
     *
     * Time Complexity: O(n), one pass to count and one pass over the fixed-size array.
     * Space Complexity: O(1), the array always has 58 slots regardless of the input size.
     */
    fun longestPalindrome(s: String): Int {
        if (s.length <= 1) return s.length
        var result = 0
        // 58 slots; 6 are never used: the characters between 'Z' (90) and 'a' (97) in the
        // ASCII table are [ \ ] ^ _ and the backtick, which can't appear in the input.
        val countArray = IntArray('z' - 'A' + 1)
        for (c in s) {
            countArray[c - 'A']++
        }

        for (count in countArray) {
            result += if (count % 2 == 0) count else count - 1
        }

        // Each odd count dropped one letter, so result < s.length means at least one letter
        // is left over, and one of them can go in the middle. If every count was even,
        // result already equals s.length.
        return if (result < s.length) result + 1 else result
    }

    fun longestPalindrome0(s: String): Int {
        val map = mutableMapOf<Char, Int>()

        for (c in s) {
            map[c] = map.getOrDefault(c, 0) + 1
        }

        var result = 0
        var oddExists = false
        map.forEach { (_, count) ->
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

fun main() {
    data class TestCase(val s: String, val expected: Int)

    val sol = Solution00409()
    val testCases = listOf(
        TestCase("abcccdd", 5),
        TestCase("abccccdd", 7),
        TestCase("abbbccccdd", 9),
        TestCase("abbbcccccdd", 9),
        TestCase("a", 1),
        TestCase("Aa", 1),      // case-sensitive: A and a are different letters, only one can be used
        TestCase("aaaa", 4),    // all the same, every count is even
        TestCase("abc", 1),     // no pairs at all, just one letter in the middle
        TestCase("AAbb", 4),    // two uppercase and two lowercase pairs
        // the whole string is already a palindrome, so every letter is used
        TestCase("zeusnilemacaronimaisanitratetartinasiaminoracamelinsuez", 55),
    )

    for ((s, expected) in testCases) {
        val result = sol.longestPalindrome(s)
        println("s=\"$s\" Result=$result Expected=$expected ${if (result == expected) "OK" else "MISMATCH"}")
    }
}
