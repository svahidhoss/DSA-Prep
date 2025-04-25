package main.java.pramp

import kotlin.collections.ArrayDeque
import kotlin.math.min

fun shortestCellPathRec(grid: Array<IntArray>, sr: Int, sc: Int, tr: Int, tc: Int): Int {
    // base case
    if (sr == tr && sc == tc) return 0

    val visitedGrid = Array(grid.size) {
        BooleanArray(grid[0].size)
    }

    val minPath = scpRec(grid, visitedGrid, sr, sc, tr, tc)
    return if (minPath == Int.MAX_VALUE) -1 else minPath
}

fun scpRec(
    grid: Array<IntArray>,
    visitedGrid: Array<BooleanArray>,
    sr: Int, sc: Int, tr: Int, tc: Int
): Int {
    // base case
    if (sr == tr && sc == tc) return 0

    val adjacentPoints = getAdjPoints(grid, visitedGrid, sr, sc)
    visitedGrid[sr][sc] = true

    var shortestAdjCellPath = Int.MAX_VALUE
    adjacentPoints.forEach { point ->
        shortestAdjCellPath = min(scpRec(grid, visitedGrid, point[0], point[1], tr, tc), shortestAdjCellPath)
    }
    return if (shortestAdjCellPath == Int.MAX_VALUE) shortestAdjCellPath
    else 1 + shortestAdjCellPath
}

/**
 * BFS graph traversal!
 */
fun shortestCellPath(grid: Array<IntArray>, sr: Int, sc: Int, tr: Int, tc: Int): Int {
    val queue = ArrayDeque<IntArray>()

    val visited = Array(grid.size) { rowIndex ->
        BooleanArray(grid[rowIndex].size)
    }
    queue.add(intArrayOf(sr, sc))

    var path = 0
    while (queue.isNotEmpty()) {
        val currentPoint = queue.removeFirst()
        if (currentPoint[0] == tr && currentPoint[1] == tc) return path
        // Mark the current point as visited
        visited[currentPoint[0]][currentPoint[1]] = true
        queue.addAll(getAdjPoints(grid, visited, currentPoint[0], currentPoint[1]))
        path++
    }
    return -1
}

private fun getAdjPoints(grid: Array<IntArray>, visited: Array<BooleanArray>, r: Int, c: Int): List<IntArray> {
    return listOfNotNull(
        isValidPoint(grid, visited, r - 1, c) to intArrayOf(r - 1, c),
        isValidPoint(grid, visited, r + 1, c) to intArrayOf(r + 1, c),
        isValidPoint(grid, visited, r, c - 1) to intArrayOf(r, c - 1),
        isValidPoint(grid, visited, r, c + 1) to intArrayOf(r, c + 1)
    ).filter { it.first } // Filter pairs where the first value is true
        .map { it.second } // Map to the second value
}

private fun isValidPoint(grid: Array<IntArray>, visited: Array<BooleanArray>, r: Int, c: Int): Boolean {
    return r in grid.indices && c in grid[0].indices && !visited[r][c] && grid[r][c] == 1
}

fun main() {
    var grid = arrayOf(intArrayOf(1, 1, 1, 1), intArrayOf(0, 0, 0, 1), intArrayOf(1, 1, 1, 1))
    println(shortestCellPathRec(grid, 0, 0, 2, 0))  // Expected output: 8

    grid = arrayOf(intArrayOf(1, 1, 1, 1), intArrayOf(0, 0, 0, 1), intArrayOf(1, 0, 1, 1))
    println(shortestCellPath(grid, 0, 0, 2, 0))  // Expected output: -1

    grid = arrayOf(intArrayOf(0, 1, 0), intArrayOf(1, 0, 0), intArrayOf(1, 0, 1))
    println(shortestCellPath(grid, 2, 0, 1, 0))  // Expected output: 1

    grid = arrayOf(intArrayOf(1, 1, 1), intArrayOf(0, 0, 0), intArrayOf(0, 0, 0))
    println(shortestCellPath(grid, 0, 1, 0, 0))  // Expected output: 1
}
