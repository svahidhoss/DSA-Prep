package java.kotlin_solutions

class Solution0448 {
    fun findDisappearedNumbers(nums: IntArray): List<Int> {
        nums.forEachIndexed { index, _ ->
            val value = Math.abs(nums[index]) - 1
            nums[value] = -Math.abs(nums[value])
        }

        return nums.mapIndexed { index, value ->
            if (value > 0) index + 1 else null
        }.filterNotNull()
    }

    fun findDisappearedNumbers2(nums: IntArray): List<Int> {
        nums.forEach { v ->
            val index = Math.abs(v) - 1
            nums[index] = -Math.abs(nums[index])
        }

        val result = mutableListOf<Int>()
        nums.forEachIndexed { i, v ->
            if (v > 0) result.add(i + 1)
        }

        return result
    }
}

fun main(args: Array<String>) {
    val sol = Solution0448()
    println(sol.findDisappearedNumbers(intArrayOf(4, 3, 2, 7, 8, 2, 3, 1)))
    println(sol.findDisappearedNumbers(intArrayOf(1, 1)))
}