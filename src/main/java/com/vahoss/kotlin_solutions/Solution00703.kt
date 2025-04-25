package com.vahoss.kotlin_solutions

import java.util.*

class KthLargest(private val k: Int, nums: IntArray) {
    private val minHeap = PriorityQueue<Int>().apply { addAll(nums.asList()) }

    fun add(`val`: Int): Int {
        minHeap.offer(`val`)
        while (minHeap.size > k) minHeap.poll()
        return minHeap.peek()
    }
}

class KthLargest2(private val k: Int, nums: IntArray) {
    // A min heap that contains the k largest elements of nums
    private val minHeap = PriorityQueue<Int>().apply {
        addAll(nums.toList())
        while (size > k) poll()
    }

    fun add(`val`: Int): Int {
        println("heap is $minHeap")
        if (minHeap.isEmpty()) minHeap.offer(`val`)
        else if (minHeap.isNotEmpty() && minHeap.peek() < `val`) {
            minHeap.offer(`val`)
            minHeap.poll()
        }

        println("heap is now $minHeap")
        return minHeap.peek()
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * var obj = KthLargest(k, nums)
 * var param_1 = obj.add(`val`)
 */
fun main() {
    val kthLargest = KthLargest2(3, intArrayOf(4, 5, 8, 2))
    println(kthLargest.add(3))   // return 4
    println(kthLargest.add(5))   // return 5
    println(kthLargest.add(10))  // return 5
    println(kthLargest.add(9))   // return 8
    println(kthLargest.add(4))
}