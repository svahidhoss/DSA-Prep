package main.java.kotlin_solutions

class Solution00004 {
    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        val mergedArray = nums1 + nums2
        val sortedArray = mergedArray.sortedArray()

        val size = sortedArray.size

        return if (size % 2 == 1) sortedArray.elementAt(size / 2).toDouble()
        else (sortedArray.elementAt(size / 2) + sortedArray.elementAt(size / 2 - 1)) / 2.0
    }
}
