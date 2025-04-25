package main.java.kotlin_solutions

class Solution01464 {
    fun maxProduct(nums: IntArray): Int {
        for (i in 0 until nums.size - 1) {
            if (nums[i] > nums[i + 1]) swap(nums, i, i + 1)
        }
        for (i in 0 until nums.size - 2) {
            if (nums[i] > nums[i + 1]) swap(nums, i, i + 1)
        }

        return (nums[nums.size - 1] - 1) * (nums[nums.size - 2] - 1)
    }

    private fun swap(nums: IntArray, i: Int, j: Int) {
        val temp = nums[i]
        nums[i] = nums[j]
        nums[j] = temp
    }
}

fun main() {
    val sol = Solution01877()
    var nums = intArrayOf(3, 5, 2, 3)
    println(sol.minPairSum(nums))
    nums = intArrayOf(3, 5, 4, 2, 4, 6)
    println(sol.minPairSum(nums))
}
