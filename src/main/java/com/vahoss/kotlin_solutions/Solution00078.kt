package com.vahoss.kotlin_solutions

class Solution00078 {
    fun subsets(nums: IntArray): List<List<Int>> {
        val result = mutableListOf<MutableSet<Int>>().apply { add(mutableSetOf()) }

        nums.forEach { num ->
            val newSets = mutableListOf<MutableSet<Int>>()
            result.forEach {
                val newSet = HashSet(it).apply { add(num) }
                newSets.add(newSet)
            }
            result.addAll(newSets)
        }

        return result.map { it.toList() }
    }

    /**
     * Same approach as [subsets], cleaned up: lists instead of sets (the numbers are
     * distinct, and lists keep the order stable), and `subset + num` returns a new list
     * so there is no copy-then-mutate step.
     *
     * Time: O(2^n * n), 2^n subsets, each up to n long.
     * Space: O(2^n * n) for the output; the temporary withNum list is at most half of it.
     */
    fun subsetsClean(nums: IntArray): List<List<Int>> {
        // Start with one empty subset (not an empty list), so the first number has something to extend.
        val result = mutableListOf<List<Int>>(emptyList())

        for (num in nums) {
            // Build the new subsets first; adding to `result` while looping over it would break the loop.
            val withNum = mutableListOf<List<Int>>()
            for (subset in result) {
                withNum.add(subset + num)   // `+` returns a NEW list, so `subset` is untouched
            }
            result.addAll(withNum)
        }

        return result
    }
}

fun main() {
    val s = Solution00078()
    println(s.subsets(intArrayOf()))
    println(s.subsets(intArrayOf(1)))
    println(s.subsets(intArrayOf(1, 2)))
    println(s.subsets(intArrayOf(1, 2, 3)))
}
