class Solution00994 {
    fun orangesRotting(grid: Array<IntArray>): Int {
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
