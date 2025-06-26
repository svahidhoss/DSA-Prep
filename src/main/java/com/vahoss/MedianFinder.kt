package com.vahoss

import java.util.*

// Problem 295
class MedianFinder {

    private val minHeap = PriorityQueue<Int>()
    private val maxHeap = PriorityQueue<Int> { a, b -> b - a }

    fun addNum(num: Int) {
        // Step 1: Add to maxHeap first (lower half)
        maxHeap.offer(num)

        // Step 2: Ensure ordering: move the max of maxHeap to minHeap
        minHeap.offer(maxHeap.poll())

        // Step 3: Re-balance if needed (maxHeap can have one more than minHeap)
        if (minHeap.size > maxHeap.size) {
            maxHeap.offer(minHeap.poll())
        }
    }

    fun findMedian(): Double {
        return if (maxHeap.size > minHeap.size) maxHeap.peek().toDouble()
        else (maxHeap.peek() + minHeap.peek()) / 2.0
    }

}


class MedianFinder2 {

    private val topMinHeap = PriorityQueue<Int>()

    private val botMaxHeap = PriorityQueue<Int>(compareBy { -it })

    fun addNum(num: Int) {
        if (topMinHeap.isEmpty()) topMinHeap.add(num)
        else if (num >= topMinHeap.peek()) topMinHeap.add(num)
        else botMaxHeap.add(num)

        if (Math.abs(topMinHeap.size - botMaxHeap.size) > 1) adjustHeap()
    }

    fun findMedian(): Double {
        // if size is even, they should have equal size
        return if (size() % 2 == 0) (topMinHeap.peek() + botMaxHeap.peek()) / 2.0
        else if (topMinHeap.size > botMaxHeap.size) topMinHeap.peek().toDouble()
        else botMaxHeap.peek().toDouble()
    }

    fun size() = topMinHeap.size + botMaxHeap.size

    private fun adjustHeap() {
        if (topMinHeap.size - botMaxHeap.size > 1) botMaxHeap.add(topMinHeap.poll())
        else topMinHeap.add(botMaxHeap.poll())
    }
}

/**
 * Time Complexity:
 * Operation	    Insertion Sort List	    Two Heaps (Optimized)
 * addNum()	        O(n)	                O(log n)
 * findMedian()	    O(1)	                O(1)
 */
class MedianFinderInsertionSort {

    private val numbers = mutableListOf<Int>()

    /**
     * Even if you use binary search to find
     * the position in O(log n),
     * you still need to shift elements to make space:
     * that’s O(n) in the worst case.
     */
    fun addNum(num: Int) {
        numbers.add(num)

        var i = numbers.size - 2
        while (i >= 0 && num < numbers[i]) {
            val temp = numbers[i]
            numbers[i] = numbers[i + 1]
            numbers[i + 1] = temp
            i--
        }
    }

    fun addNumBinarySearch(num: Int) {
        val i = numbers.binarySearch(num)
        val insertPos = if (i >= 0) i else -i - 1
        numbers.add(insertPos, num)
    }

    fun findMedian(): Double {
        return if (numbers.size % 2 == 1) numbers[numbers.size / 2].toDouble()
        else (numbers[numbers.size / 2 - 1] + numbers[numbers.size / 2]) / 2.0
    }
}

fun main() {
    var medianFinder = MedianFinder()

    medianFinder.addNum(1) // arr = [1]
    medianFinder.addNum(2) // arr = [1, 2]
    println(medianFinder.findMedian())// return 1.5 (i.e., (1 + 2) / 2)

    medianFinder.addNum(3) // arr[1, 2, 3]
    println(medianFinder.findMedian()) // return 2.0

    // 2nd Scenario
    medianFinder = MedianFinder()
    medianFinder.addNum(-1)
    println(medianFinder.findMedian())
    medianFinder.addNum(-2)
    println(medianFinder.findMedian())
    medianFinder.addNum(-3)
    println(medianFinder.findMedian())
    medianFinder.addNum(-4)
    println(medianFinder.findMedian())
    medianFinder.addNum(-5)
    println(medianFinder.findMedian())

}
