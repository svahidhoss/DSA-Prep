package dp

class Solution00053 {
    fun maxSubArray(nums: IntArray): Int {
        val newNum = nums.mapIndexed { i, v -> if (i != 0) v + nums[i - 1] else v }

        return nums.max() - nums.min()
    }
}

fun main() {
    val sol = Solution00053()
    println(sol.maxSubArray(intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4)))
    println(sol.maxSubArray(intArrayOf(1)))
    println(sol.maxSubArray(intArrayOf(5, 4, -1, 7, 8)))
}
