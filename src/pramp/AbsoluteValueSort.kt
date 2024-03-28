package pramp

import java.util.PriorityQueue
import kotlin.math.abs

/**
 * The custom comparator provided here, first compares the absolute
 * values of the elements. If the absolute values are equal, it then
 * compares the elements themselves to ensure that negative numbers
 * come before positive numbers when their absolute values are the same.
 */
fun absSort(arr: IntArray): IntArray {
    // Use a single priority queue with a custom comparator for absolute values
    /*val absHeap = PriorityQueue<Int> { a, b ->
        val absCompare = abs(a).compareTo(abs(b))
        if (absCompare != 0) absCompare else a.compareTo(b)
    }*/
    val absHeap = PriorityQueue<Int> { a, b ->
        if(abs(a) == abs(b)) a - b
        else abs(a) - abs(b)
    }

    // Add all elements to the heap
    for (v in arr) {
        absHeap.add(v)
    }

    // Poll from the heap to get the sorted order based on absolute values
    val result = IntArray(arr.size)
    for (i in arr.indices) {
        result[i] = absHeap.poll()
    }
    return result
}

fun absSort2(arr: IntArray): IntArray {
    val posMinHeap = PriorityQueue<Int>()
    val negMaxHeap = PriorityQueue<Int> { a, b -> b - a }

    for (v in arr) {
        if (v < 0) negMaxHeap.add(v)
        else posMinHeap.add(v)
    }

    val result = IntArray(arr.size)
    var i = 0
    while (posMinHeap.isNotEmpty() || negMaxHeap.isNotEmpty()) {
        result[i++] = when {
            posMinHeap.peek() == null -> negMaxHeap.poll()
            negMaxHeap.peek() == null -> posMinHeap.poll()
            abs(negMaxHeap.peek()) <= posMinHeap.peek() -> negMaxHeap.poll()
            else -> posMinHeap.poll()
        }
    }
    return result
}

fun absSort3(arr: IntArray): IntArray {
    arr.sort()

    var midIndex = arr.binarySearch(0)
    if (midIndex < 0) midIndex = -midIndex - 1

    val result = IntArray(arr.size)
    var p = midIndex - 1
    var q = midIndex
    var count = 0
    while (p >= 0 && q < arr.size) {
        val val1 = arr[p]
        val val2 = arr[q]
        if (val1 == -val2) {
            result[count] = val1
            p--
        } else {
            result[count] = val2
            q++
        }
        count++
    }
    return result
}

fun main() {
    println(absSort(intArrayOf(2, -7, -2, -2, 0)).contentToString())
    println(absSort(intArrayOf(-2, -1)).contentToString())
    println(absSort(intArrayOf(0, 1, 2)).contentToString())
    println(absSort(intArrayOf(2, -1, -1, -1)).contentToString())
    println(absSort(intArrayOf(-2, 3, 5, -1, 4)).contentToString())

    println(absSort(intArrayOf(-2, -1, 0, 1, 2)).joinToString(", ")) // Output: 0, -1, 1, -2, 2
}