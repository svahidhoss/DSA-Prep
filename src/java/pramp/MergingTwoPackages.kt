package java.pramp


fun getIndicesOfItemWeights(arr: IntArray, limit: Int): IntArray {

    val map = mutableMapOf<Int, Int>()
    arr.forEachIndexed { i, v ->
        val complement = limit - v
        map[complement]?.let {
            return intArrayOf(i, it)
        }
        map[v] = i
    }

    return intArrayOf()
}

fun main() {
    println(getIndicesOfItemWeights(intArrayOf(4, 6, 10, 15, 16), 21).contentToString())
}