import java.util.PriorityQueue
import kotlin.math.pow
import kotlin.math.sqrt

data class Point(val distance: Double, val x: Int, val y: Int)

/**
 * Optimal solution: O(n log k)
 */
fun kClosest(points: Array<IntArray>, k: Int): Array<IntArray> {
    val maxHeap = PriorityQueue<Point> { a, b ->
        when {
            a.distance < b.distance -> 1
            a.distance > b.distance -> -1
            else -> 0
        }
    }
    // val maxHeap = PriorityQueue<Point> { a, b -> b.distance.compareTo(a.distance) }

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
 * Second attempt.
 */
fun kClosest2(points: Array<IntArray>, k: Int): Array<IntArray> {
    val minHeap = PriorityQueue<Point> { a, b ->
        when {
            a.distance < b.distance -> -1
            a.distance > b.distance -> 1
            else -> 0
        }
    }

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
 * My first attempt.
 *
 * Positive aspects:
 * 1.The overall approach using a min-heap (PriorityQueue) is correct
 * and efficient.
 * 2.The distance calculation function is implemented correctly.
 * 3.The code handles the input and output formats properly.
 *
 * Areas for improvement:
 * 1.Using a map to store distances and points can lead to issues if
 * multiple points have the same distance. This could result in
 * losing some points.
 * 2.The current implementation has a time complexity of O(n log n)
 * due to adding all points to the heap. It can be optimized to O(n log k)
 * 3.Unnecessary use of Double for distances when Float would suffice.
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

    points = arrayOf(
        intArrayOf(3, 3),
        intArrayOf(5, -1),
        intArrayOf(-2, 4)
    )
    println(kClosest(points, 2).contentDeepToString())  // [[3, 3], [-2, 4]]

    points = arrayOf(
        intArrayOf(0, 1),
        intArrayOf(1, 0)
    )
    println(kClosest(points, 2).contentDeepToString()) // [[0,1],[1,0]]
}
