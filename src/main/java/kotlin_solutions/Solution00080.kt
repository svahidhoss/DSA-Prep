package main.java.kotlin_solutions

import java.util.*

class Solution0080 {
    fun removeDuplicates(nums: IntArray): Int {
        val n = nums.toMutableList()
        n.removeAt(4)
        var p = nums.size - 1
        var q = p
        var r = p

        while (q >= 0) {
            while (q >= 0 && nums[q] == nums[p])
                q--

            var diff = p - q
            if (diff > 2) {
                diff -= 2
                // move the last elements dif times
                for (j in 0 until diff) {
                    for (i in p until r) {
                        swap(nums, i, i + 1)
                    }
                    p--
                }

                r -= diff
            }
            p = q
        }

        return r + 1
    }

    private fun swap(nums: IntArray, i: Int, j: Int) {
        val temp = nums[i]
        nums[i] = nums[j]
        nums[j] = temp
    }
}

fun main() {
    val sol = Solution0080()
    var nums = intArrayOf(1, 1, 1, 2, 2, 3)
    println(sol.removeDuplicates(nums))
    println(Arrays.toString(nums))
    nums = intArrayOf(0, 0, 1, 1, 1, 1, 2, 3, 3)
    println(sol.removeDuplicates(nums))
    println(nums.contentToString())

}