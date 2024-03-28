import kotlin.math.max

class Solutions00198 {
    /**
     * Wrong!
     */
    fun rob2(nums: IntArray): Int {
        var sum1 = 0
        var sum2 = 0

        nums.forEachIndexed { i, v ->
            if (i % 2 == 1) sum1 += v
            else sum2 += v
        }

        return max(sum2, sum1)
    }

    var dps = intArrayOf()

    fun rob(nums: IntArray): Int {
        dps = IntArray(nums.size) { Int.MIN_VALUE }
        return dp(nums.size - 1, nums)
    }

    private fun dp(n: Int, nums: IntArray): Int {
        if (n == 0) dps[0] = nums[0]
        else if (n == 1) dps[1] = max(nums[1], nums[0])
        else if (dps[n] == Int.MIN_VALUE) {
            dps[n] = Math.max(dp(n - 2, nums) + nums[n], dp(n - 1, nums))
        }
        return dps[n]
    }
}

fun main() {
    val sol = Solutions00198()
    var nums = intArrayOf(1, 2, 3, 1)
    println(sol.rob(nums))
    nums = intArrayOf(2, 7, 9, 3, 1)
    println(sol.rob(nums))
    nums = intArrayOf(2, 1, 1, 4)
    println(sol.rob(nums))
}