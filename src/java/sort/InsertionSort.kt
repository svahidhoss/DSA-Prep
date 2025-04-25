package java.sort

class InsertionSort {


}

fun insertionSort(arr: IntArray) {
    for (i in 1 until arr.size) {
        val key = arr[i]
        var j = i - 1

        // Move elements of arr[0..i-1], that are greater than key,
        // to one position ahead of their current position
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j]
            j--
        }
        arr[j + 1] = key
    }
}

fun main() {
    val numbers = intArrayOf(12, 11, 13, 5, 6)
    println("Original Array: ${numbers.joinToString()}")
    println("Original Array: ${numbers.contentToString()}")

    insertionSort(numbers)

    println("Sorted Array: ${numbers.joinToString()}")
}
