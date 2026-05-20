package com.vahoss.kotlin_solutions

/**
 * Time: O(n log n) — sort dominates | Space: O(1) — in-place sort (O(log n) call stack)
 */
fun containsDuplicateSort(nums: IntArray): Boolean {
    nums.sort()
    for (i in 1 until nums.size) {
        if (nums[i] == nums[i - 1]) return true
    }
    return false
}

/**
 * Time: O(n) — single pass | Space: O(n) — hash set
 */
fun containsDuplicate(nums: IntArray): Boolean {
    val set = mutableSetOf<Int>()
    nums.forEach {
        if (set.contains(it)) return true
        set.add(it)
    }

    return false
}

/**
 * Time: O(n) — single pass via add() short-circuit | Space: O(n) — hash set
 */
fun containsDuplicateIdiomatic(nums: IntArray): Boolean {
    val seen = mutableSetOf<Int>()
    return nums.any { !seen.add(it) }
}

fun main() {
    val testCases = listOf(
        intArrayOf(1, 2, 3, 1) to true,
        intArrayOf(1, 2, 3, 4) to false,
        intArrayOf(1, 1, 1, 3, 3, 4, 3, 2, 4, 2) to true
    )

    testCases.forEachIndexed { index, (input, expected) ->
        val result1 = containsDuplicateSort(input.copyOf())
        val result2 = containsDuplicate(input)
        val result3 = containsDuplicateIdiomatic(input)
        println("Test Case $index:")
        println("Input: ${input.joinToString(", ")}")
        println("Expected: $expected")
        println("containsDuplicateSort      ${if (result1 == expected) "PASSED" else "FAILED"}")
        println("containsDuplicate          ${if (result2 == expected) "PASSED" else "FAILED"}")
        println("containsDuplicateIdiomatic ${if (result3 == expected) "PASSED" else "FAILED"}")
        println()
    }
}
