package main.java.pramp

fun shiftedArrSearch2(shiftArr: IntArray, num: Int): Int {
    for (i in shiftArr.indices) {
        if (shiftArr[i] == num) return i
    }

    return -1
}

fun shiftedArrSearch(shiftArr: IntArray, num: Int): Int {
    var p = 0
    var q = shiftArr.size - 1
    var m = 0
    while (p <= q) {
        m = p + (q - p) / 2
        if (m > 0 && shiftArr[m] < shiftArr[m - 1]) break // pivot point is found
        if (shiftArr[m] > shiftArr[q]) p = m + 1
        else q = m - 1
    }

    if (num >= shiftArr[m] && num <= shiftArr.last()) {
        // right side
        p = m
        q = shiftArr.size - 1
    } else {
        // left side
        p = 0
        q = m - 1
    }

    val result = shiftArr.binarySearch(num, p, q + 1)
    return if (result >= 0) result else -1
}

fun main() {
//    println(shiftedArrSearch2(intArrayOf(2), 2))
//    println(shiftedArrSearch(intArrayOf(2), 2))
//    println(shiftedArrSearch2(intArrayOf(1, 2), 2))
//    println(shiftedArrSearch(intArrayOf(1, 2), 2))
//    println(shiftedArrSearch2(intArrayOf(0, 1, 2, 3, 4, 5), 1))
//    println(shiftedArrSearch(intArrayOf(0, 1, 2, 3, 4, 5), 1))

    // TODO fix this one
//    println(shiftedArrSearch2(intArrayOf(1, 2, 3, 4, 5, 0), 0))
    println(shiftedArrSearch(intArrayOf(1, 2, 3, 4, 5, 0), 0))
    println(shiftedArrSearch2(intArrayOf(9, 12, 17, 2, 4, 5), 17))
    println(shiftedArrSearch(intArrayOf(9, 12, 17, 2, 4, 5), 17))
    println(shiftedArrSearch2(intArrayOf(9, 12, 17, 2, 4, 5, 6), 4))
    println(shiftedArrSearch(intArrayOf(9, 12, 17, 2, 4, 5, 6), 4))
}