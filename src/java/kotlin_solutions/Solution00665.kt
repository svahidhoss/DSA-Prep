package java.kotlin_solutions

class Solution00665 {

    fun checkPossibility(nums: IntArray): Boolean {
        var decIndex = -1
        nums.forEachIndexed { i, v ->
            if (i != 0 && v < nums[i - 1]) {
                if (decIndex != -1) return false
                decIndex = i
            }
        }

        // If there's no decreasing element or the decreasing element is the first or last element
        if (decIndex <= 1 || decIndex == nums.size - 1) {
            return true
        }

        // If the decreasing element can be increased to be greater than or equal to its previous element
        if (nums[decIndex - 1] <= nums[decIndex + 1]) {
            return true
        }

        // If the decreasing element can be decreased to be less than or equal to its next element
        if (nums[decIndex - 2] <= nums[decIndex]) {
            return true
        }

        // If none of the above conditions are met, return false
        return false
    }

    // Not working properly
    fun checkPossibility2(nums: IntArray): Boolean {
        val nums2 = nums.mapIndexed { i, v -> if (i == 0) 0 else v - nums[i - 1] }
        val count = nums2.count { it < 0 }
        if (count >= 2) return false

        val id = nums2.indexOfFirst { it < 0 }
        return try {
            (nums[id - 2] >= nums[id - 1])
        } catch (e: ArrayIndexOutOfBoundsException) {
            true
        }
    }
}

fun main() {
    val sol = Solution00665()

    // Output: true
    println(sol.checkPossibility(intArrayOf(4, 2, 3)))
    // Output: false
    println(sol.checkPossibility(intArrayOf(4, 2, 1)))
    // Output: false
    println(sol.checkPossibility(intArrayOf(3, 4, 2, 3)))
    // Output: true
    println(sol.checkPossibility(intArrayOf(3, 3, 2, 3)))
}