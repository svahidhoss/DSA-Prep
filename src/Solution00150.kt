import java.util.*

class Solution00150 {
    fun rotate(nums: IntArray, k: Int): Unit {
        // in case k is bigger than the size of the array
        var steps = k
        if (k >= nums.size) steps %= nums.size

        // reverse once
        nums.reverse()
        nums.reverse(0, steps)
        nums.reverse(steps, nums.size)

        println(Arrays.toString(nums))
    }

    fun rotateLinearTime(nums: IntArray, k: Int): Unit {
        // in case k is bigger than the size of the array
        var steps = k
        if (k >= nums.size) steps %= nums.size

        val nums2 = nums.copyOfRange(nums.size - steps, nums.size) + nums.copyOfRange(0, nums.size - steps)
        nums.forEachIndexed { index, _ ->
            nums[index] = nums2[index]
        }

        println(Arrays.toString(nums))
    }

    fun rotateBruteForce(nums: IntArray, k: Int): Unit {
        // in case k is bigger than the size of the array
        var steps = k
        if (k >= nums.size) steps %= nums.size

        for (i in 0 until k) {
            for (j in nums.size - 1 downTo 1) {
                val temp = nums[j]
                nums[j] = nums[j - 1]
                nums[j - 1] = temp
            }
        }

        println(Arrays.toString(nums))
    }
}

fun main() {
    val sol = Solution00150()
    sol.rotateBruteForce(intArrayOf(1, 2, 3, 4, 5, 6, 7), 3)
    sol.rotateBruteForce(intArrayOf(1, 2, 3, 4, 5, 6, 7), 5)
    sol.rotateBruteForce(intArrayOf(-1, -100, 3, 99), 2)
    sol.rotateBruteForce(intArrayOf(-1, -2), 4)
    sol.rotateBruteForce(intArrayOf(-1), 2)
}