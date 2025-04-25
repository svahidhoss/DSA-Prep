package java.sort

import java.util.PriorityQueue

fun heapSort(nums: IntArray): List<Int> {
    val minHeap = PriorityQueue<Int>()
    nums.forEach { minHeap.add(it) }
    return List(nums.size) { minHeap.poll() }
}

fun main() {
    val nums = intArrayOf(4, 2, 1, 3, 0)
    println(heapSort(nums)) // prints [0, 1, 2, 3, 4]
}
