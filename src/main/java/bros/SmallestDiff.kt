package main.java.bros

// Given two arrays of integers,
// compute the pair of values with the smallest difference.
// Return the difference.

// Example:
// Input:
// A = [1, 3, 15, 11, 2]
// B = [23, 127, 235, 19, 8]
// Output:
// 3 (i.e Pair [11,8])

/*

var min = Int.Max

O(nA * nB)
O(1)

p
1 2 3 11 15
8 19 23 127 235
q

diff 3

 */

data class Answer(val a: Int?, val b: Int?, val diff: Int)

fun smallestDiff(array1: IntArray, array2: IntArray): Answer {

    // Can sort the arrays in place?
    array1.sort()
    array2.sort()

    var c1: Int? = null
    var c2: Int? = null
    var minDiff = Int.MAX_VALUE
    var p = 0
    var q = 0
    while (p < array1.size && q < array2.size) {
        val aValue = array1[p]
        val bValue = array2[q]
        val diff = Math.abs(aValue - bValue)

        if (aValue >= bValue) q++
        else p++

        // update the min, c1 and c2 pair if there is an update
        if (diff < minDiff) {
            minDiff = diff
            c1 = Math.min(aValue, bValue)
            c2 = Math.max(aValue, bValue)
        }
    }

    val answer = Answer(c1, c2, if (minDiff == Int.MAX_VALUE) -1 else minDiff)
    return answer
}

fun smallestDiff2(array1: IntArray, array2: IntArray): Answer {
    array1.sort()
    array2.sort()

    var c1: Int? = null
    var c2: Int? = null
    var minDiff = Int.MAX_VALUE
    var p = 0
    var q = 0
    while (p < array1.size && q < array2.size) {
        val aValue = array1[p]
        val bValue = array2[q]
        val diff = if (aValue > bValue) aValue.toLong() - bValue else bValue.toLong() - aValue // Prevent overflow

        if (aValue >= bValue) q++
        else p++

        if (diff < minDiff) {
            minDiff = diff.toInt()
            c1 = aValue
            c2 = bValue
        }
    }

    return Answer(c1, c2, minDiff)
}


fun main() {

    // for (i in 1..5) println("Hello, World!")
    var A = intArrayOf(1, 3, 15, 11, 2)
    var B = intArrayOf(23, 127, 235, 19, 8)
    var result = smallestDiff(A, B)
    println(result)

    A = intArrayOf()
    B = intArrayOf()
    result = smallestDiff(A, B)
    println(result)

    A = intArrayOf()
    B = intArrayOf()
    result = smallestDiff(A, B)
    println(result)

    A = intArrayOf(Int.MAX_VALUE, Int.MAX_VALUE - 1)
    B = intArrayOf()
    result = smallestDiff(A, B)
    println(result)

    A = intArrayOf(Int.MAX_VALUE)
    B = intArrayOf(Int.MIN_VALUE)
    result = smallestDiff(A, B)
    println(result)

    A = intArrayOf(100, 2, 15)
    B = intArrayOf(100, 5)
    result = smallestDiff(A, B)
    println(result)
}
