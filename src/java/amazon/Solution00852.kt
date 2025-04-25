package java.amazon

class Solution00852 {
    fun peakIndexInMountainArray(arr: IntArray): Int {
        var beg = 0
        var end = arr.size - 1
        var mid = (beg + end) / 2
        // The stopping point is where the mid is bigger than both its pre and next points
        // Also follows the constraints
        while (!(arr[mid] > arr[mid + 1] && arr[mid] > arr[mid - 1])) {
            mid = (beg + end) / 2
            if (arr[mid] > arr[beg] && arr[mid] > arr[end]) {
                // check if mid-value is bigger than the next element
                if (arr[mid] > arr[mid + 1]) end = mid
                else beg = mid
            } else if (arr[mid] > arr[beg]) beg = mid
            else end = mid
        }

        return mid
    }

    fun peakIndexInMountainArray2(arr: IntArray): Int {
        var beg = 0
        var end = arr.size - 1
        // Initialize mid inside the loop
        while (true) {
            val mid = (beg + end) / 2
            // If mid is greater than both its neighbors, it's the peak
            if (arr[mid] > arr[mid + 1] && arr[mid] > arr[mid - 1]) {
                return mid
            }
            // If mid is not the peak, adjust beg or end based on comparison with neighbors
            if (arr[mid] > arr[mid + 1]) end = mid
            else beg = mid
        }
    }
}

fun main() {
    val s = Solution00852()
    println(s.peakIndexInMountainArray2(intArrayOf(0, 1, 0)))
    println(s.peakIndexInMountainArray2(intArrayOf(0, 2, 1, 0)))
    println(s.peakIndexInMountainArray2(intArrayOf(0, 10, 5, 2)))
    println(s.peakIndexInMountainArray2(intArrayOf(3, 4, 5, 1)))
    println(s.peakIndexInMountainArray2(intArrayOf(12, 13, 19, 41, 55, 69, 70, 71, 96, 72)))
}