package com.vahoss.kotlin_solutions

class Solution00150a {

    fun evalRPN(tokens: Array<String>): Int {
        val stack = ArrayDeque<Int>()

        for (token in tokens) {
            val number = token.toIntOrNull()

            // if not a number, must be an operator
            if (number == null) {
                val b = stack.removeLast()
                val a = stack.removeLast()
                val result = when (token) {
                    "+" -> a + b
                    "-" -> a - b
                    "/" -> a / b
                    "*" -> a * b
                    else -> throw Exception("Unsupported operator: $token")
                }
                stack.addLast(result)
            } else {
                stack.addLast(number)
            }
        }

        return stack.removeLast()
    }

    fun evalRPN2(tokens: Array<String>): Int {
        val stack = ArrayDeque<Int>()
        var i = 0
        stack.addLast(tokens[i++].toInt())
        while (i < tokens.size) {
            // check the latest value for being a number
            val nextToken = tokens[i].toIntOrNull()

            // if not a number, must be an operator
            if (nextToken == null) {
                val b = stack.removeLast()
                val a = stack.removeLast()
                val result = when (tokens[i]) {
                    "+" -> a + b
                    "-" -> a - b
                    "/" -> a / b
                    "*" -> a * b
                    else -> throw Exception("That's not a supported operator")
                }
                // Add result of the operation back to stack
                stack.addLast(result)
            } else {
                // if just a number add it
                stack.addLast(nextToken)
            }
            i++
        }
        return stack.removeLast()
    }
}

fun main() {
    val sol = Solution00150a()
    println(sol.evalRPN(arrayOf("2", "1", "+", "3", "*")))
    println(sol.evalRPN(arrayOf("4", "13", "5", "/", "+")))
    println(sol.evalRPN(arrayOf("10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+")))
}