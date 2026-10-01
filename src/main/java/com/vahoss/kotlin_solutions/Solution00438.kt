package com.vahoss.kotlin_solutions

import kotlin.collections.component1
import kotlin.collections.component2
import kotlin.collections.iterator

/**
 * Fixed-size sliding window over s, comparing a frequency map of the current
 * window against p's frequency map. When the character leaving the window
 * equals the character entering it, the window's multiset is unchanged, so
 * the previous validity (switch) is reused instead of recomputed.
 *
 * Time: O(n + m). checkWindow costs O(min(pMap.size, 26)) per call, and since
 * both strings are lowercase English letters that's O(26) = O(1), not O(m).
 * Space: O(n). pMap/sMap are each bounded by O(26) = O(1); result dominates.
 */
fun findAnagrams(s: String, p: String): List<Int> {
    val n = s.length
    val m = p.length
    // Guardrail
    if (m > n) return emptyList()

    val sMap = mutableMapOf<Char, Int>()
    val pMap = mutableMapOf<Char, Int>()

    val result = mutableListOf<Int>()

    p.forEach {
        pMap[it] = pMap.getOrPut(it) { 0 } + 1
    }

    for (i in 0 until m) {
        sMap[s[i]] = sMap.getOrPut(s[i]) { 0 } + 1
    }

    var switch = checkWindow(sMap, pMap)
    if (switch) result.add(0)

    for (i in 0 until n - m) {
        val leftChar = s[i]
        val rightChar = s[i + m]
        if (switch && leftChar == rightChar) result.add(i + 1)
        else {
            // check left element
            sMap[leftChar] = sMap[leftChar]!! - 1
            // check right element
            sMap[rightChar] = sMap.getOrPut(rightChar) { 0 } + 1
            // check tho whole string again

            switch = checkWindow(sMap, pMap)
            if (switch) result.add(i + 1)
        }

    }


    return result
}

/**
 * O(min(pMap.size, 26)) per call - bounded by the lowercase-English alphabet,
 * so effectively O(1) regardless of how large p is.
 */
private fun checkWindow(sMap: MutableMap<Char, Int>, pMap: MutableMap<Char, Int>): Boolean {
    var switch = true

    for ((k, v) in pMap) {
        if (sMap[k] == null || sMap[k] != v) {
            switch = false
            break
        }
    }

    return switch
}

fun main() {
    data class TestCase(val s: String, val p: String, val expected: List<Int>)

    val testCases = listOf(
        TestCase("cbaebabacd", "abc", listOf(0, 6)),
        TestCase("abab", "ab", listOf(0, 1, 2)),
        TestCase("a", "a", listOf(0)),
        TestCase("a", "ab", emptyList()),
        TestCase("z", "ba", emptyList()),
        TestCase("aaaaaaaaaa", "aaaaaaaaa", listOf(0, 1)),
        TestCase("abcdefg", "xyz", emptyList()),
        TestCase("baa", "aa", listOf(1)),
    )

    for (tc in testCases) {
        val result = findAnagrams(tc.s, tc.p)
        println("s=\"${tc.s}\" p=\"${tc.p}\" Result: $result, Expected: ${tc.expected}")
    }
}