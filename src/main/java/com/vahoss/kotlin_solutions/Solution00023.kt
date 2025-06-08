package com.vahoss.kotlin_solutions

import com.vahoss.ListNode
import com.vahoss.createLinkedList
import com.vahoss.println
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
     * main loop O(N log k)
     * result list: * O(N)
     */
    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        val dummy = ListNode(-1)

        // create a min heap based on the int value of list node
        val minHeap = PriorityQueue<ListNode>(compareBy { it.`val` })
        // val minHeap = PriorityQueue<ListNode> { a, b -> a.`val` - b.`val` }

        // Add the head of each non-null list to the heap
        lists.filterNotNull().forEach {
            minHeap.offer(it)
        }

        var currNode = dummy

        while (minHeap.isNotEmpty()) {
            val newNode = minHeap.poll()
            currNode.next = newNode
            currNode = newNode

            newNode.next?.let {
                minHeap.offer(it)
            }
        }

        return dummy.next
    }

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
    fun mergeKLists2(lists: Array<ListNode?>): ListNode? {
        val result = ListNode(-1)

        // create a min heap
        val minHeap = PriorityQueue<Int>()
        lists.forEach { listNode ->
            var node = listNode
            while (node != null) {
                minHeap.offer(node?.`val`)
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

    fun mergeKLists3(lists: Array<ListNode?>): ListNode? {
        val minHeap = PriorityQueue<Pair<Int, Int>>(lists.size) { a, b -> a.first - b.first }

        lists.forEachIndexed { i, listNode ->
            if (listNode != null) {
                minHeap.add(Pair(listNode.`val`, i))
            }
        }

        val result = ListNode(-1)
        var node = result
        while (minHeap.isNotEmpty()) {
            val (minValue, minListIndex) = minHeap.poll()
            val newNode = ListNode(minValue)
            node.next = newNode
            node = node.next!!

            // move the element one step forward
            lists[minListIndex] = lists[minListIndex]?.next
            lists[minListIndex]?.let {
                minHeap.add(it.`val` to minListIndex)
            }
        }

        return result.next
    }
}

fun main() {
    val sol = Solution00023()
    val lists = arrayOf(
        createLinkedList(listOf(1, 4, 5)),
        createLinkedList(listOf(1, 3, 4)),
        createLinkedList(listOf(2, 6))
    )
    println(sol.mergeKLists(lists))
}
