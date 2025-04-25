package java.kotlin_solutions

fun exist(board: Array<CharArray>, word: String): Boolean {
    val visited = Array(board.size) { BooleanArray(board[0].size) }

    for (i in board.indices) {
        for (j in 0 until board[0].size) {
            if (board[i][j] == word[0] && dfs(i, j, board, word, visited)) return true
        }
    }

    return false
}

fun dfs(i: Int, j: Int, board: Array<CharArray>, word: String, visited: Array<BooleanArray>): Boolean {
    // If we have reached the end of the word
    if (visited.size == word.length - 1) return true

    // Boundary checks and character matching check
    if (i !in board.indices || j !in board[0].indices || board[i][j] != word[word.length - 1] || visited[i][j]) {
        return false
    }

    // Mark as visited
    visited[i][j] = true

    // Explore all directions
    for (direction in directions) {
        val newRow = i + direction.first
        val newCol = j + direction.second
        if (dfs(newRow, newCol, board, word, visited)) {
            return true
        }
    }

    // Backtrack
    visited[i][j] = false
    return false
}


val directions = arrayOf(Pair(0, 1), Pair(1, 0), Pair(0, -1), Pair(-1, 0))

fun exist2(board: Array<CharArray>, word: String): Boolean {
    board.forEachIndexed { i, chars ->
        chars.forEachIndexed { j, char ->
            if (word[0] == char && dfs(i, j, board, word, mutableSetOf())) return true
        }
    }

    return false
}

fun dfs(i: Int, j: Int, board: Array<CharArray>, word: String, visited: MutableSet<Pair<Int, Int>>): Boolean {
    println("visiting $i, $j -> ${board[i][j]}")
    visited.add(Pair(i, j))
    // if we have reached the end of the word
    if (visited.size == word.length) return true

    directions.forEach {
        val newRow = i + it.first
        val newCol = j + it.second
        if (newRow in board.indices && newCol in board[0].indices // boundary check
            && board[newRow][newCol] == word[visited.size]
            && !visited.contains(Pair(newRow, newCol))
        ) if (dfs(newRow, newCol, board, word, visited)) return true // keep DFSing!
    }

    println("backtracking by removing $i, $j -> ${board[i][j]}")
    // backtrack by removing the current cell from the visited set when a path does not lead to a solution
    visited.remove(Pair(i, j))

    return false
}

fun main() {
    var array = arrayOf(
        charArrayOf('a')
    )
    println(exist(array, "a")) // true

    array = arrayOf(
        charArrayOf('A', 'B', 'C', 'E'),
        charArrayOf('S', 'F', 'C', 'S'),
        charArrayOf('A', 'D', 'E', 'E')
    )
    println(exist(array, "ABCCED")) // true

    array = arrayOf(
        charArrayOf('A', 'B', 'C', 'E'),
        charArrayOf('S', 'F', 'C', 'S'),
        charArrayOf('A', 'D', 'E', 'E')
    )
    println(exist(array, "SEE")) // true

    array = arrayOf(
        charArrayOf('A', 'B', 'C', 'E'),
        charArrayOf('S', 'F', 'C', 'S'),
        charArrayOf('A', 'D', 'E', 'E')
    )
    println(exist(array, "ABCB")) // false

    array = arrayOf(
        charArrayOf('C', 'A', 'A'),
        charArrayOf('A', 'A', 'A'),
        charArrayOf('B', 'C', 'D')
    )
    println(exist(array, "AAB")) // true
}