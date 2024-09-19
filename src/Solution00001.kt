import java.lang.IllegalArgumentException
import java.lang.IllegalStateException
import java.util.*

class Solution00001 {

    fun twoSum(nums: IntArray, target: Int): IntArray {
        val map = mutableMapOf<Int, Int>()
        nums.forEachIndexed { i, num ->
            map[target - num]?.let {
                return intArrayOf(i, it)
            }
            map[num] = i
        }

        throw IllegalStateException("No valid answer was found!")
    }

    fun twoSum3(nums: IntArray, target: Int): IntArray {
        val map = mutableMapOf<Int, MutableList<Int>>()

        nums.forEachIndexed { i, v ->
            map[v]?.add(i) ?: apply {
                map[target - v]?.let {
                    return intArrayOf(it.first(), i)
                }
                map[v] = mutableListOf(i)
            }
        }

        throw IllegalStateException("ONo valid answer was found..")
    }

    fun twoSum2(nums: IntArray, target: Int): IntArray {
        var index1: Int? = null
        var index2: Int? = null

        val map = mutableMapOf<Int, MutableList<Int>>()
        for (i in nums.indices) {
            map[nums[i]]?.add(i) ?: apply {
                map[nums[i]] = mutableListOf(i)
            }
        }

        for (i in nums.indices) {
            val potIndex = map[target - nums[i]]
            if (potIndex != null) {
                index1 = i
                if (potIndex.size > 1) {
                    for (i2 in potIndex)
                        if (i2 != index1) index2 = i2
                } else {
                    if (potIndex[0] != index1) index2 = potIndex[0]
                }

                return intArrayOf(index1, index2!!)
            }
        }

        throw IllegalArgumentException("No solution found. Code has problem?")
    }

}

fun main() {
    val sol = Solution00001()
    var nums = intArrayOf(2, 7, 11, 15)
    println(Arrays.toString(sol.twoSum3(nums, 9)))
    nums = intArrayOf(2, 3, 4)
    println(Arrays.toString(sol.twoSum3(nums, 6)))
    nums = intArrayOf(3, 3)
    println(Arrays.toString(sol.twoSum(nums, 6)))
    nums = intArrayOf(3, 2, 4)
    println(Arrays.toString(sol.twoSum3(nums, 6)))
    nums = intArrayOf(-1, 0)
    println(Arrays.toString(sol.twoSum(nums, -1)))
    nums = intArrayOf(5, 25, 75)
    println(sol.twoSum(nums, 100).contentToString())
}
