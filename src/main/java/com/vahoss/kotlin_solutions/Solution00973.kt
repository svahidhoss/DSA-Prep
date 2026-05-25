package com.vahoss.kotlin_solutions

import java.util.PriorityQueue
import kotlin.math.pow
import kotlin.math.sqrt

data class Point0(val x: Int, val y: Int, val distance: Int)

/**
 * Optimal solution using a max-heap of size k.
 *
 * Approach: maintain a max-heap capped at k elements. For each new point, if it is
 * closer than the current farthest (heap root), evict the root and insert the new point.
 * After all n points the heap holds exactly the k closest.
 *
 * Uses squared distance (x² + y²) instead of sqrt — avoids floating-point entirely.
 * Safe because sqrt is monotone, so relative order is preserved.
 *
 * Time:  O(n log k) — each point triggers at most one heap push/pop (log k)
 * Space: O(k) — heap never exceeds k elements
 */
fun kClosest0(points: Array<IntArray>, k: Int): Array<IntArray> {
    // Safe here since distances are non-negative, but worth swapping to:
    // val maxHeap = PriorityQueue<Point0> { a, b -> b.distance - a.distance }
    val maxHeap = PriorityQueue<Point0>(compareByDescending { it.distance })
    points.forEach {
        val newPoint = Point0(
            it[0], it[1],
            it[0] * it[0] + it[1] * it[1]
        )
        if (maxHeap.size == k) {
            if (maxHeap.peek().distance > newPoint.distance) {
                maxHeap.remove()
                maxHeap.offer(newPoint)
            }
        } else { // size < k
            maxHeap.offer(newPoint)
        }

    }
    val result = mutableListOf<IntArray>()

    repeat(k) {
        val point = maxHeap.remove()
        result.add(intArrayOf(point.x, point.y))
    }

    return result.toTypedArray()
}

data class Point(val distance: Double, val x: Int, val y: Int)


/**
 * Optimal solution: O(n log k)
 */
fun kClosest(points: Array<IntArray>, k: Int): Array<IntArray> {
    val maxHeap = PriorityQueue<Point>(compareByDescending { it.distance })

    points.forEach {
        val newPoint = Point(getDistance(it), it[0], it[1])
        if (maxHeap.size < k) {
            maxHeap.offer(newPoint)
        } else if (maxHeap.peek().distance > newPoint.distance) {
            maxHeap.poll()
            maxHeap.offer(newPoint)
        }
    }

    val result = mutableListOf<IntArray>()
    while (maxHeap.isNotEmpty()) {
        val point = maxHeap.poll()
        result.add(intArrayOf(point.x, point.y))
    }
    return result.toTypedArray()
}

/**
 * Second attempt. This is O(n logn) time complexity and O(n) space complexity.
 */
fun kClosest2(points: Array<IntArray>, k: Int): Array<IntArray> {
    val minHeap = PriorityQueue<Point>(compareBy { it.distance })

    points.forEach {
        val newPoint = Point(getDistance(it), it[0], it[1])
        minHeap.offer(newPoint)
    }

    val result = mutableListOf<IntArray>()
    repeat(k) {
        val point = minHeap.poll()
        result.add(intArrayOf(point.x, point.y))
    }
    return result.toTypedArray()
}

/**
 * First attempt — INCORRECT for duplicate distances.
 *
 * Uses a min-heap of distances paired with a Map<Double, IntArray> for lookup.
 * Bug: map keys are distances, so two points at the same distance overwrite each other.
 * The overwritten point is lost; the subsequent map lookup returns null and is silently
 * skipped, producing a result with fewer than k elements.
 *
 * Time:  O(n log n) — all points enter the heap
 * Space: O(n)
 */
fun kClosest1(points: Array<IntArray>, k: Int): Array<IntArray> {
    val minHeap = PriorityQueue<Double>()
    val map = mutableMapOf<Double, IntArray>()

    points.forEach {
        val distance = getDistance(it)
        minHeap.add(distance)
        map[distance] = it
    }

    val result = mutableListOf<IntArray>()
    repeat(k) {
        map[minHeap.poll()]?.let {
            result.add(it)
        }
    }
    return result.toTypedArray()
}

private fun getDistance(point: IntArray): Double {
    return sqrt(point[0].toDouble().pow(2.0) + point[1].toDouble().pow(2.0))
}

fun main() {
    var points: Array<IntArray> = arrayOf(
        intArrayOf(1, 3),
        intArrayOf(-2, 2)
    )
    println(kClosest(points, 1).contentDeepToString()) // [[-2, 2]]
    println(kClosest0(points, 1).contentDeepToString()) // [[-2, 2]]

    points = arrayOf(
        intArrayOf(3, 3),
        intArrayOf(5, -1),
        intArrayOf(-2, 4)
    )
    println(kClosest(points, 2).contentDeepToString())  // [[3, 3], [-2, 4]]
    println(kClosest0(points, 2).contentDeepToString())  // [[3, 3], [-2, 4]]

    points = arrayOf(
        intArrayOf(0, 1),
        intArrayOf(1, 0)
    )
    println(kClosest(points, 2).contentDeepToString()) // [[0,1],[1,0]]
    println(kClosest0(points, 2).contentDeepToString()) // [[0,1],[1,0]]
}
