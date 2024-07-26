import java.util.PriorityQueue
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * My first attempt.
 */
fun kClosest(points: Array<IntArray>, k: Int): Array<IntArray> {
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
