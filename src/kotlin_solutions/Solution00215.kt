package kotlin_solutions

import java.util.PriorityQueue

class Solution0215 {
    fun findKthLargest(nums: IntArray, k: Int): Int {
        val minHeap = PriorityQueue<Int>()

        for (n in nums) {
            minHeap.add(n)
            if (minHeap.size > k) minHeap.poll()
        }

        return minHeap.peek()
    }

    fun findKthLargestMaxHeap(nums: IntArray, k: Int): Int {
        val maxHeap = PriorityQueue<Int>(Comparator.reverseOrder())

        for (n in nums) maxHeap.add(n)
        for (i in 1 until k) maxHeap.poll()

        return maxHeap.peek()
    }

    // n log(n)
    fun findKthLargestUsingSort(nums: IntArray, k: Int): Int {
        nums.sort()
        return nums[nums.size - k]
    }
}

fun main() {
    val sol = Solution0215()

    println(sol.findKthLargest(intArrayOf(3, 2, 1, 5, 6, 4), 2))
    println(sol.findKthLargest(intArrayOf(3, 2, 3, 1, 2, 4, 5, 5, 6), k = 4))
}
