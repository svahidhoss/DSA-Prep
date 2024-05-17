package pramp


fun findPairsWithGivenDifference(arr: IntArray, k: Int): Array<IntArray> {

    // convert to set
    val set = arr.toSet()

    // pairs is used to make sure uniqueness is implemented
    val answers = mutableSetOf<Pair<Int, Int>>()

    // find the pairs: abs(i - v) = k => v = k-i or i-k
    for (i in arr) {
        if (set.contains(i - k)) answers.add(Pair(i, i - k))
        if (set.contains(i + k)) answers.add(Pair(i + k, i))
    }


    // Convert the answers to a 2D array
    return answers.map { intArrayOf(it.first, it.second) }.toTypedArray()
}

fun findPairsWithGivenDifference2(arr: IntArray, k: Int): Array<IntArray> {
    // Convert to set for O(1) lookups
    val set = arr.toSet()
    val answers = mutableSetOf<IntArray>()

    // Find the pairs: abs(i - v) = k => v = i - k or v = i + k
    for (i in arr) {
        val smaller = minOf(i, i - k)
        val larger = maxOf(i, i - k)
        if (set.contains(larger)) {
            answers.add(intArrayOf(smaller, larger))
        }

        val smaller2 = minOf(i, i + k)
        val larger2 = maxOf(i, i + k)
        if (set.contains(larger2)) {
            answers.add(intArrayOf(smaller2, larger2))
        }
    }

    // Convert the answers to a 2D array
    return answers.toTypedArray()
}


fun main() {
    var arr = intArrayOf(1, 5, 2, 2, 2, 3, 4)
    var k = 3

    var result = findPairsWithGivenDifference(arr, k)
    for (pair in result) {
        print(pair.contentToString() + "\t")
    }

    println()

    arr = intArrayOf(0, -1, -2, 2, 1)
    k = 1

    result = findPairsWithGivenDifference(arr, k)
    for (pair in result) {
        print(pair.contentToString() + "\t")
    }

    println()
    arr = intArrayOf(1, 7, 5, 3, 32, 17, 12)
    k = 17

    result = findPairsWithGivenDifference(arr, k)
    for (pair in result) {
        print(pair.contentToString() + "\t")
    }
}