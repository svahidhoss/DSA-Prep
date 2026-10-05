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

fun main() {
    println(letterCombinations("23"))
    println(letterCombinations("2"))
    println(letterCombinations(""))
}
