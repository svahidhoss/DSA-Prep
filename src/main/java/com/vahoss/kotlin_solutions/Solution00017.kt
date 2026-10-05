package com.vahoss.kotlin_solutions

private val map = mapOf(
    '2' to "abc",
    '3' to "def",
    '4' to "ghi",
    '5' to "jkl",
    '6' to "mno",
    '7' to "pqrs",
    '8' to "tuv",
    '9' to "wxyz"
)

fun letterCombinations(digits: String): List<String> {
    val result = mutableListOf<String>()
    if (digits.isEmpty()) return result

    for (digit in digits) {
        val chars = map.getOrDefault(digit, "")
        if (result.isEmpty()) result.addAll(chars.map { it.toString() })
        else {
            val newList = mutableListOf<String>()
            for (prefix in result) {
                for (ch in chars) newList.add(prefix + ch)
            }
            result.clear()
            result.addAll(newList)
        }
    }

    return result
}

/**
 * Same layer-by-layer idea as [letterCombinations], shortened:
 * - Starts from listOf("") (one empty prefix) so the first digit needs no special case.
 * - For each digit, every existing prefix is extended by every letter of that digit.
 *
 * What flatMap does: map { } turns each element into ONE value, so mapping each
 * prefix to a list of its extensions would give a list of lists:
 *     ["a", "b"].map { p -> "de".map { p + it } }  ->  [["ad", "ae"], ["bd", "be"]]
 * flatMap { } turns each element into a LIST and then joins those lists into one
 * flat list, which is exactly the next layer of prefixes:
 *     ["a", "b"].flatMap { p -> "de".map { p + it } }  ->  ["ad", "ae", "bd", "be"]
 *
 * Time: O(4^n * n), up to 4^n combinations, each n chars long.
 * Space: O(4^n * n) for the output; the per-digit temporary list is no larger than it.
 */
fun letterCombinationsFlatMap(digits: String): List<String> {
    if (digits.isEmpty()) return emptyList()

    var result = listOf("")
    for (digit in digits) {
        // getValue throws on a digit outside 2-9 instead of silently dropping it.
        val letters = map.getValue(digit)
        result = result.flatMap { prefix -> letters.map { prefix + it } }
    }
    return result
}

fun main() {
    data class TestCase(val digits: String, val expected: List<String>)

    val testCases = listOf(
        TestCase("", emptyList()),
        TestCase("2", listOf("a", "b", "c")),
        TestCase("23", listOf("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf")),
        TestCase("7", listOf("p", "q", "r", "s")),
        TestCase("9", listOf("w", "x", "y", "z")),
        TestCase("22", listOf("aa", "ab", "ac", "ba", "bb", "bc", "ca", "cb", "cc")),
        TestCase("79", List(16) { i -> "pqrs"[i / 4].toString() + "wxyz"[i % 4] }),
        TestCase("234", List(27) { i -> "abc"[i / 9].toString() + "def"[(i / 3) % 3] + "ghi"[i % 3] }),
    )

    for (tc in testCases) {
        val r1 = letterCombinations(tc.digits)
        val r2 = letterCombinationsFlatMap(tc.digits)
        println(
            "digits=\"${tc.digits}\" letterCombinations=${if (r1 == tc.expected) "OK" else "MISMATCH $r1"}  " +
                "letterCombinationsFlatMap=${if (r2 == tc.expected) "OK" else "MISMATCH $r2"}  Expected=${tc.expected}"
        )
    }
}
