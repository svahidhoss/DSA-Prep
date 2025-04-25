package com.vahoss.kotlin_solutions

class Solution00560 {
    fun subarraySum(nums: IntArray, k: Int): Int {
        if (nums.size == 1 && nums[0] == k) return 1
        val map = mutableMapOf(nums[0] to 0)
        var count = 0
        // Calculate the prefix sum value of the nums array
        for (i in 1 until nums.size) {
            nums[i] += nums[i - 1]
            if (nums[i] == k) count++
            map[nums[i]] = i
            val valToLookFor = nums[i] - k
            if (map.containsKey(valToLookFor)) count++
            println("Looking for $k - ${nums[i]} = $valToLookFor in the map, count = $count")
        }
        return count
    }

    fun subarraySum2(nums: IntArray, k: Int): Int {
        var count = 0
        var sum = 0
        val map = HashMap<Int, Int>()
        map[0] = 1
        for (i in nums.indices) {
            sum += nums[i]
            if (map.containsKey(sum - k)) count += map[sum - k]!!
            map[sum] = map.getOrDefault(sum, 0) + 1
        }
        return count
    }
}

fun main() {
    val sol = Solution00560()

    // Output: 1
    println(sol.subarraySum(intArrayOf(1), 1))
    // Output: 2
    println(sol.subarraySum(intArrayOf(1, 1, 1), 2))
    // Output: 2
    println(sol.subarraySum(intArrayOf(1, 2, 3), 3))
    // Output: 3
    println(sol.subarraySum(intArrayOf(-1, -2, -3, 0, 1, 2, 3, 4, 5), 3))
}