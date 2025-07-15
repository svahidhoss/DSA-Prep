package com.vahoss.kotlin_solutions

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

class Solution00994KtTest {

    @BeforeEach
    fun setUp() {
    }

    @AfterEach
    fun tearDown() {
    }


    private val solution = Solution00994()

    @Nested
    @DisplayName("Basic Test Cases")
    inner class BasicTests {

        @Test
        @DisplayName("Standard case - all oranges rot in 4 minutes")
        fun testStandardCase() {
            val grid = arrayOf(
                intArrayOf(2, 1, 1),
                intArrayOf(1, 1, 0),
                intArrayOf(0, 1, 1)
            )
            assertEquals(4, solution.orangesRotting(grid))
        }

        @Test
        @DisplayName("Impossible case - isolated fresh orange")
        fun testImpossibleCase() {
            val grid = arrayOf(
                intArrayOf(2, 1, 1),
                intArrayOf(0, 1, 1),
                intArrayOf(1, 0, 1)
            )
            assertEquals(-1, solution.orangesRotting(grid))
        }

        @Test
        @DisplayName("Already all rotten - 0 minutes")
        fun testAlreadyRotten() {
            val grid = arrayOf(
                intArrayOf(0, 2)
            )
            assertEquals(0, solution.orangesRotting(grid))
        }
    }

    @Nested
    @DisplayName("Edge Cases")
    inner class EdgeCases {

        @Test
        @DisplayName("Empty grid")
        fun testEmptyGrid() {
            val grid = arrayOf<IntArray>()
            assertEquals(0, solution.orangesRotting(grid))
        }

        @Test
        @DisplayName("Single cell - empty")
        fun testSingleCellEmpty() {
            val grid = arrayOf(intArrayOf(0))
            assertEquals(0, solution.orangesRotting(grid))
        }

        @Test
        @DisplayName("Single cell - fresh orange (impossible)")
        fun testSingleCellFresh() {
            val grid = arrayOf(intArrayOf(1))
            assertEquals(-1, solution.orangesRotting(grid))
        }

        @Test
        @DisplayName("Single cell - rotten orange")
        fun testSingleCellRotten() {
            val grid = arrayOf(intArrayOf(2))
            assertEquals(0, solution.orangesRotting(grid))
        }

        @Test
        @DisplayName("Only empty cells")
        fun testOnlyEmptyCells() {
            val grid = arrayOf(
                intArrayOf(0, 0, 0),
                intArrayOf(0, 0, 0),
                intArrayOf(0, 0, 0)
            )
            assertEquals(0, solution.orangesRotting(grid))
        }

        @Test
        @DisplayName("No rotten oranges initially")
        fun testNoRottenOranges() {
            val grid = arrayOf(
                intArrayOf(1, 1, 1),
                intArrayOf(1, 1, 1),
                intArrayOf(1, 1, 1)
            )
            assertEquals(-1, solution.orangesRotting(grid))
        }
    }

    @Nested
    @DisplayName("Multiple Rotten Sources")
    inner class MultipleRottenSources {

        @Test
        @DisplayName("Two rotten oranges at opposite corners")
        fun testTwoRottenCorners() {
            val grid = arrayOf(
                intArrayOf(2, 1, 0, 0, 0, 0, 0, 0, 1, 2),
                intArrayOf(0, 1, 1, 1, 1, 1, 1, 1, 1, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
            )
            assertEquals(5, solution.orangesRotting(grid))
        }

        @Test
        @DisplayName("Multiple rotten oranges - faster spread")
        fun testMultipleRottenFaster() {
            val grid = arrayOf(
                intArrayOf(2, 1, 1, 1, 1, 1, 1, 2)
            )
            assertEquals(3, solution.orangesRotting(grid))
        }

        @Test
        @DisplayName("Rotten oranges in middle")
        fun testRottenInMiddle() {
            val grid = arrayOf(
                intArrayOf(1, 1, 1),
                intArrayOf(1, 2, 1),
                intArrayOf(1, 1, 1)
            )
            assertEquals(2, solution.orangesRotting(grid))
        }
    }

    @Nested
    @DisplayName("Complex Scenarios")
    inner class ComplexScenarios {

        @Test
        @DisplayName("Large grid with mixed pattern")
        fun testLargeGridImpossible() {
            val grid = arrayOf(
                intArrayOf(2, 1, 1, 0, 0, 0, 1, 1, 1, 2),
                intArrayOf(1, 0, 1, 0, 1, 0, 1, 0, 1, 1),
                intArrayOf(1, 1, 1, 0, 1, 0, 1, 1, 1, 1),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(1, 1, 1, 0, 1, 0, 1, 1, 1, 1),
                intArrayOf(1, 0, 1, 0, 1, 0, 1, 0, 1, 1),
                intArrayOf(2, 1, 1, 0, 0, 0, 1, 1, 1, 2)
            )
            assertEquals(-1, solution.orangesRotting(grid))
        }

        @Test
        @DisplayName("Barriers blocking spread")
        fun testBarriers() {
            val grid = arrayOf(
                intArrayOf(2, 1, 1),
                intArrayOf(0, 0, 0),
                intArrayOf(1, 1, 1)
            )
            assertEquals(-1, solution.orangesRotting(grid))
        }

        @Test
        @DisplayName("L-shaped pattern")
        fun testLShape() {
            val grid = arrayOf(
                intArrayOf(2, 1, 1, 1),
                intArrayOf(0, 0, 0, 1),
                intArrayOf(0, 0, 0, 1),
                intArrayOf(0, 0, 0, 1)
            )
            assertEquals(6, solution.orangesRotting(grid))
        }
    }

    @Nested
    @DisplayName("Performance Tests")
    inner class PerformanceTests {

        @Test
        @DisplayName("Large grid - all fresh with one rotten")
        fun testLargeGridPerformance() {
            val size = 10
            val grid = Array(size) { IntArray(size) { 1 } }
            grid[0][0] = 2  // Single rotten orange at top-left

            // Should take (size-1) + (size-1) = 2*(size-1) minutes to reach bottom-right
            assertEquals(2 * (size - 1), solution.orangesRotting(grid))
        }

        @Test
        @DisplayName("Large grid - all rotten")
        fun testLargeGridAllRotten() {
            val size = 100
            val grid = Array(size) { IntArray(size) { 2 } }
            assertEquals(0, solution.orangesRotting(grid))
        }
    }

    @Nested
    @DisplayName("Grid Modification Tests")
    inner class GridModificationTests {

        @Test
        @DisplayName("Verify grid is modified correctly")
        fun testGridModification() {
            val grid = arrayOf(
                intArrayOf(2, 1, 1),
                intArrayOf(1, 1, 0),
                intArrayOf(0, 1, 1)
            )

            val result = solution.orangesRotting(grid)

            // All oranges should be rotten (2) or empty (0)
            for (i in grid.indices) {
                for (j in grid[i].indices) {
                    assertTrue(grid[i][j] != 1, "Fresh orange found at [$i][$j] after processing")
                }
            }

            assertEquals(4, result)
        }

        @Test
        @DisplayName("Original grid is preserved for impossible case")
        fun testImpossibleCaseGridState() {
            val grid = arrayOf(
                intArrayOf(2, 1, 1),
                intArrayOf(0, 1, 1),
                intArrayOf(1, 0, 1)
            )

            val result = solution.orangesRotting(grid)

            // Should return -1 and some fresh oranges should remain
            assertEquals(-1, result)

            // Count remaining fresh oranges
            var freshCount = 0
            for (i in grid.indices) {
                for (j in grid[i].indices) {
                    if (grid[i][j] == 1) freshCount++
                }
            }
            assertTrue(freshCount > 0, "Should have fresh oranges remaining in impossible case")
        }
    }
}

// Mock solution class for testing
/*class OrangesRottingSolution {
    fun orangesRotting(grid: Array<IntArray>): Int {
        if (grid.isEmpty()) return 0

        var minute = 0
        val queue = ArrayDeque<Pair<Int, Int>>()

        // Find all initially rotten oranges
        for (i in grid.indices) {
            for (j in grid[i].indices) {
                if (grid[i][j] == 2) {
                    queue.add(Pair(i, j))
                }
            }
        }

        val directions = arrayOf(intArrayOf(0, 1), intArrayOf(-1, 0), intArrayOf(0, -1), intArrayOf(1, 0))

        // BFS level by level
        while (queue.isNotEmpty()) {
            val size = queue.size
            var rottenThisRound = false

            repeat(size) {
                val (row, col) = queue.removeFirst()

                directions.forEach { dir ->
                    val newRow = row + dir[0]
                    val newCol = col + dir[1]

                    if (newRow in grid.indices && newCol in grid[0].indices && grid[newRow][newCol] == 1) {
                        grid[newRow][newCol] = 2
                        queue.add(Pair(newRow, newCol))
                        rottenThisRound = true
                    }
                }
            }

            if (rottenThisRound) minute++
        }

        // Check if any fresh oranges remain
        for (i in grid.indices) {
            for (j in grid[i].indices) {
                if (grid[i][j] == 1) return -1
            }
        }

        return minute
    }

}*/

