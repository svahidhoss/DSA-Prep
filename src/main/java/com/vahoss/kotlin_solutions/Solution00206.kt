package com.vahoss.kotlin_solutions

import com.vahoss.ListNode
import com.vahoss.createListNodeFromArray

class Solution00206 {

    /**
     * Recursive — recurses to the tail, then rewires pointers on the way back.
     * Time: O(n) | Space: O(n) — call stack depth equals list length
     */
    fun reverseListRecursive(head: ListNode?): ListNode? {
        // Stop condition: null or list with 1 element
        head?.next ?: return head

        val newHead = reverseListRecursive(head.next)

        head.next?.next = head
        head.next = null

        return newHead
    }

    /**
     * Tail-recursive — reverses pointers on the way DOWN, accumulator-style.
     * Time: O(n) | Space: O(n) — JVM does not optimize tail calls; stack still grows
     */
    fun reverseListTailRecursive(head: ListNode?): ListNode? {
        if (head?.next == null) return head

        // Make the original head a tail
        val nextNode = head.next
        head.next = null
        return reverseListTailRecursive(head, nextNode)
    }

    private fun reverseListTailRecursive(currentNode: ListNode?, nextNode: ListNode?): ListNode? {
        if (nextNode == null) return currentNode

        val newNext = nextNode.next
        nextNode.next = currentNode
        return reverseListTailRecursive(nextNode, newNext)
    }

    /**
     * Iterative — three-pointer prev/curr/next dance. Canonical interview solution.
     * Time: O(n) | Space: O(1)
     */
    fun reverseListIterative(head: ListNode?): ListNode? {
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

    /**
     * Builds a new reversed list — leaves the original list untouched.
     * Time: O(n) | Space: O(n) — allocates n new nodes
     */
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
}

private fun ListNode?.toIntList(): List<Int> {
    val result = mutableListOf<Int>()
    var node = this
    while (node != null) {
        result.add(node.`val`)
        node = node.next
    }
    return result
}

fun main() {
    val sol = Solution00206()

    val cases = listOf(
        intArrayOf(1, 2, 3, 4, 5) to listOf(5, 4, 3, 2, 1),
        intArrayOf(1, 2) to listOf(2, 1),
        intArrayOf(1) to listOf(1),
        intArrayOf() to emptyList(),
    )

    for ((input, expected) in cases) {
        val r1 = sol.reverseListRecursive(createListNodeFromArray(input)).toIntList()
        val r2 = sol.reverseListTailRecursive(createListNodeFromArray(input)).toIntList()
        val r3 = sol.reverseListIterative(createListNodeFromArray(input)).toIntList()
        val r4 = sol.reverseListPreserving(createListNodeFromArray(input)).toIntList()
        val tag = if (r1 == expected && r2 == expected && r3 == expected && r4 == expected) "PASS" else "FAIL"
        println("[$tag] input=${input.toList()} expected=$expected recursive=$r1 tailRec=$r2 iter=$r3 preserve=$r4")
    }
}