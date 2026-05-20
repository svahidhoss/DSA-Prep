package com.vahoss.amazon

import java.lang.IllegalStateException

class Solution00169 {
    
    /**
     * HashMap frequency count — tracks occurrences of each element.
     * Time: O(n) | Space: O(n)
     */
    fun majorityElementMap(nums: IntArray): Int {
        val map = mutableMapOf<Int, Int>()
        var result = nums.first()
        for (num in nums) {
            map[num] = map.getOrDefault(num, 0) + 1
            result = maxOf(result, map[num]!!)
        }
        return result
    }

    /**
     * Sort — majority element always occupies the middle index after sorting.
     * Time: O(n log n) | Space: O(1)
     */
    fun majorityElementSort(nums: IntArray): Int {
        nums.sort()
        return nums[nums.size / 2]
    }

    /**
     * Boyer-Moore voting — candidate cancels out with non-matching elements;
     * survivor is the majority.
     * Time: O(n) | Space: O(1)
     */
    fun majorityElement(nums: IntArray): Int {
        var count = 0
        var candidate = nums[0]
        nums.forEach { n ->
            if (count == 0) candidate = n
            if (n == candidate) count++ else count--
        }
        return candidate
    }

    /**
     * Brute force — for each element, counts occurrences until majority
     * threshold is reached.
     * Time: O(n²) | Space: O(1)
     */
    fun majorityElementBruteForce(nums: IntArray): Int {
        nums.forEachIndexed { i, n ->
            var count = 0
            for (j in i until nums.size) {
                if (n == nums[j]) count++
                if (count > nums.size / 2) return n
            }
        }
        throw IllegalStateException("No results were found.")
    }
}

fun main() {
    val sol = Solution00169()

    val cases = listOf(
        intArrayOf(3, 2, 3) to 3,
        intArrayOf(2, 2, 1, 1, 1, 2, 2) to 2,
        intArrayOf(1) to 1,
        intArrayOf(1, 1) to 1,
        intArrayOf(6, 5, 5) to 5,
    )

    for ((input, expected) in cases) {
        val r1 = sol.majorityElementSort(input.copyOf())
        val r2 = sol.majorityElement(input)
        val r3 = sol.majorityElementBruteForce(input)
        val r4 = sol.majorityElementMap(input)
        val tag = if (r1 == expected && r2 == expected && r3 == expected && r4 == expected) "PASS" else "FAIL"
        println("[$tag] input=${input.toList()} expected=$expected sort=$r1 boyer-moore=$r2 brute=$r3 map=$r3")
    }
}