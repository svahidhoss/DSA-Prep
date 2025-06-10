package com.vahoss.kotlin_solutions

class Solution00542 {

    // TODO solve using DP

    /**
     * Updates the input matrix so that each cell containing a 1 is replaced with the
     * distance to the nearest 0. Cells that already contain 0 remain unchanged.
     *
     * Approach:
     * - This uses a **multi-source Breadth-First Search (BFS)** algorithm.
     * - All cells with value 0 are treated as starting points (sources) and added to the queue.
     * - From each 0-cell, we explore its 4 neighbors (up/down/left/right) using BFS.
     * - Any unvisited 1-cell is updated with the shortest distance from a 0 and added to the queue.
     * - This guarantees that each cell is filled with its **minimum distance to a 0**.
     *
     * Time Complexity: O(m * n), where m and n are the dimensions of the matrix.
     * Space Complexity: O(m * n) for the result matrix and the BFS queue.
     */
    fun updateMatrix(mat: Array<IntArray>): Array<IntArray> {
        // -1 means not visited
        val result = Array(mat.size) { IntArray(mat[0].size) { -1 } }

        val queue = ArrayDeque<Pair<Int, Int>>()
        for (i in mat.indices) {
            for (j in mat[i].indices) {
                if (mat[i][j] == 0) {
                    result[i][j] = 0
                    queue.add(Pair(i, j))
                }
            }
        }

        while (queue.isNotEmpty()) {
            val (x, y) = queue.removeFirst()
            directions.forEach { direction ->
                val newI = x + direction.first
                val newJ = y + direction.second
                if (newI in mat.indices && newJ in mat[0].indices && result[newI][newJ] == -1) {
                    result[newI][newJ] = result[x][y] + 1
                    queue.add(Pair(newI, newJ))
                }
            }
        }

        return result
    }

    private val directions = arrayOf(Pair(0, 1), Pair(1, 0), Pair(0, -1), Pair(-1, 0))
}

fun main() {
    val sol = Solution00542()
    println(
        sol.updateMatrix(arrayOf(intArrayOf(0, 0, 0), intArrayOf(0, 1, 0), intArrayOf(0, 0, 0))).contentDeepToString()
    )
    println(
        sol.updateMatrix(arrayOf(intArrayOf(0, 0, 0), intArrayOf(0, 1, 0), intArrayOf(1, 1, 1))).contentDeepToString()
    )
}