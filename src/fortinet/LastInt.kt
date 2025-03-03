package fortinet

import java.util.*

/**
 * Description
 * ===
 * Given a pool of positive integers, we take the two largest integers from the pool.
 * Their difference (if any, as the two numbers can be the same) is
 * placed back into the pool.
 * We repeat this process with the two currently the largest integers
 * from the pool until there is only one integer left in the pool
 * (or when the pool is empty).
 *
 * What is the last remaining integer in the pool. Return 0 if there are no integers in the pool.
 *
 * Examples
 * ===
 * Input: pool = [2, 4, 5]
 * Output: 1
 *
 *   5
 *   4
 *  [2] add -> 1
 * 	[2,1]
 * 	add 1 ->[1]
 * Input: pool = [1,2,3,6,7,7]
 * Output: 0
 *
 *   O(nlogn)
 *   logn
 *   7 - 7 = 0
 *   0,1,2,3,6
 *   6-3=3
 *   0,1,2,3,3
 *   3-3=0
 *   0,0,1,2
 *   2-1= 1
 *   0 0 1 1
 *   0
 *   0 0 0
 */
fun lastInt(pool: List<Int>): Int {
    // Create a max heap from the pool of integers
    val maxHeap = PriorityQueue<Int>(compareByDescending { it })
    maxHeap.addAll(pool)

    while (maxHeap.size > 1) {
        // Remove the two largest integers from the heap
        val first = maxHeap.poll()
        val second = maxHeap.poll()

        // Calculate their difference
        val diff = first - second

        // If the difference is not zero, add it back to the heap
        if (diff > 0) maxHeap.add(diff)
    }

    // Return the last remaining integer, or 0 if the heap is empty
    return maxHeap.poll() ?: 0
}

fun lastInt1(pool: List<Int>): Int {
    val maxHeap = PriorityQueue<Int> { a, b -> b - a }.apply { addAll(pool) }
    while (maxHeap.size > 1) {
        val diff = maxHeap.poll() - maxHeap.poll()
        if (diff > 0) maxHeap.add(diff)
    }

    return maxHeap.peek() ?: 0
}

fun main() {
    val pool = listOf(5, 7, 8, 9, 3, 2)
    // println(lastInt2(pool)) // Example output: 1
    println(lastInt(pool)) // Example output: 1
    println(lastInt1(pool)) // Example output: 1
}

/**
 * Yes, the problem can be approached by initially sorting the pool of integers,
 * repeatedly taking the last two elements (which are the largest due to sorting),
 * calculating their difference, and then inserting this difference back into the
 * pool in its correct position using binary search. However, this approach has some
 * nuances in terms of time complexity, especially concerning the insertion step.
 *
 * Here's a conceptual overview of how this approach would work:
 * 1. Sort the pool of integers in non-decreasing order.
 * 2. Repeat the following until there is only one integer left in the pool (or none):
 * 2.1. Take the last two elements from the pool (these are the largest because the pool is sorted).
 * 2.2. Calculate their difference.
 * 2.3. If the difference is not zero, use binary search to find the correct position for this difference in the pool and insert it.
 * 3. Return the last remaining integer in the pool, or 0 if the pool is empty.
 *
 * Implementation Considerations
 * Implementing this approach in Kotlin would involve using a mutable list for
 * the pool to allow for easy removal and insertion of elements. Kotlin's standard
 * library does not directly support binary search with insertion, so you would need
 * to implement this part manually or adjust the list after finding the insertion point.
 *
 * Time Complexity Analysis
 * - Sorting: The initial sort of the pool has a time complexity of O(n log n),
 * where n is the number of elements in the pool.
 * - Taking the last two elements: This operation is O(1).
 * - Calculating the difference: Also O(1).
 * - Inserting using binary search: While finding the insertion point using binary search is O(log n),
 * inserting an element into an array (or ArrayList) at an arbitrary position is O(n) because it may
 * require shifting all subsequent elements. Therefore, each iteration of insertion has a time
 * complexity of O(n).
 *
 * Given that you might need to perform the insertion operation up to n/2 times in the worst case,
 * the overall time complexity could approach O(n^2) in scenarios where many insertions are necessary.
 * This makes the approach less efficient than using a priority queue or a similar data structure
 * optimized for such operations.
 *
 * Conclusion
 * While solving the problem using sorting and binary search for insertion is
 * conceptually straightforward and an interesting exercise, it is less efficient
 * than using a priority queue due to the overhead of inserting elements into a
 * sorted list. The priority queue approach remains more suitable for this problem,
 * offering better performance, especially for larger pools of integers.
 */

fun lastInt2(pool: List<Int>): Int {
    /* implement your solution here */
    /********************************/
    var newPool = pool.sorted()

    while (newPool.size > 1) {
        val n = newPool.size
        val diff = newPool[n - 1] - newPool[n - 2]
        val temp = newPool.take(n - 2).toMutableList()
        newPool = temp
        // insert the diff TODO: figure out the binary search
        val indexToInsert = newPool.binarySearch(diff)
        newPool.add(indexToInsert, diff)
    }


    // return num;
    return newPool.first()
}