package com.vahoss.kotlin_solutions

import java.util.*

class Solution00200 {
    fun numIslands(grid: Array<CharArray>): Int {
        // init the count
        var count = 0
        // check each element for land, then do a BFS search and mark them as water
        grid.forEachIndexed { i, row ->
            row.forEachIndexed { j, v ->
                if (v == '1') {
                    bfs(i, j, grid)
                    count++
                }
            }
        }

        return count
    }

    private fun bfs(row: Int, col: Int, grid: Array<CharArray>) {
        val directions = listOf(Pair(0, 1), Pair(1, 0), Pair(0, -1), Pair(-1, 0))
        // mark as land/visited
        grid[row][col] = '0'

        val queue = LinkedList<Pair<Int, Int>>()
        queue.offer(Pair(row, col))

        while (queue.isNotEmpty()) {
            val node = queue.poll()

            // Do a BFS expansion
            directions.forEach { dir ->
                val newRow = node.first + dir.first
                val newCol = node.second + dir.second
                if (newRow in grid.indices && newCol in grid[0].indices && grid[newRow][newCol] == '1')
                    queue.add(Pair(newRow, newCol))
                grid[newRow][newCol] = '0'  // Mark as visited
            }
        }
    }

    /**
     * Implements BFS in a recursive manner.
     */
    private fun visit(r: Int, c: Int, grid: Array<CharArray>) {
        // 1. mark as land
        grid[r][c] = '0'
        // 2. Do a BFS expansion
        if (r + 1 < grid.size && grid[r + 1][c] == '1') visit(r + 1, c, grid)
        if (r - 1 >= 0 && grid[r - 1][c] == '1') visit(r - 1, c, grid)
        if (c + 1 < grid[0].size && grid[r][c + 1] == '1') visit(r, c + 1, grid)
        if (c - 1 >= 0 && grid[r][c - 1] == '1') visit(r, c - 1, grid)
    }
}

fun main() {
    val s = Solution00200()
    // Given 2D array of characters
    var grid: Array<CharArray> = arrayOf(
        charArrayOf('1', '1', '1', '1', '0'),
        charArrayOf('1', '1', '0', '1', '0'),
        charArrayOf('1', '1', '0', '0', '0'),
        charArrayOf('0', '0', '0', '0', '0')
    )
    println(s.numIslands(grid))

    grid = arrayOf(
        charArrayOf('1', '1', '0', '0', '0'),
        charArrayOf('1', '1', '0', '0', '0'),
        charArrayOf('0', '0', '1', '0', '0'),
        charArrayOf('0', '0', '0', '1', '1')
    )
    println(s.numIslands(grid))
}
