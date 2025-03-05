package kotlin_solutions

class Solution00011 {
    fun maxArea(height: IntArray): Int {
        var left = 0
        var right = height.size - 1
        var maxArea = 0

        while (left < right) {
            val min = Math.min(height[left], height[right])
            val area = min * (right - left)
            maxArea = Math.max(maxArea, area)

            if (height[left] < height[right]) left++
            else right--
        }

        return maxArea
    }
}

fun main() {
    val sol = Solution00011()
    // Example 1
    var array = intArrayOf(1, 8, 6, 2, 5, 4, 8, 3, 7)
    println(sol.maxArea(array))

    // Example 2
    array = intArrayOf(1, 1)
    println(sol.maxArea(array))

    // Example 3
    array = intArrayOf(2, 3, 4, 5, 18, 17, 6)
    println(sol.maxArea(array))
}
