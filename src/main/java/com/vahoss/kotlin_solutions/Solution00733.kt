package com.vahoss.kotlin_solutions

class Solution00733 {
    private val directions = arrayOf(Pair(1, 0), Pair(0, 1), Pair(-1, 0), Pair(0, -1))

    fun floodFill(image: Array<IntArray>, sr: Int, sc: Int, color: Int): Array<IntArray> {
        val origColor = image[sr][sc]
        // Early exit
        if (origColor == color) return image

        val queue = ArrayDeque<Pair<Int, Int>>()

        // Mark visited when ENQUEUING, not when DEQUEUING
        image[sr][sc] = color
        queue.add(Pair(sr, sc))

        while (queue.isNotEmpty()) {
            val (x, y) = queue.removeFirst()

            // check the adjacent nodes in BFS
            directions.forEach { (dirX, dirY) ->
                val newX = x + dirX
                val newY = y + dirY
                if (newX in image.indices
                    && newY in image[0].indices
                    && image[newX][newY] == origColor
                ) {
                    // Mark visited when ENQUEUING, not when DEQUEUING
                    // change to the fill color
                    image[newX][newY] = color
                    queue.add(Pair(newX, newY))
                }
            }
        }

        return image
    }

    fun floodFillDFS(image: Array<IntArray>, sr: Int, sc: Int, color: Int): Array<IntArray> {
        val origColor = image[sr][sc]
        // Early return if original color is the same as new color (prevents infinite recursion)
        if (origColor == color) return image

        floodFillRecursive(image, sr, sc, origColor, color)
        return image
    }

    /**
     * Both approaches are O(M × N) time, but BFS is significantly more
     * space-efficient in practice. DFS space is bounded by recursion depth
     * which can be O(M × N) in the worst case — a real stack overflow risk
     * on large inputs.
     *
     * BFS queue space is bounded by the frontier width,
     * which is at most O(min(M, N)). For production code on large images
     * I'd prefer BFS or an iterative DFS with an explicit stack.
     */
    private fun floodFillRecursive(image: Array<IntArray>, sr: Int, sc: Int, origColor: Int, color: Int) {
        // paint on entry, marks visited, prevents revisit
        image[sr][sc] = color

        directions.forEach { (x, y) ->
            val newX = sr + x
            val newY = sc + y
            if (newX in image.indices
                && newY in image[0].indices
                && image[newX][newY] == origColor
            ) {
                floodFillRecursive(image, newX, newY, origColor, color)
            }
        }
    }

    fun floodFillDFSIterative(image: Array<IntArray>, sr: Int, sc: Int, color: Int): Array<IntArray> {
        val origColor = image[sr][sc]
        if (origColor == color) return image

        val stack = ArrayDeque<Pair<Int, Int>>()
        image[sc][sr] = color
        stack.add(Pair(sr, sc))

        while (stack.isNotEmpty()) {
            val (x, y) = stack.removeLast()

            for ((dx, dy) in directions) {
                val newX = x + dx
                val newY = y + dy

                if (
                    newX in image.indices &&
                    newY in image[0].indices &&
                    image[newX][newY] == origColor
                ) {
                    image[newX][newY] = color
                    stack.add(Pair(newX, newY))
                }
            }
        }

        return image
    }
}
