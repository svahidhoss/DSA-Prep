package java.kotlin_solutions

class Solution2250 {
    fun countRectangles(
        rectangles: Array<IntArray>,
        points: Array<IntArray>
    ): IntArray {
        val result = IntArray(rectangles.size)
        // Sort rectangles by their length
        val sortedPoints = rectangles.sortedBy { it[0] }
        val sortedPointsX = sortedPoints.map { it[0] }
        for (p in points) {
            val foundRec = sortedPointsX.binarySearch(p[0])
        }

        return result
    }
}

fun main() {
    val sol = Solution2250()
    var rectangles = arrayOf(intArrayOf(1, 2), intArrayOf(2, 3), intArrayOf(2, 5))
    var points = arrayOf(intArrayOf(2, 1), intArrayOf(1, 4))
    println(sol.countRectangles(rectangles, points))

    rectangles = arrayOf(intArrayOf(1, 1), intArrayOf(2, 2), intArrayOf(3, 3))
    points = arrayOf(intArrayOf(1, 3), intArrayOf(1, 1))
    println(sol.countRectangles(rectangles, points))
}