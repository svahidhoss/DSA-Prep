package com.vahoss.kotlin_solutions

fun containsDuplicate(nums: IntArray): Boolean {
    nums.sort()
    for (i in 1 until nums.size) {
        if (nums[i] == nums[i - 1]) return true
    }
    return false
}

fun containsDuplicate2(nums: IntArray): Boolean {
    val set = mutableSetOf<Int>()
    nums.forEach {
        if (set.contains(it)) return true
        set.add(it)
    }

    return false
}

fun main() {
    val testCases = listOf(
        intArrayOf(1, 2, 3, 1) to true,
        intArrayOf(1, 2, 3, 4) to false,
        intArrayOf(1, 1, 1, 3, 3, 4, 3, 2, 4, 2) to true
    )

    testCases.forEachIndexed { index, (input, expected) ->
        val result1 = containsDuplicate(input)
        val result2 = containsDuplicate2(input)
        println("Test Case $index:")
        println("Input: ${input.joinToString(", ")}")
        println("Expected: $expected")
        println("containsDuplicate Output: $result1")
        println("containsDuplicate2 Output: $result2")
        println("containsDuplicate ${if (result1 == expected) "PASSED" else "FAILED"}")
        println("containsDuplicate2 ${if (result2 == expected) "PASSED" else "FAILED"}")
        println()
    }
}
