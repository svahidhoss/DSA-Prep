package com.vahoss.kotlin_solutions

/**

O(n)
O(log n) solution

find the mid index
compare its value with the value begore and after
before is bigger retrun val

 */
class Solution00153 {
    fun findMin(nums: IntArray): Int {
        if(nums.size <= 2) return nums.min()

        var beg = 0
        var end = nums.lastIndex

        while (beg < end) {
            val mid = beg + (end - beg) / 2
            if (nums[mid] > nums[mid + 1]) return nums[mid + 1]
            if (nums[mid] < nums[mid - 1]) return nums[mid]

            if (nums[beg] > nums[mid]) end = mid - 1 else beg = mid + 1
        }

        return nums.first()
    }

    fun findMinOptimized(nums: IntArray): Int {
        if (nums.size == 1) return nums[0]

        var left = 0
        var right = nums.lastIndex

        while (left < right) {
            val mid = left + (right - left) / 2

            // If mid is greater than the next element, next element is the minimum
            if (mid < nums.lastIndex && nums[mid] > nums[mid + 1]) return nums[mid + 1]

            // If mid is less than the previous element, mid is the minimum
            if (mid > 0 && nums[mid] < nums[mid - 1]) return nums[mid]

            // If the left half is sorted, minimum is in the right half
            if (nums[left] <= nums[mid]) left = mid + 1
            // Otherwise, minimum is in the left half
            else right = mid - 1
        }

        // If we've gotten here, the array was not rotated
        return nums[left]
    }
}

fun main() {
    val s = Solution00153()
    var array = intArrayOf(3, 4, 5, 1, 2)
    s.findMin(array).let(::println)

    array = intArrayOf(4, 5, 6, 7, 0, 1, 2)
    s.findMin(array).let(::println)

    array = intArrayOf(11, 13, 15, 17)
    s.findMin(array).let(::println)

    array = intArrayOf(1, 2)
    s.findMin(array).let(::println)
}
