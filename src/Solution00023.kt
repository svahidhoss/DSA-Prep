import java.util.*

/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
class Solution00023 {
    /**
     * Time Complexity(where N is the total number of nodes across all lists):
     *
     * Adding all elements to the heap would be O(N log N).
     * Removing all elements from the heap would also be O(N log N).
     * So, the overall time complexity would be O(N log N).
     *
     * Space Complexity:
     * O(N) for the heap, where N is the total number of nodes.
     *
     */
    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        val result = ListNode(-1)

        // create a min heap
        val minHeap = PriorityQueue<Int>()
        lists.forEach { listNode ->
            var node = listNode
            while (node != null) {
                minHeap.add(node?.`val`)
                node = node?.next
            }
        }

        var node = result
        while (minHeap.isNotEmpty()) {
            val newNode = ListNode(minHeap.poll())
            node.next = newNode
            node = newNode
        }

        return result.next
    }
}

fun main(args: Array<String>) {
    val sol = Solution00023()
    val lists = arrayOf(
        createLinkedList(listOf(1, 4, 5)),
        createLinkedList(listOf(1, 3, 4)),
        createLinkedList(listOf(2, 6))
    )
    println(sol.mergeKLists(lists))
}
