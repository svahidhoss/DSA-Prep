/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution00015 {
    fun threeSum(nums: IntArray): List<List<Int>> {
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

    fun twoSumOrdered(numbers: IntArray, target: Int): IntArray {
        var p = 0
        var q = numbers.size - 1

        while (p < q) {
            val sum = numbers[p] + numbers[q]
            when {
                sum == target -> return intArrayOf(p + 1, q + 1)
                sum < target -> p++
                else -> q--
            }
        }

        throw IllegalStateException("Valid answer was not found")
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
}
