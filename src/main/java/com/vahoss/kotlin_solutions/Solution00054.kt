package com.vahoss.kotlin_solutions


class Solution00054 {
    val directions = arrayOf(0 to 1, 1 to 0, 0 to -1, -1 to 0)

    /**
     * Walks in the current direction (right, down, left, up, cycling) until the
     * next cell would be out of bounds or already visited, then turns.
     * Time: O(m * n) - every cell visited once.
     * Space: O(m * n) - the visited grid, same size as matrix.
     */
    fun spiralOrder(matrix: Array<IntArray>): List<Int> {
        val m = matrix.size
        val n = matrix[0].size
        val result = mutableListOf<Int>()

        // false by default
        val visited = Array(m) { BooleanArray(n) }

        // current point
        var row = 0
        var col = 0
        var dirIndex = 0
        var dir = directions[dirIndex]
        while (result.size < m * n) {
            if (!visited[row][col]) {
                println("row $row and col $col is ${matrix[row][col]}")
                result.add(matrix[row][col])
                visited[row][col] = true
            }
            val nRow = row + dir.first
            val nCol = col + dir.second

            if (nRow in matrix.indices && nCol in matrix[0].indices && !visited[nRow][nCol]) {
                row = nRow
                col = nCol
            } else {
                dir = directions[++dirIndex % directions.size]
            }
        }
        return result
    }

    /**
     * Shrinking boundaries instead of a visited grid: track top/bottom/left/right
     * edges of the remaining unvisited region and walk one full edge per side
     * (right along top, down along right, left along bottom, up along left),
     * shrinking the boundary just walked before moving to the next side. The
     * two inner `if` guards skip the bottom-row/left-column walk once the top/right
     * walk has already consumed the last remaining row/column (single row or
     * single column matrices) - without them you'd revisit cells.
     * Time: O(m * n) - every cell visited once.
     * Space: O(1) extra - four boundary variables, no visited grid needed.
     */
    fun spiralOrderOptimized(matrix: Array<IntArray>): List<Int> {
        val result = mutableListOf<Int>()
        var top = 0
        var bottom = matrix.size - 1
        var left = 0
        var right = matrix[0].size - 1

        while (top <= bottom && left <= right) {
            for (col in left..right) result.add(matrix[top][col])
            top++

            for (row in top..bottom) result.add(matrix[row][right])
            right--

            if (top <= bottom) {
                for (col in right downTo left) result.add(matrix[bottom][col])
                bottom--
            }

            if (left <= right) {
                for (row in bottom downTo top) result.add(matrix[row][left])
                left++
            }
        }

        return result
    }

}

fun main() {
    val sol = Solution00054()

    data class TestCase(val matrix: Array<IntArray>, val expected: List<Int>)

    val testCases = listOf(
        TestCase(
            arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9)),
            listOf(1, 2, 3, 6, 9, 8, 7, 4, 5)
        ),
        TestCase(
            arrayOf(intArrayOf(1, 2, 3, 4), intArrayOf(5, 6, 7, 8), intArrayOf(9, 10, 11, 12)),
            listOf(1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7)
        ),
        TestCase(arrayOf(intArrayOf(1)), listOf(1)),
        TestCase(arrayOf(intArrayOf(1, 2), intArrayOf(3, 4)), listOf(1, 2, 4, 3)),
        TestCase(arrayOf(intArrayOf(1, 2, 3, 4)), listOf(1, 2, 3, 4)),
        TestCase(arrayOf(intArrayOf(1), intArrayOf(2), intArrayOf(3)), listOf(1, 2, 3)),
    )

    for (tc in testCases) {
        val r1 = sol.spiralOrder(tc.matrix)
        val r2 = sol.spiralOrderOptimized(tc.matrix)
        println("spiralOrder: $r1 ${if (r1 == tc.expected) "OK" else "MISMATCH"}, spiralOrderOptimized: $r2 ${if (r2 == tc.expected) "OK" else "MISMATCH"}, Expected: ${tc.expected}")
    }
}