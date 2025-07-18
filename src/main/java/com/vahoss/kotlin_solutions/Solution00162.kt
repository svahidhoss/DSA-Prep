package com.vahoss.kotlin_solutions

class Solutions00162 {
    /**
     * Linear time complexity solution.
     */
    fun findPeakElementLinear(nums: IntArray): Int {
        if (nums.size == 1) return 0

        // it's always true > - infinity
        var isAscending = true

        for (i in 0..<nums.lastIndex) {
            if (isAscending && nums[i] > nums[i + 1]) return i
            isAscending = nums[i + 1] > nums[i]
        }

        // if no peak is found in the loop, the last element must be a peak
        return nums.lastIndex
    }

    fun findPeakElement(nums: IntArray): Int {
        if (nums.size == 1) return 0
        return findPeakElement(nums, 0, nums.lastIndex)
    }

    /**
     * This solution is creative and demonstrates good understanding of divide-and-conquer!
     * However, there are some issues that make it less efficient
     * than the optimal binary search approach.
     *
     * What it does well:
     * Correctly handles boundary conditions with Int.MIN_VALUE
     * Uses proper mid-point calculation to avoid overflow
     * Recursive structure is clean and readable
     *
     * Issues:
     * Time complexity: This is actually O(n) in the worst case, not O(log n).
     * You're searching both left AND right sides when the middle isn't a peak,
     * which means you might visit every element.
     * Unnecessary complexity: The problem guarantees a peak exists, so you
     * don't need to return -1 or check both sides.
     * Stack overhead: Recursive calls add function call overhead compared
     * to iterative approach.
     *
     * The key insight you're missing:
     * In binary search for peak finding, you only need to search ONE side based on the slope.
     * If nums[mid] < nums[mid + 1], the peak must be on the right side (since we're going uphill).
     * Otherwise, it must be on the left side or at mid.
     */
    private fun findPeakElement2(nums: IntArray, beg: Int, end: Int): Int {
        if (beg <= end) {
            val mid = beg + (end - beg) / 2
            // check boundaries
            val preVal = if (mid > 0) nums[mid - 1] else Int.MIN_VALUE
            val nextVal = if (mid < nums.lastIndex) nums[mid + 1] else Int.MIN_VALUE
            if (nums[mid] > nextVal && nums[mid] > preVal) {
                return mid
            }

            val left = findPeakElement2(nums, beg, mid - 1)
            if (left != -1) return left
            val right = findPeakElement2(nums, mid + 1, end)
            if (right != -1) return right
        }

        // nothing was found
        return -1
    }

    private fun findPeakElement(nums: IntArray, beg: Int, end: Int): Int {
        if (beg == end) return beg

        val mid = beg + (end - beg) / 2

        if (nums[mid] < nums[mid + 1]) {
            // Peak is on the right side
            return findPeakElement(nums, mid + 1, end)
        } else {
            // Peak is on the left side or at mid
            return findPeakElement(nums, beg, mid)
        }
    }

    fun findPeakElementOptimized(nums: IntArray): Int {
        var left = 0
        var right = nums.lastIndex

        while (left < right) {
            val mid = left + (right - left) / 2
            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1
            } else {
                right = mid
            }
        }

        return left
    }
}

fun main() {
    val sol = Solutions00162()

    var nums = intArrayOf(1, 2, 3, 1)
    println(sol.findPeakElement(nums))

    nums = intArrayOf(1, 2, 1, 3, 5, 6, 4)
    println(sol.findPeakElement(nums))

    nums = intArrayOf(2)
    println(sol.findPeakElement(nums))

    nums = intArrayOf(2, 1, 3)
    println(sol.findPeakElement(nums))
}
