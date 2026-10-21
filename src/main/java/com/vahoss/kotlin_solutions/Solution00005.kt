package com.vahoss.kotlin_solutions

/**
 * Expand around center: for every index, grow a palindrome outward while the two ends
 * match, once for odd lengths (center is a character) and once for even lengths
 * (center is the gap between two characters). Only the start and end indexes of the
 * best palindrome are tracked; the substring is built once at the end.
 *
 * Time Complexity: O(n^2), up to 2n - 1 centers, each expanding up to O(n).
 * Space Complexity: O(1) extra, apart from the returned substring.
 */
private fun longestPalindrome(s: String): String {
    if (s.length <= 1) return s
    // start and LAST INCLUDED index of the best so far
    var result = Range(0, 0)
    for (i in s.indices) {
        // odd length, like aba
        result = expand(s, i - 1, i + 1, result)
        // even length, like abba
        result = expand(s, i - 1, i, result)
    }
    return s.substring(result.start, result.end + 1)
}

private data class Range(val start: Int, val end: Int) {
    val length get() = end - start + 1
}

private fun expand(s: String, l: Int, r: Int, best: Range): Range {
    var result = best
    var left = l
    var right = r
    while (left >= 0 && right <= s.lastIndex && s[left] == s[right]) {
        if (right - left + 1 > result.length) {
            result = Range(left, right)          // end is the last included index
        }
        left--
        right++
    }
    return result
}

fun main() {
    // expected is a set because some inputs have several equally long palindromes.
    data class TestCase(val s: String, val expected: Set<String>)

    val testCases = listOf(
        TestCase("babad", setOf("bab", "aba")),                 // two valid answers
        TestCase("cbbd", setOf("bb")),                          // even length
        TestCase("abc", setOf("a", "b", "c")),                  // no repeats: any single character
        TestCase("a", setOf("a")),                              // one character
        TestCase("aaaa", setOf("aaaa")),                        // all the same
        TestCase("abba", setOf("abba")),                        // whole string, even length
        TestCase("racecar", setOf("racecar")),                  // whole string, odd length
        TestCase("forgeeksskeegfor", setOf("geeksskeeg")),      // palindrome in the middle
    )

    for ((s, expected) in testCases) {
        val result = longestPalindrome(s)
        println(
            "s=\"$s\" Result=\"$result\" Expected=$expected ${if (result in expected) "OK" else "MISMATCH"}"
        )
    }
}
