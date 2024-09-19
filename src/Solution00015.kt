class Solution00015 {

    /**
     * Time complexity: O(n^2)
     * Space complexity: O(n)
     */
    fun threeSum2(nums: IntArray): List<List<Int>> {
        val result = mutableSetOf<List<Int>>()

        // Add all the values to the map
        val map = mutableMapOf<Int, Int>()
        nums.forEachIndexed { i, num ->
            map[num] = i
        }

        // Outer loop to iterate over the first element
        for (i in nums.indices) {
            // Inner loop to iterate over the second element
            for (j in i + 1..nums.lastIndex) {
                // since the sum of all these values must be 0
                // The target third value we're looking for
                val target = -nums[i] - nums[j]
                map[target]?.let {
                    if (i != it && j != it) result.add(listOf(target, nums[i], nums[j]).sorted())
                }
            }
        }

        return result.toList()
    }

    /**
     * Time complexity: O(n^2)
     * Space complexity: O(1)
     */
    fun threeSum(nums: IntArray): List<List<Int>> {
        val result = mutableSetOf<List<Int>>()
        nums.sort()

        nums.forEachIndexed { i, num ->
            // call two pointer based 2sum to find -num as target
            result.addAll(twoSumTwoPointer(nums, -num, i))
        }

        return result.toList()
    }

    private fun twoSumTwoPointer(numbers: IntArray, target: Int, begIndex: Int): MutableList<List<Int>> {
        var p = begIndex + 1
        var q = numbers.lastIndex
        val result = mutableListOf<List<Int>>()

        while (p < q) {
            val sum = numbers[p] + numbers[q]
            when {
                sum == target -> {
                    result.add(listOf(numbers[begIndex], numbers[p], numbers[q]))
                    while (p < q && numbers[p] == numbers[p + 1]) p++ // Skip duplicates
                    while (p < q && numbers[q] == numbers[q - 1]) q-- // Skip duplicates
                    p++
                    q--
                }
                sum < target -> p++
                else -> q--
            }
        }

        return result
    }
}

fun main() {
    val sol = Solution00015()
    var nums = intArrayOf(-1, 0, 1, 2, -1, -4)
    println(sol.threeSum(nums))
    nums = intArrayOf(0, 1, 1)
    println(sol.threeSum(nums))
    nums = intArrayOf(0, 0, 0)
    println(sol.threeSum(nums))
    nums = intArrayOf(0, 0, 0, 0)
    println(sol.threeSum(nums))
    nums = intArrayOf(-2, 0, 1, 1, 2)
    println(sol.threeSum(nums))
}
