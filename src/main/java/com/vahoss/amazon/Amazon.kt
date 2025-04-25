package com.vahoss.amazon

import java.util.*

class Amazon {
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
}

fun main() {
    val a = Amazon()
    println("result is " + Arrays.toString(a.maximumBookCopies(arrayOf(6, 6, 2, -6, -2, -6))))
//    println(Arrays.toString(a.maximumBookCopies(arrayOf(1, 2, -1, 2))))
//    println(Arrays.toString(a.maximumBookCopies(arrayOf(1, 2, 2, 1, 1, 0))))
//    println(Arrays.toString(a.maximumBookCopies(arrayOf(1, 1, 2, -2, 1, 0))))

//    println(a.findReviewScore("roductButScrapAfterWash", arrayOf("crap", "odpro")))
//    println(a.findReviewScore("FastDeliveryOkayProduct", arrayOf("eryoka", "yo", "eli")))
}