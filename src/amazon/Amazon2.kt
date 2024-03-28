package amazon

import java.util.*

class Amazon2 {

    fun processLogs(logs: Array<String>, threshold: Int): Array<String> {
        // Map to store the count of transactions for each user
        val transactionCountsMap = mutableMapOf<String, Int>()

        // Process each log entry
        logs.forEach {
            // Split the log entry into parts
            val parts = it.split(" ")
            val senderId = parts[0]
            val recId = parts[1]

            // Update the transaction count for the sender
            transactionCountsMap[senderId] = transactionCountsMap.getOrDefault(senderId, 0) + 1

            // Update the transaction count for the recipient if it's a different user
            if (senderId != recId) {
                transactionCountsMap[recId] = transactionCountsMap.getOrDefault(recId, 0) + 1
            }
        }

        // Filter the users that meet or exceed the threshold and sort them
        return transactionCountsMap.filter { it.value >= threshold }
            .keys
            .sortedBy { it.toInt() }
            .toTypedArray()
    }


    fun maximumBookCopies(portalUpdate: Array<Int>): Array<Int> {
        // Write your code here
        val result = IntArray(portalUpdate.size)

        val bookMaxHeap = PriorityQueue<Book> { a, b -> b.count - a.count }
        val inventoryMap = mutableMapOf<Int, Int>()

        for (i in portalUpdate.indices) {
            val id = Math.abs(portalUpdate[i])
            val count = inventoryMap.getOrDefault(id, 0) + if (portalUpdate[i] > 0) 1 else -1
            inventoryMap[id] = count
            println("map is: $inventoryMap")

            val book = Book(id, count)
            bookMaxHeap.add(book)
            println("max heap is $bookMaxHeap")
            // remove books with outdated counts
            while (bookMaxHeap.peek().count != inventoryMap[bookMaxHeap.peek().id]!!) {
                println("${bookMaxHeap.poll()} was polled")
            }

            result[i] = bookMaxHeap.peek().count
            println("result[i] -> ${result[i]} \n")
        }

        return result.toTypedArray()
    }

    data class Book(val id: Int, var count: Int)

    fun findReviewScore(review: String, prohibitedWords: Array<String>): Int {
        // Write your code here
        val lcReview = review.lowercase()
        val lcProhibitedWords = prohibitedWords.map { it.lowercase() }

        var result = 0
        var p = 0
        var q = 0

        while (q < lcReview.length) {
            val currentSubstring = lcReview.substring(p, q + 1)
            val containedWord = lcProhibitedWords.firstOrNull { it in currentSubstring }

            containedWord?.let {
                p = q - it.length
            } ?: apply {
                if (currentSubstring.length > result) result = currentSubstring.length
            }
            q++
        }

        return result
    }

    fun numberOfItems(s: String, startIndices: Array<Int>, endIndices: Array<Int>): Array<Int> {
        // Write your code here


        val cumulativeSum = IntArray(s.length)
        var currentSum = 0
        var inCompartment = false

        s.forEachIndexed { i, c ->
            if (c == '|') {
                // only for the first one
                if (!inCompartment) inCompartment = !inCompartment
                cumulativeSum[i] = currentSum
            } else if (c == '*' && inCompartment) {
                currentSum++
                cumulativeSum[i] = cumulativeSum[i - 1]
            }
        }

        println(cumulativeSum.contentToString())

        val result = Array(startIndices.size) { 0 }
        for (i in startIndices.indices) {
            var beg = startIndices[i] - 1
            var end = endIndices[i] - 1
            while (s[beg] != '|') {
                beg++
            }

            while (s[end] != '|') {
                end--
            }

            result[i] = if (end < beg) 0 else cumulativeSum[end] - cumulativeSum[beg]
        }

        return result

//            val actualStart = (beg until end).findLast { s[it] == '|' } ?: beg
//            val actualEnd = (beg..end).find { s[it] == '|' } ?: end
//
//            // Calculate the number of items within the compartment
//            result[i] = if (actualEnd < actualStart) 0 else cumulativeItems[actualEnd] - cumulativeItems[actualStart]
    }

    fun maxSetSize(riceBags: Array<Int>): Int {
        // Convert the array to a set for efficient lookups
        val riceSet = riceBags.toHashSet()
        var maxSize = -1

        // Iterate over each rice bag
        for (bag in riceBags) {
            // Skip if this bag can be a continuation of another sequence
            if (!riceSet.contains(bag * bag)) continue

            // Start a new potential sequence from this bag
            var currentSize = 1
            var currentBag = bag

            // Continue the sequence while the next square exists
            while (riceSet.contains(currentBag * currentBag)) {
                currentBag *= currentBag
                currentSize++
            }

            // Update the maximum size found
            maxSize = maxOf(maxSize, currentSize)
        }

        // Return the maximum set size found, or -1 if no valid set exists
        return if (maxSize >= 2) maxSize else -1
    }

    /*fun findKthMinimumVulnerability2(k: Int, m: Int, vulnerability: Array<Int>): Array<Int> {
        // Write your code here
        // Create a max heap with a custom comparator for reverse order
        val maxHeap = PriorityQueue<Int>(compareByDescending { it })

        // Add the first k elements to the heap
        for (i in 0 until m) {
            maxHeap.add(vulnerability[i])
        }

        val result = mutableListOf<Int>()

        // Iterate over the rest of the elements
        for (i in m until vulnerability.size) {
            // If the current element is smaller than the root of the heap
            if (vulnerability[i] < maxHeap.peek()) {
                // Remove the root and add the current element
                maxHeap.poll()
                maxHeap.add(vulnerability[i])
                result.add(maxHeap.peek())
            }
        }

        // The root of the heap is the k-th smallest element
        return result.toTypedArray()
    }*/


    fun findKthMinimumVulnerability2(k: Int, m: Int, vulnerability: Array<Int>): Array<Int> {
        val result = mutableListOf<Int>()

        val n = vulnerability.size

        // Iterate through the array with a window of size 'm'
        for (i in 0..n - m) {
            // Extract the current window
            val slidingWindow = vulnerability.copyOfRange(i, i + m).sorted()

            // Add the k-th smallest element from the sorted window to the result
            result.add(slidingWindow[k - 1])
        }

        return result.toTypedArray()
    }

    data class IndexedVulnerability(val index: Int, val value: Int)

    fun findKthMinimumVulnerability(k: Int, m: Int, vulnerability: Array<Int>): Array<Int> {
        // Result list to store the k-th minimum vulnerability for each window
        val result = mutableListOf<Int>()
        // Min heap to store vulnerabilities along with their indices for easy removal
        val minHeap = PriorityQueue<IndexedVulnerability>(compareBy { it.index })
        // Max heap to keep track of the largest of the k smallest elements
        val maxHeap = PriorityQueue<IndexedVulnerability>(compareByDescending { it.index })

//        for (i in vulnerability.indices) {
        vulnerability.forEachIndexed { i, v ->
            // Add new element into the min heap
            minHeap.offer(IndexedVulnerability(v, i))

            // Remove elements that are out of the current window's range
            while (minHeap.peek().value <= i - m) {
                minHeap.poll()
            }

            // Maintain only k elements in the max heap, which represents the k smallest elements of the window
            if (maxHeap.size < k) {
                maxHeap.offer(minHeap.poll())
            } else if (minHeap.isNotEmpty() && minHeap.peek().index < maxHeap.peek().index) {
                val toRemove = maxHeap.poll()
                maxHeap.offer(minHeap.poll())
                minHeap.offer(toRemove)
            }

            // Once the window size is reached, add the root of the max heap to the result
            if (i >= m - 1) {
                result.add(maxHeap.peek().index)
            }
        }

        return result.toTypedArray()
    }

}

fun main() {
    val a = Amazon2()

//    var riceBags = arrayOf(3, 9, 4, 2, 16)
//    println(a.maxSetSize(riceBags)) // Output: 3
//    riceBags = arrayOf(625, 4, 2, 5, 25)
//    println(a.maxSetSize(riceBags))
//    riceBags = arrayOf(7, 4, 8, 9)
//    println(a.maxSetSize(riceBags))

    println(a.findKthMinimumVulnerability(2, 3, arrayOf(1, 3, 2, 1)).contentToString())
    println(a.findKthMinimumVulnerability(1, 1, arrayOf(3, 2, 1, 1, 1)).contentToString())


//    println(a.numberOfItems("*|*|*|", arrayOf(1, 1), arrayOf(1, 6)).contentToString())
//    println(a.numberOfItems("|**|*|*", arrayOf(1, 1), arrayOf(5, 6)).contentToString())
//    println(a.numberOfItems("*|*|", arrayOf(1), arrayOf(3)).contentToString())
//    println(a.numberOfItems("*|*|*|", arrayOf(1), arrayOf(6)).contentToString())


//    println("result is " + Arrays.toString(a.maximumBookCopies(arrayOf(6, 6, 2, -6, -2, -6))))

//    println(Arrays.toString(a.maximumBookCopies(arrayOf(1, 2, -1, 2))))
//    println(Arrays.toString(a.maximumBookCopies(arrayOf(1, 2, 2, 1, 1, 0))))
//    println(Arrays.toString(a.maximumBookCopies(arrayOf(1, 1, 2, -2, 1, 0))))

//    println(a.findReviewScore("roductButScrapAfterWash", arrayOf("crap", "odpro")))
//    println(a.findReviewScore("FastDeliveryOkayProduct", arrayOf("eryoka", "yo", "eli")))
}
