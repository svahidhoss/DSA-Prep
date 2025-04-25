package java.amazon

data class Answer(val begIndex: Int, val endIndex: Int)

fun maximumBooks(books: IntArray): Long {
    val mem = mutableMapOf<Int, Int>()
    books.forEachIndexed { i, v ->
        for (j in i until books.size) {
//            if(mem())
        }
        var currentMax = v
    }

    return 0L
}

fun main() {
    val a = Answer(0, 6)
    val b = Answer(0, 6)
    println(a == b)
    val mem = mutableMapOf<Answer, Int>()
    mem[a] = 10
    mem[b] = 11

    println(mem[a])
    println(mem[b])


    println(maximumBooks(intArrayOf(8, 5, 2, 7, 9)))
    println(maximumBooks(intArrayOf(7, 0, 3, 4, 5)))
    println(maximumBooks(intArrayOf(8, 2, 3, 7, 3, 4, 0, 1, 4, 3)))
}