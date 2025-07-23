package com.vahoss.kotlin_solutions

class Solution0062 {
    // TODO: Add the Mathematical/Combinatorial Solution (Most Efficient)

    fun uniquePaths(m: Int, n: Int): Int {
        // -1: not visited yet
        val grid = Array(m) { IntArray(n) { -1 } }
        // Base case: 1 way to reach destination
        grid[m - 1][n - 1] = 1
        return uniquePaths(0, 0, grid)
    }

    private fun uniquePaths(i: Int, j: Int, grid: Array<IntArray>): Int {
        // Already computed and visited
        if (grid[i][j] != -1) return grid[i][j]

        val down = if (i + 1 < grid.size) uniquePaths(i + 1, j, grid) else 0
        val right = if (j + 1 < grid[0].size) uniquePaths(i, j + 1, grid) else 0
        grid[i][j] = down + right
        return grid[i][j]
    }
}

fun main() {
    val sol = Solution0062()
    println(sol.uniquePaths(3, 2))
    println(sol.uniquePaths(3, 3))
    println(sol.uniquePaths(3, 7))
}