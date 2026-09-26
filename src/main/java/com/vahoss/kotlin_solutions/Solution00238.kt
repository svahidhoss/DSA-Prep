package com.vahoss.kotlin_solutions

class Solution0238 {
    /**
     * O(1) extra space (excluding the output array). The forward pass writes prefix
     * products directly into the output array instead of a separate left array. The
     * backward pass folds the suffix product into a single running variable instead
     * of a right array, multiplying it into output[i] *before* updating it to include
     * nums[i] - otherwise output[i] would end up including itself.
     * Time: O(n). Space: O(1) extra.
     */
    fun productExceptSelfOptimized(nums: IntArray): IntArray {
        val n = nums.size
        val output = IntArray(n) { 1 }

        for (i in 1 until n) {
            output[i] = nums[i - 1] * output[i - 1]
        }

        var rightProduct = 1
        for (i in n - 1 downTo 0) {
            output[i] *= rightProduct
            rightProduct *= nums[i]
        }

        return output
    }

    /**
     * Prefix/suffix product arrays: left[i] = product of everything before i,
     * right[i] = product of everything after i, answer[i] = left[i] * right[i].
     * Time: O(n). Space: O(n) extra (left + right arrays, plus the zip result).
     */
    fun productExceptSelf(nums: IntArray): IntArray {
        val n = nums.size
        val left = IntArray(n) { 1 }
        val right = IntArray(n) { 1 }

        for (i in 1 until n)
            left[i] = nums[i - 1] * left[i - 1]

        for (i in n - 2 downTo 0)
            right[i] = nums[i + 1] * right[i + 1]

        return left.zip(right) { a, b -> a * b }.toIntArray()
    }
}

fun main() {
    val sol = Solution0238()

    data class TestCase(val nums: IntArray, val expected: IntArray)

    val testCases = listOf(
        TestCase(intArrayOf(1, 2, 3, 4), intArrayOf(24, 12, 8, 6)),
        TestCase(intArrayOf(-1, 1, 0, -3, 3), intArrayOf(0, 0, 9, 0, 0)),
        TestCase(intArrayOf(0, 0), intArrayOf(0, 0)),
        TestCase(intArrayOf(1, 0), intArrayOf(0, 1)),
        TestCase(intArrayOf(0, 1, 2), intArrayOf(2, 0, 0)),
        TestCase(intArrayOf(1, 1), intArrayOf(1, 1)),
        TestCase(intArrayOf(-1, -1, -1), intArrayOf(1, 1, 1)),
        TestCase(intArrayOf(2, 3), intArrayOf(3, 2)),
        TestCase(intArrayOf(5), intArrayOf(1)),
    )

    for (tc in testCases) {
        val r1 = sol.productExceptSelf(tc.nums.copyOf())
        val r2 = sol.productExceptSelfOptimized(tc.nums.copyOf())
        println(
            "${tc.nums.joinToString(prefix = "[", postfix = "]")} expected=${tc.expected.joinToString()}  " +
                    "productExceptSelf=${r1.joinToString()} ${if (r1.contentEquals(tc.expected)) "OK" else "MISMATCH"}  " +
                    "productExceptSelfOptimized=${r2.joinToString()} ${if (r2.contentEquals(tc.expected)) "OK" else "MISMATCH"}"
        )
    }
}
