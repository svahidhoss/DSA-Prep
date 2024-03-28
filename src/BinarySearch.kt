class BinarySearch {
    // Returns the index of the first element equal to or greater than the target.
    // If there is no instance of the target in the list, it returns the length of the list.
    fun lowerBound(nums: IntArray, target: Int): Int {
        var start = 0
        var end = nums.size - 1
        var index = nums.size
        while (start <= end) {
            val mid = (start + end) / 2
            if (nums[mid] >= target) {
                end = mid - 1
                index = mid
            } else {
                start = mid + 1
            }
        }
        return index
    }

    // Returns the index of the first element greater than the target.
    // If there is no instance of the target in the list, it returns the length of the list.
    fun upperBound(nums: IntArray, target: Int): Int {
        var start = 0
        var end = nums.size - 1
        var index = nums.size
        while (start <= end) {
            val mid = (start + end) / 2
            if (nums[mid] > target) {
                end = mid - 1
                index = mid
            } else {
                start = mid + 1
            }
        }
        return index
    }
}

fun findAllOccurrences(arr: IntArray, target: Int): List<Int> {
    return arr.indices.filter { arr[it] == target }
}

fun main() {
    val arr = intArrayOf(2, 4, 6, 8, 10, 10, 10, 12, 14, 16, 18, 20)
    val target = 10

//    val occurrences = findAllOccurrences(arr, target)
//    if (occurrences.isNotEmpty()) {
//        println("Element $target found at indices: $occurrences")
//        println("The first index is: ${occurrences.first()}")
//        println("The last index is: ${occurrences.last()}")
//    } else {
//        println("Element not found in the array.")
//    }

    val result = arr.binarySearch(target)
    if (result >= 0) {
        println("Element found at index after binary search: $result")
    } else {
        println("Element not found in the list after binary search.")
    }
}
