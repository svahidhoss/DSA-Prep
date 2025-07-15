package com.vahoss.kotlin_solutions

class Solution00994 {

    /**
     * You are given an m x n grid where each cell can have one of three values:
     *
     * 0 representing an empty cell,
     * 1 representing a fresh orange, or
     * 2 representing a rotten orange.
     * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
     *
     * Return the minimum number of minutes that must elapse until
     * no cell has a fresh orange. If this is impossible, return -1.
     */
    fun orangesRotting(grid: Array<IntArray>): Int {
        var minute = 0
        val queue = ArrayDeque<Pair<Int, Int>>()
        // 1.scan the whole grid for rotten oranges
        for (i in grid.indices) {
            for (j in grid[i].indices) {
                if (grid[i][j] == 2) {
                    queue.add(Pair(i, j))
                }
            }
        }

        // 2. Start doing a bfs with the rotten orange, add new elements to the new queue
        val newQueue = mutableListOf<Pair<Int, Int>>()
        while (queue.isNotEmpty()) {
            val pair = queue.removeFirst()
            // Check the adjacent points
            val points = getAdjacentPoints(pair, grid)
            newQueue.addAll(points)

            // Refresh the new queue
            if (queue.isEmpty() && newQueue.isNotEmpty()) {
                queue.addAll(newQueue)
                newQueue.clear()
                minute++
            }
        }

        // 3. Scan the whole grid for fresh oranges: return -1 if needed
        for (i in grid.indices) {
            for (j in grid[i].indices) {
                if (grid[i][j] == 1) {
                    return -1
                }
            }
        }

        // 4. return the min number of minutes
        return minute
    }

    private val directions = arrayOf(intArrayOf(0, 1), intArrayOf(-1, 0), intArrayOf(0, -1), intArrayOf(1, 0))

    private fun getAdjacentPoints(pair: Pair<Int, Int>, grid: Array<IntArray>): List<Pair<Int, Int>> {
        val result = mutableListOf<Pair<Int, Int>>()

        directions.forEach {
            val newRow = pair.first + it[0]
            val newCol = pair.second + it[1]
            if (newRow in grid.indices && newCol in grid[0].indices
                && grid[newRow][newCol] == 1
            ) {
                // Mark as rotten:
                grid[newRow][newCol] = 2
                result.add(Pair(newRow, newCol))
            }
        }

        return result
    }


    fun orangesRottingOptimized(grid: Array<IntArray>): Int {
        // Find all the rotten orange and the count of fresh ones
        val queue = ArrayDeque<Pair<Int, Int>>()
        // To know if we have finished rotting
        var freshCount = 0
        grid.forEachIndexed { row, ints ->
            ints.forEachIndexed { col, v ->
                when (v) {
                    1 -> freshCount++
                    2 -> queue.addLast(Pair(row, col))
                    else -> {}
                }
            }
        }

        if (freshCount == 0) return 0

        val directions = arrayOf(Pair(1, 0), Pair(0, 1), Pair(-1, 0), Pair(0, -1))
        var minutes = 0
        val rows = grid.size
        val cols = grid[0].size
        while (queue.isNotEmpty()) {
            // number of elements in the queue for this level
            val count = queue.size
            var anyRotten = false

            repeat(count) {
                val point = queue.removeFirst()
                for ((dx, dy) in directions) {
                    val newX = point.first + dx
                    val newY = point.second + dy
                    // check the boundary and if it is a fresh orange
                    if (newX in 0 until rows && newY in 0 until cols
                        && grid[newX][newY] == 1
                    ) {
                        // mark as rotten
                        grid[newX][newY] = 2
                        freshCount--
                        // visit later
                        queue.add(Pair(newX, newY))
                        anyRotten = true
                    }
                }
            }

            if (anyRotten) minutes++
        }

        // if not all fresh oranges have become rotten, we have not succeeded
        return if (freshCount > 0) -1 else minutes
    }

    data class Point(val x: Int, val y: Int)

    fun orangesRotting1(grid: Array<IntArray>): Int {
        // Find all the fresh and rotten orange
        val queue = ArrayDeque<Point>()
        // To know if we have finished rotting
        val freshSet = mutableSetOf<Point>()
        grid.forEachIndexed { row, ints ->
            ints.forEachIndexed { col, v ->
                when (v) {
                    1 -> freshSet.add(Point(row, col))
                    2 -> queue.add(Point(row, col))
                    else -> {}
                }
            }
        }

        var minute = 0
        val nextMinuteQueue = ArrayDeque<Point>()
        while (queue.isNotEmpty()) {
            val currentPoint = queue.removeFirst()
            rot(currentPoint, grid, freshSet, nextMinuteQueue)
            if (queue.isEmpty() && nextMinuteQueue.isNotEmpty()) {
                minute++
                queue.addAll(nextMinuteQueue)
                nextMinuteQueue.clear()
            }
        }

        // if not all fresh oranges have become rotten, we have not succeeded
        return if (freshSet.isNotEmpty()) -1 else minute
    }

    private fun rot(
        point: Point,
        grid: Array<IntArray>,
        freshSet: MutableSet<Point>,
        nextMinuteQueue: ArrayDeque<Point>
    ) {
        val directions = arrayOf(Pair(1, 0), Pair(0, 1), Pair(-1, 0), Pair(0, -1))
        val rows = grid.size
        val cols = grid[0].size
        for ((dx, dy) in directions) {
            val newX = point.x + dx
            val newY = point.y + dy
            // check the boundary and it is not part of rotten set
            if (newX in 0 until rows && newY in 0 until cols) {
                val newPoint = Point(newX, newY)
                if (freshSet.remove(newPoint)) nextMinuteQueue.add(newPoint)
            }
        }
    }


    fun orangesRotting2(grid: Array<IntArray>): Int {
        // Find all the rotten and fresh oranges
        val rotten = mutableSetOf<Pair<Int, Int>>()
        val fresh = mutableSetOf<Pair<Int, Int>>()
        grid.forEachIndexed { i, row ->
            row.forEachIndexed { j, v ->
                when (v) {
                    2 -> rotten.add(Pair(i, j))
                    1 -> fresh.add(Pair(i, j))
                }
            }
        }

        val directions = listOf(Pair(1, 0), Pair(0, 1), Pair(-1, 0), Pair(0, -1))
        var min = 0
        val toRotSet = mutableSetOf<Pair<Int, Int>>()
        // Do a BFS traversal until no longer possible
        while (true) {
            toRotSet.clear()
            rotten.forEach { point ->
                // check if possible to go to four dif directions
                directions.forEach { dir ->
                    val newRow = point.first + dir.first
                    val newCol = point.second + dir.second
                    if (newRow in grid.indices && newCol in grid[0].indices && grid[newRow][newCol] == 1) {
                        // mark as rotted
                        grid[newRow][newCol] = 2
                        val rottedPair = Pair(newRow, newCol)
                        toRotSet.add(rottedPair)
                        fresh.remove(rottedPair)
                    }
                }
            }
            if (toRotSet.isEmpty()) break
            rotten.addAll(toRotSet)
            min++
        }

        return if (fresh.isEmpty()) min else -1
    }
}

fun main() {
    val s = Solution00994()
    // Given 2D array of characters
    var grid: Array<IntArray> = arrayOf(
        intArrayOf(2, 1, 1),
        intArrayOf(1, 1, 0),
        intArrayOf(0, 1, 1),
    )
    println(s.orangesRotting(grid))

    grid = arrayOf(
        intArrayOf(2, 1, 1),
        intArrayOf(0, 1, 1),
        intArrayOf(1, 0, 1),
    )
    println(s.orangesRotting(grid))

    grid = arrayOf(intArrayOf(0, 2))
    println(s.orangesRotting(grid))
}
