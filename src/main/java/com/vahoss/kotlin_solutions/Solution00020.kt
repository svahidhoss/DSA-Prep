package com.vahoss.kotlin_solutions

import java.util.*


class Solution00020 {

    /**
     * Stack-based bracket matching. Detects "nothing to pop" via a caught
     * NoSuchElementException from removeLast() instead of a null check, and
     * spells out the three closing brackets explicitly rather than asking
     * the pairs map. Correct, but [isValidImproved] does the same thing with
     * fewer moving parts.
     * Time: O(n). Space: O(n).
     */
    fun isValid(s: String): Boolean {
        val pairs = mapOf(')' to '(', ']' to '[', '}' to '{')
        val stack = ArrayDeque<Char>()
        s.forEach {
            if (it == ')' || it == '}' || it == ']') {
                try {
                    val ch = stack.removeLast()
                    if (pairs[it] != ch) return false
                } catch (e: NoSuchElementException) {
                    return false
                }
            } else {
                stack.add(it)
            }
        }
        return stack.isEmpty()
    }

    /**
     * Same stack-based approach as [isValid], cleaned up: `char in pairs` asks
     * the map directly instead of restating the closing brackets, and
     * `lastOrNull()` checks the top before popping instead of catching an
     * exception on empty.
     * Time: O(n). Space: O(n).
     */
    fun isValidImproved(s: String): Boolean {
        val stack = ArrayDeque<Char>()
        val pairs = mapOf(')' to '(', ']' to '[', '}' to '{')

        s.forEach { char ->
            if (char in pairs) {
                if (stack.lastOrNull() == pairs[char])
                    stack.removeLast()
                else
                    return false
            } else {
                stack.addLast(char)
            }
        }

        return stack.isEmpty()
    }
}

fun main() {
    val sol = Solution00020()

    data class TestCase(val s: String, val expected: Boolean)

    val testCases = listOf(
        TestCase("()", true),
        TestCase("()[]{}", true),
        TestCase("(]", false),
        TestCase("([])", true),
        TestCase("([)]", false),
        TestCase("]", false),
    )

    for (tc in testCases) {
        val r1 = sol.isValid(tc.s)
        val r2 = sol.isValidImproved(tc.s)
        println(
            "\"${tc.s}\" expected=${tc.expected}  " +
                "isValid=$r1 ${if (r1 == tc.expected) "OK" else "MISMATCH"}  " +
                "isValidImproved=$r2 ${if (r2 == tc.expected) "OK" else "MISMATCH"}"
        )
    }
}
