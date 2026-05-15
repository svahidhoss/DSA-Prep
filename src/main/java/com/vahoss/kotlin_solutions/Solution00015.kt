package com.vahoss.kotlin_solutions

class Solution00015 {

    /**
     * Pros:
     * - Correctly handles duplicate values (e.g. [0,0,0]) by storing all indices per key
     * - Does not require sorting the input array
     *
     * Cons:
     * - Average-case O(n^2): when values are spread across many distinct keys, each targetList
     *   has O(1) entries on average
     * - Worst-case O(n^3): when many elements share the same value, targetList has O(n) entries,
     *   making the inner forEach an O(n) loop inside the O(n^2) outer loops
     * - O(n) extra space for the map (in addition to the result set)
     * - Sorting each triplet before insertion adds overhead per found triplet
     */
    fun threeSum1(nums: IntArray): List<List<Int>> {
        val result = mutableSetOf<List<Int>>()

        val map = mutableMapOf<Int, MutableList<Int>>()
        nums.forEachIndexed { i, num ->
            map.getOrPut(num) { mutableListOf() }.add(i)
        }

        for (i in nums.indices) {
            for (j in i + 1..nums.lastIndex) {
                if (i == j) continue
                val targetList = map[-(nums[i] + nums[j])]
                targetList?.forEach {
                    if (i != it && j != it) result.add(listOf(nums[i], nums[j], nums[it]).sorted())
                }
            }
        }

        return result.toList()
    }


    /**
     * Time complexity: O(n^2) — O(n log n) sort + O(n^2) two-pointer scan
     * Space complexity: O(1) auxiliary (excluding output)
     */
    fun threeSum(nums: IntArray): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        nums.sort()

        nums.forEachIndexed { i, num ->
            // skip duplicate values of the fixed element to avoid duplicate triplets in output
            if (i > 0 && nums[i] == nums[i - 1]) return@forEachIndexed
            // find all pairs in nums[i+1..n-1] that sum to -num
            result.addAll(twoSumTwoPointer(nums, -num, i))
        }

        return result
    }

    private fun twoSumTwoPointer(numbers: IntArray, target: Int, begIndex: Int): MutableList<List<Int>> {
        var p = begIndex + 1
        var q = numbers.lastIndex
        val result = mutableListOf<List<Int>>()

        while (p < q) {
            val sum = numbers[p] + numbers[q]
            when {
                sum == target -> {
                    result.add(listOf(numbers[begIndex], numbers[p], numbers[q]))
                    while (p < q && numbers[p] == numbers[p + 1]) p++ // Skip duplicates
                    while (p < q && numbers[q] == numbers[q - 1]) q-- // Skip duplicates
                    p++
                    q--
                }

                sum < target -> p++
                else -> q--
            }
        }

        return result
    }
}

fun main() {
    val sol = Solution00015()
    var nums = intArrayOf(-1, 0, 1, 2, -1, -4)
    println(sol.threeSum(nums))
    nums = intArrayOf(0, 1, 1)
    println(sol.threeSum(nums))
    nums = intArrayOf(0, 0, 0)
    println(sol.threeSum(nums))
    nums = intArrayOf(0, 0, 0, 0)
    println(sol.threeSum(nums))
    nums = intArrayOf(-2, 0, 1, 1, 2)
    println(sol.threeSum(nums))
}
