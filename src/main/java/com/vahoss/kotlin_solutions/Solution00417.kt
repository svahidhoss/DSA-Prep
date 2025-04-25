package com.vahoss.kotlin_solutions

import java.util.ArrayDeque
import java.util.Queue

private val possibleDirections = arrayOf(Pair(0, 1), Pair(1, 0), Pair(0, -1), Pair(-1, 0))

data class Coordinate(val row: Int, val col: Int)

class Solution00417 {

    /**
     * BFS solution
     */
    fun pacificAtlanticBFS(heights: Array<IntArray>): List<List<Int>> {
        val m = heights.size
        val n = heights[0].size
        if (m == 0 || n == 0) return emptyList()

        val pacificReachable = Array(m) { BooleanArray(n) }
        val atlanticReachable = Array(m) { BooleanArray(n) }

        // create a queue for pacific ocean elements
        val pacificQueue: Queue<Coordinate> = ArrayDeque()
        for (i in 0 until m) {
            pacificQueue.offer(Coordinate(i, 0))
            pacificReachable[i][0] = true
        }
        for (i in 0 until n) {
            pacificQueue.offer(Coordinate(0, i))
            pacificReachable[0][i] = true
        }
        bfs(pacificQueue, pacificReachable, heights)

        // create a queue for atlantic ocean elements
        val atlanticQueue: Queue<Coordinate> = ArrayDeque()
        for (i in 0 until m) {
            atlanticQueue.offer(Coordinate(i, n - 1))
            atlanticReachable[i][n - 1] = true
        }
        for (i in 0 until n) {
            atlanticQueue.offer(Coordinate(m - 1, i))
            atlanticReachable[m - 1][i] = true
        }
        bfs(atlanticQueue, atlanticReachable, heights)

        // preparing the answers
        val answers = mutableListOf<List<Int>>()
        for (r in 0 until m) {
            for (c in 0 until n) {
                if (pacificReachable[r][c] && atlanticReachable[r][c]) answers.add(listOf(r, c))
            }
        }

        return answers
    }

    private fun bfs(
        queue: Queue<Coordinate>,
        reachable: Array<BooleanArray>,
        heights: Array<IntArray>
    ) {
        while (queue.isNotEmpty()) {
            val (row, col) = queue.poll()
            for ((dirRow, dirCol) in possibleDirections) {
                val newR = row + dirRow
                val newC = col + dirCol
                // if within bounds, not visited yet and if height is acceptable
                if (newR in heights.indices && newC in heights[0].indices
                    && !reachable[newR][newC]
                    && heights[newR][newC] >= heights[row][col]
                ) {
                    reachable[newR][newC] = true
                    // Do a BFS for new directions
                    queue.offer(Coordinate(newR, newC))
                }
            }
        }
    }

    fun pacificAtlantic(heights: Array<IntArray>): List<List<Int>> {
        if (heights.isEmpty() || heights[0].isEmpty()) return emptyList()

        val m = heights.size
        val n = heights[0].size

        val pacificReachable = Array(m) { BooleanArray(n) }
        val atlanticReachable = Array(m) { BooleanArray(n) }

        for (i in 0 until m) dfs(heights, pacificReachable, i, 0)
        for (i in 0 until n) dfs(heights, pacificReachable, 0, i)

        for (i in 0 until m) dfs(heights, atlanticReachable, i, n - 1)
        for (i in 0 until n) dfs(heights, atlanticReachable, m - 1, i)


        // preparing the answers
        val answers = mutableListOf<List<Int>>()
        for (r in 0 until m) {
            for (c in 0 until n) {
                if (pacificReachable[r][c] && atlanticReachable[r][c]) answers.add(listOf(r, c))
            }
        }

        return answers
    }

    private fun dfs(heights: Array<IntArray>, reachable: Array<BooleanArray>, r: Int, c: Int) {
        reachable[r][c] = true

        for (dir in possibleDirections) {
            val newR = r + dir.first
            val newC = c + dir.second
            // check if the new node is eligible for exploration
            if (newR in heights.indices
                && newC in heights[0].indices
                && !reachable[newR][newC]
                && heights[newR][newC] >= heights[r][c]
            ) {
                // explore its adjacent nodes
                dfs(heights, reachable, newR, newC)
            }
        }
    }

    /**
     * accepted points:
     * [0, n-1]
     * [m-1, 0]
     *
     * 1p 2p 2p 3P 5AP
     * 3P 2P 3P 4P 4A
     * 2p 4, 5, 3  1a
     * 6p 7, 1,4  5a
     * 5ap 1a 1a 2a 4a
     *
     * 1. Create a mem grid m*n, put oceans that have immediate access, (+visited mem)
     * 2. Go thorough all the points in the array and mark them
     * 3. Return the ap grids
     *
     * 0: N
     * 1: A
     * 2: P
     * 3: AP
     */
    fun pacificAtlanticFirstAttempt(heights: Array<IntArray>): List<List<Int>> {
        val m = heights.size
        val n = heights[0].size

        // setting up memory and visited
        val visited = Array(m) { BooleanArray(n) }
        val memory = Array(m) { r ->
            IntArray(n) { c ->
                if (r == 0 && c == 0) 1
                else if (r == m - 1 && c == n - 1) 2
                else 0
            }
        }

        memory[0][n - 1] = 3
        visited[0][n - 1] = true

        memory[m - 1][0] = 3
        visited[m - 1][0] = true

        for (i in heights.indices) {
            for (j in heights[i].indices) {
                if (!visited[i][j]) recOcean(i, j, heights, memory, visited)
            }
        }

        // preparing the answers
        val answers = mutableListOf<List<Int>>()
        memory.forEachIndexed { r, ints ->
            ints.forEachIndexed { c, v ->
                if (v == 3) {
                    answers.add(listOf(r, c))
                }
            }
        }

        return answers
    }

    private fun recOcean(
        row: Int,
        col: Int,
        heights: Array<IntArray>,
        memory: Array<IntArray>,
        visited: Array<BooleanArray>,
        preRow: Int = -1,
        preCol: Int = -1
    ) {
        // check if pre exists
        if (preRow != -1 && heights[row][col] > heights[preRow][preCol]) return

        if (memory[row][col] == -1) {

        }

        visited[row][col] = true
    }
}

fun main() {
    val solution = Solution00417()  // Assuming your class is named Solution

    // Test Case 1: Example from the problem statement
    val heights1 = arrayOf(
        intArrayOf(1, 2, 2, 3, 5),
        intArrayOf(3, 2, 3, 4, 4),
        intArrayOf(2, 4, 5, 3, 1),
        intArrayOf(6, 7, 1, 4, 5),
        intArrayOf(5, 1, 1, 2, 4)
    )
    println("Test Case 1:")
    println("Input: ${heights1.contentDeepToString()}")
    println("Output: ${solution.pacificAtlantic(heights1)}")
    println()

    // Test Case 2: Single cell
    val heights2 = arrayOf(intArrayOf(1))
    println("Test Case 2:")
    println("Input: ${heights2.contentDeepToString()}")
    println("Output: ${solution.pacificAtlantic(heights2)}")
    println()

    // Test Case 3: 2x2 grid
    val heights3 = arrayOf(
        intArrayOf(1, 2),
        intArrayOf(2, 1)
    )
    println("Test Case 3:")
    println("Input: ${heights3.contentDeepToString()}")
    println("Output: ${solution.pacificAtlantic(heights3)}")
    println()

    // Test Case 4: All cells with the same height
    val heights4 = arrayOf(
        intArrayOf(1, 1, 1),
        intArrayOf(1, 1, 1),
        intArrayOf(1, 1, 1)
    )
    println("Test Case 4:")
    println("Input: ${heights4.contentDeepToString()}")
    println("Output: ${solution.pacificAtlantic(heights4)}")
    println()

    // Test Case 5: Increasing heights from top-left to bottom-right
    val heights5 = arrayOf(
        intArrayOf(1, 2, 3),
        intArrayOf(4, 5, 6),
        intArrayOf(7, 8, 9)
    )
    println("Test Case 5:")
    println("Input: ${heights5.contentDeepToString()}")
    println("Output: ${solution.pacificAtlantic(heights5)}")
    println()
}
