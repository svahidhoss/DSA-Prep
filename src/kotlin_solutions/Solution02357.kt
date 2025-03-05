package kotlin_solutions

import kotlin.collections.HashSet

class Solution02357 {
    fun minimumOperations(nums: IntArray): Int {
        val set = HashSet<Int>()
        for (n in nums) {
            if (n != 0) set.add(n)
        }
        return set.size
    }

    fun minimumOperations2(nums: IntArray): Int {
        val uniqueSet = nums.toHashSet()
        uniqueSet.remove(0)

        return uniqueSet.size
    }
}

fun main() {
    println(Solution02357().minimumOperations(intArrayOf(1, 5, 0, 3, 5)))
}