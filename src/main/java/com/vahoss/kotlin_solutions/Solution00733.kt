package com.vahoss.kotlin_solutions

class Solution00733 {
    private val directions = arrayOf(Pair(1, 0), Pair(0, 1), Pair(-1, 0), Pair(0, -1))

    fun floodFill(image: Array<IntArray>, sr: Int, sc: Int, color: Int): Array<IntArray> {
        val origColor = image[sr][sc]
        // BFS search
        val deque = ArrayDeque<Pair<Int, Int>>()
        if (origColor != color) deque.add(Pair(sr, sc))

        while (deque.isNotEmpty()) {
            val (x, y) = deque.removeFirst()
            image[x][y] = color
            for ((dx, dy) in directions) {
                val newX = x + dx
                val newY = y + dy
                if(newX in image.indices && newY in image[0].indices && image[newX][newY] == origColor)
                    deque.add(Pair(newX, newY))
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

    private fun floodFillRecursive(image: Array<IntArray>, sr: Int, sc: Int, origColor: Int, color: Int) {
        val currentColor = image[sr][sc]
        if (currentColor == origColor) {
            image[sr][sc] = color
            directions.forEach { dir ->
                val newR = sr + dir.first
                val newC = sc + dir.second
                if (newR in image.indices && newC in image[0].indices) {
                    floodFillRecursive(image, newR, newC, origColor, color)
                }
            }
        }
    }

    fun floodFillDFS2(image: Array<IntArray>, sr: Int, sc: Int, color: Int): Array<IntArray> {
        val origColor = image[sr][sc]
        if (origColor == color) return image

        val stack = ArrayDeque<Pair<Int, Int>>()
        stack.add(Pair(sr, sc))

        while (stack.isNotEmpty()) {
            val (x, y) = stack.removeLast()
            image[x][y] = color

            for ((dx, dy) in directions) {
                val newX = x + dx
                val newY = y + dy

                if (
                    newX in image.indices &&
                    newY in image[0].indices &&
                    image[newX][newY] == origColor
                ) {
                    stack.add(Pair(newX, newY))
                }
            }
        }

        return image
    }
}
