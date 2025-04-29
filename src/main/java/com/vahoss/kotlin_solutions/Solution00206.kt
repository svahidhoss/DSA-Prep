package com.vahoss.kotlin_solutions

import com.vahoss.ListNode
import com.vahoss.createListNodeFromArray
import com.vahoss.println

class Solution00206 {

    /**
     * Another iterative solution.
     */
    fun reverseList(head: ListNode?): ListNode? {
        // for null or cases where we have only one element
        if (head?.next == null) return head

        var current = head
        var prev: ListNode? = null

        while (current != null) {
            val newHead = current.next
            current.next = prev
            prev = current
            current = newHead
        }

        return prev
    }

    fun reverseListRecursive(head: ListNode?): ListNode? {
        if (head?.next == null) return head

        // Make the orig head a tail
        val nextNode = head.next
        head.next = null
        return reverseListRecursive(head, nextNode)
    }

    private fun reverseListRecursive(currentNode: ListNode?, nextNode: ListNode?): ListNode? {
        if (nextNode == null) return currentNode

        val newNext = nextNode.next
        nextNode.next = currentNode
        return reverseListRecursive(nextNode, newNext)
    }

    /**
     * Time complexity is O(n) where n is the number of nodes in the list,
     * and the space complexity is O(1) as it only uses a constant amount of
     * extra space regardless of the input size
     */
    fun reverseListIterative(head: ListNode?): ListNode? {
        // Base case: empty list or single node
        if (head?.next == null) return head

        var currentNode = head
        var nextNode = head.next

        while (nextNode != null) {
            // Store the next node's 'next' before we change it
            val tempNext = nextNode.next

            // Reverse the link
            nextNode.next = currentNode

            // Move the pointers one step forward
            currentNode = nextNode
            nextNode = tempNext
        }

        // Prevent a loop by setting original head's next to null
        head.next = null

        // Return the new head (previously the last node)
        return currentNode
    }

    fun reverseListPreserving(head: ListNode?): ListNode? {
        // Base case: empty list or single node
        if (head?.next == null) return head
        var currentNode = head

        var node: ListNode? = null
        while (currentNode != null) {
            // Create a new node based on current node's value
            val newNode = ListNode(currentNode.`val`)

            // Link the new node to the current reverse list
            newNode.next = node
            node = newNode

            // Move to the next node in the original list
            currentNode = currentNode.next
        }

        return node
    }

    fun reverseList2(head: ListNode?): ListNode? {
        var counter = 0
        var newHead = head
        while (newHead != null) {
            counter++
            newHead = newHead.next
        }
        newHead = head
        val nodesMap = arrayOfNulls<ListNode>(counter)
        for (i in 0 until counter) {
            nodesMap[i] = newHead
            newHead = newHead!!.next
        }
        newHead = ListNode(0)
        val result = ListNode(0, newHead)
        for (i in counter - 1 downTo 0) {
            newHead!!.next = nodesMap[i]
            newHead = newHead.next
        }
        newHead!!.next = null
        return result.next!!.next
    }
}

fun main() {
    val s = Solution00206()
    println(s.reverseList(createListNodeFromArray(intArrayOf(1, 2, 3, 4, 5))))
    println(s.reverseListPreserving(createListNodeFromArray(intArrayOf(1, 2, 3, 4, 5))))
    println(s.reverseList(createListNodeFromArray(intArrayOf(1, 2))))
    println(s.reverseListPreserving(createListNodeFromArray(intArrayOf(1, 2))))
    println(s.reverseList(createListNodeFromArray(intArrayOf())))
    println(s.reverseListPreserving(createListNodeFromArray(intArrayOf())))
}