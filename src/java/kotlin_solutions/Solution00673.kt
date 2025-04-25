package java.kotlin_solutions

import kotlin.math.max

class Solution00673 {
    fun findNumberOfLIS(nums: IntArray): Int {
        // If the array has only one element, the length of the LIS is 1
        when (nums.size) {
            1 -> return 1
        }
        var maxLength = 0
        var start = 0
        var end = 1
        while (end < nums.size) {
            // If the current number is not greater than the previous one,
            // calculate the length of the current increasing subsequence
            // and update the start position
            if (nums[end - 1] >= nums[end]) {
                maxLength = max(maxLength, end - start)
                start = end
            }
            end++
        }
        // One last check for the max value
        maxLength = max(maxLength, end - start)
        return maxLength
    }
}

fun main() {
    val sol = Solution00673()
    println(sol.findNumberOfLIS(intArrayOf(1, 3, 5, 4, 7)))
    println(sol.findNumberOfLIS(intArrayOf(2, 2, 2, 2, 2)))
}
