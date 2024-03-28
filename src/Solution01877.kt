
class Solution01877 {
    fun minPairSum(nums: IntArray): Int {
        val sortedNumsList = nums.sorted()
        val list1 = sortedNumsList.subList(0, nums.size / 2)
        val list2 = sortedNumsList.subList(nums.size / 2, nums.size).reversed()

        val sumList = list1.zip(list2) { a, b -> a + b }
        println(sumList)
        return sumList.max()
    }
}

class SolutionX {
    fun getGoodIndices(variables: Array<IntArray>, target: Int): List<Int> {
        val set = mutableSetOf<Int>()
        for((i, array) in variables.withIndex()) {
            val firstMod = power(array[0], array[1], 10)

            if (power(firstMod, array[2], array[3]) == target)
                set.add(i)
        }
        return set.toList()
    }

    private fun power(x: Int, y: Int, m: Int): Int {
        if (y == 0) return 1

        var p = power(x, y / 2, m) % m
        p = (p * p) % m

        return if (y % 2 == 0) p
        else (x * p) % m
    }
}

fun main() {
    val sol = Solution01877()
    var nums = intArrayOf(3, 5, 2, 3)
    println(sol.minPairSum(nums))
    nums = intArrayOf(3, 5, 4, 2, 4, 6)
    println(sol.minPairSum(nums))
}
