package com.vahoss.fortinet

class Triplets {

    fun triplets(t: Long, d: Array<Int>): Long {
        // Step 1: Sort the array
        d.sort()
        var count = 0L

        // Iterate through the array with two pointers a and b from each triplet
        for (a in d.indices) {
            for (b in a + 1 until d.size) {
                // Calculate the maximum value that d[c] can take
                val remaining = (t - d[a] - d[b]).toInt()

                // Use Kotlin's binary search to find the index of the first element greater than remaining:
                // If such an element is found, binarySearch returns its index in a way that it can be used
                // to calculate the number of elements not exceeding remaining.
                // If no such element is found, binarySearch returns (-(insertion point) - 1),
                // where the insertion point is the index of the first element greater than remaining.
                val searchResult = d.binarySearch(remaining, fromIndex = b + 1)

                val c = if (searchResult >= 0) {
                    // If binarySearch found an element equal to remaining, find the last occurrence of this element
                    // This loop ensures we count all elements equal to remaining
                    var lastIndex = searchResult
                    while (lastIndex < d.size - 1 && d[lastIndex + 1] == remaining) {
                        lastIndex++
                    }
                    lastIndex
                } else {
                    // If binarySearch did not find an element equal to remaining, calculate the insertion point
                    -searchResult - 2
                }

                // Add the number of valid triplets with the current a and b to the count
                // Ensure c is greater than b before adding to count to avoid negative results from binary search indicating no valid c found
                if (c > b) {
                    count += (c - b)
                }
            }
        }

        return count
    }
}

fun main() {
    val sol = Triplets()

    var d = arrayOf(1, 2, 3, 4, 5)
    val t = 8L
    println("Number of triplets: ${sol.triplets(t, d)}")
    d = arrayOf(1, 2, 3, 4, 6)
    println("Number of triplets: ${sol.triplets(t, d)}")
    d = arrayOf(3, 1, 2, 4)
    println("Number of triplets: ${sol.triplets(7, d)}")
}
