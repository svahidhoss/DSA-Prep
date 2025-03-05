package kotlin_solutions

import kotlin.math.max

class Solution00674 {
    fun findLengthOfLCISNew(nums: IntArray): Int {
        // If the array has only one element, the length of the LCIS is 1
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

    fun findLengthOfLCIS(nums: IntArray): Int {
        if (nums.size == 1) return 1

        var result = 0
        var p = 0
        var q = 1
        while (q < nums.size) {
            if (nums[q - 1] >= nums[q]) {
                result = max(result, q - p)
                p = q
            }
            q++
        }
        // One last check for the max value
        result = max(result, q - p)
        return result
    }
}

fun main() {
    val sol = Solution00674()
    println(sol.findLengthOfLCIS(intArrayOf(1, 3, 5, 4, 7)))
    println(sol.findLengthOfLCIS(intArrayOf(2, 2, 2, 2, 2)))
}