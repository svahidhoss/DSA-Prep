package main.java

import java.util.*

class MedianFinder {

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

class MedianFinderInsertionSort {

    private val numbers = mutableListOf<Int>()

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

    fun findMedian(): Double {
        return if (numbers.size % 2 == 1) numbers[numbers.size / 2].toDouble()
        else (numbers[numbers.size / 2 - 1] + numbers[numbers.size / 2]) / 2.0
    }
}

fun main() {
    var medianFinder = MedianFinderInsertionSort()

    medianFinder.addNum(1) // arr = [1]
    medianFinder.addNum(2) // arr = [1, 2]
    println(medianFinder.findMedian())// return 1.5 (i.e., (1 + 2) / 2)

    medianFinder.addNum(3) // arr[1, 2, 3]
    println(medianFinder.findMedian()) // return 2.0

    // 2nd Scenario
    medianFinder = MedianFinderInsertionSort()
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
