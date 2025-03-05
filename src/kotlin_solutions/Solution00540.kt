package kotlin_solutions

import java.lang.IllegalStateException

class Solution00540 {
    fun singleNonDuplicate(nums: IntArray): Int {
        return nonDuplicate(nums, 0, nums.size - 1)
    }

    fun singleNonDuplicate2(nums: IntArray): Int {
        var p = 0
        var q = nums.size - 1

        while (p <= q) {
//            if (p == q) return nums[p]
            val m = p + (q - p) / 2

            if (m % 2 == 0 && nums[m] == nums[m - 1]) {
                // first half
                q = m - 2
            } else if (m % 2 == 1 && nums[m] == nums[m + 1]) {
                // first half
                q = m - 1
            } else if (nums[m] == nums[m - 1]) {
                // second half
                p = m + 1
            } else if (nums[m] == nums[m + 1]) {
                // second half
                p = m + 2
            } else {
                return nums[m]
            }
        }

        throw IllegalStateException("No result were found")
    }

    fun nonDuplicate(nums: IntArray, s: Int, e: Int): Int {
        if (e < s || s > e) return 0
        if (s == e) return nums[s]
        val mid = s + (e - s) / 2
        if (nums[mid] != nums[mid + 1] && nums[mid] != nums[mid - 1])
            return nums[mid]

        if ((nums[mid] == nums[mid - 1] && s == e - 1) || (nums[mid] == nums[mid + 1] && e == s + 1))
            return 0


        return if (nums[mid] == nums[mid - 1]) {
            nonDuplicate(nums, s, mid - 2) + nonDuplicate(nums, mid + 1, e)
        } else
            nonDuplicate(nums, s, mid - 1) + nonDuplicate(nums, mid + 2, e)

    }
}

fun main() {
    val sol = Solution00540()
    println(sol.singleNonDuplicate2(intArrayOf(1, 1, 2, 3, 3, 4, 4, 8, 8)))
    println(sol.singleNonDuplicate2(intArrayOf(3, 3, 7, 7, 10, 11, 11)))
}