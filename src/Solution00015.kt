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
        val result = mutableListOf<MutableList<Int>>()
        nums.sort()
        for (i in nums.indices) {
            // break the loop and finish once you reach values greater than 0
            // as the sum would not add up to 0
            if (nums[i] > 0) return result
            // skip if value is equal to previous one
            if (i > 0 && nums[i] == nums[i-1]) continue

            twoSum(nums, i, result)
        }
        return result
    }

    private fun twoSum(nums: IntArray, i: Int, result: MutableList<MutableList<Int>>) {
        val seen = mutableSetOf<Int>()
        // start from index+1
        for (j in i + 1 until nums.size) {
            // The value we're looking for
            val target = -nums[i] - nums[j]
            if (seen.contains(target)) {
                result.add(mutableListOf(nums[i], target, nums[j]))
            }
            seen.add(nums[j])
        }
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
//    nums.sort()
//    println(Arrays.toString(nums))
//    println(Arrays.toString(nums.sliceArray(1 until nums.size)))
//    println(Arrays.toString(nums))
//    println(nums.slice(1 until nums.size))
//    println(Arrays.toString(nums))
    println(sol.threeSum(nums))
    nums = intArrayOf(0, 1, 1)
    println(sol.threeSum(nums))
    nums = intArrayOf(0, 0, 0)
    println(sol.threeSum(nums))
    nums = intArrayOf(0,0, 0, 0)
    println(sol.threeSum(nums))
}
