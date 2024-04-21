package pramp


fun getDifferentNumber(arr: IntArray): Int {
    arr.forEachIndexed { i, v ->
        if (v < arr.size) {
            val temp = arr[v]
            arr[v] = v
            arr[i] = temp
        }
        while (arr[i] < i) {
            val temp = arr[arr[i]]
            arr[arr[i]] = arr[i]
            arr[i] = temp
        }
    }

    arr.forEachIndexed { i, v ->
        if (v != i) return i
    }
    // Every value is in its correct place
    return arr.size
}
fun getDifferentNumber1(arr: IntArray): Int {
    val min = arr.min()
    if (min > 0) return 0

    val set = arr.toSet()
    for (i in arr.indices) {
        if (!set.contains(i)) return i
    }

    return arr.size
}

fun main() {
    println(getDifferentNumber(intArrayOf(0)))
    println(getDifferentNumber(intArrayOf(0, 1, 2, 3)))
    println(getDifferentNumber(intArrayOf(0, 5, 4, 1, 3, 6, 2)))
    println(getDifferentNumber(intArrayOf(0, 100000)))
    println(getDifferentNumber(intArrayOf(1000000)))
    println("----")
    println(getDifferentNumber1(intArrayOf(0)))
    println(getDifferentNumber1(intArrayOf(0, 1, 2, 3)))
    println(getDifferentNumber1(intArrayOf(0, 5, 4, 1, 3, 6, 2)))
    println(getDifferentNumber1(intArrayOf(0, 100000)))
    println(getDifferentNumber1(intArrayOf(1000000)))
}
