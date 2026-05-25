package com.vahoss.kotlin_solutions

class Solution00542 {


    /**
     * Approach: two-pass Dynamic Programming.
     *   1. Init: copy matrix — keep 0s as 0, set all 1s to (m + n) as a safe upper-bound sentinel
     *   2. Pass 1 — top-left → bottom-right:
     *        for each cell, take the min of its current value vs (top neighbor + 1) and (left neighbor + 1)
     *   3. Pass 2 — bottom-right → top-left:
     *        for each cell, take the min of its current value vs (bottom neighbor + 1) and (right neighbor + 1)
     *   4. Return the result matrix
     *
     * Why it works: pass 1 propagates shortest distances reachable from top/left;
     *               pass 2 covers bottom/right — together all 4 directions are handled.
     *
     * Time: O(m * n) — two linear passes
     * Space: O(1) extra — only the output matrix
     */
    fun updateMatrixDP(mat: Array<IntArray>): Array<IntArray> {
        val m = mat.size
        val n = mat[0].size
        val result = Array(m) { i -> IntArray(n) { j -> if (mat[i][j] == 1) m + n else 0 } }

        for (i in 0 until m) {
            for (j in 0 until n) {
                // equivalent to: if (i > 0)
                if (i - 1 in mat.indices)
                    result[i][j] = minOf(result[i - 1][j] + 1, result[i][j])
                // equivalent to: if (j > 0)
                if (j - 1 in mat[0].indices)
                    result[i][j] = minOf(result[i][j - 1] + 1, result[i][j])
            }
        }

        for (i in m - 1 downTo 0) {
            for (j in n - 1 downTo 0) {
                // equivalent to: if (i < m - 1)
                if (i + 1 in mat.indices)
                    result[i][j] = minOf(result[i + 1][j] + 1, result[i][j])
                // equivalent to: if (j < n - 1)
                if (j + 1 in mat[0].indices)
                    result[i][j] = minOf(result[i][j + 1] + 1, result[i][j])
            }
        }


        return result
    }

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
        sol.updateMatrixDP(arrayOf(intArrayOf(0, 0, 0), intArrayOf(0, 1, 0), intArrayOf(0, 0, 0))).contentDeepToString()
    )

    println(
        sol.updateMatrix(arrayOf(intArrayOf(0, 0, 0), intArrayOf(0, 1, 0), intArrayOf(1, 1, 1))).contentDeepToString()
    )
    println(
        sol.updateMatrixDP(arrayOf(intArrayOf(0, 0, 0), intArrayOf(0, 1, 0), intArrayOf(1, 1, 1))).contentDeepToString()
    )
}