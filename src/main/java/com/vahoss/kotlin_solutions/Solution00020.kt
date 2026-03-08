package com.vahoss.kotlin_solutions

import java.util.*


class Solution00020 {

    fun isValid(s: String): Boolean {
        val stack = ArrayDeque<Char>()

        s.forEach {
            try {
                if (it == ')' && stack.last() == '(')
                    stack.removeLast()
                else if (it == ']' && stack.last() == '[')
                    stack.removeLast()
                else if (it == '}' && stack.last() == '{')
                    stack.removeLast()
                else
                    stack.addLast(it)

            } catch (e: NoSuchElementException) {
                return false
            }
        }

        return stack.isEmpty()
    }

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
    val map = mapOf(
        "()" to true,
        "()[]{}" to true,
        "(]" to false,
        "([])" to true,
        "([)]" to false,
        "]" to false
    )

    map.forEach {
        println(sol.isValid(it.key))
        println(it.value)
    }
}
