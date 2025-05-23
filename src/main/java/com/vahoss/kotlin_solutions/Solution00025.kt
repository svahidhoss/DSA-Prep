package com.vahoss.kotlin_solutions

import com.vahoss.ListNode
import com.vahoss.createLinkedList
import com.vahoss.println

/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
class Solution00025 {

    fun reverseKGroup(head: ListNode?, k: Int): ListNode? {
        val dummy = ListNode(0)
        dummy.next = head

        var groupPrev: ListNode? = dummy

        while (true) {
            // we're done if null
            val kthNode = getKthNode(groupPrev, k) ?: break
            val groupNext = kthNode.next

            // Reverse group
            var prev = groupNext
            var curr = groupPrev?.next

            while (curr != groupNext) {
                val tmp = curr?.next
                curr?.next = prev
                prev = curr
                curr = tmp
            }

            // Reconnect reversed group
            val tmp = groupPrev?.next
            groupPrev?.next = kthNode
            groupPrev = tmp
        }

        return dummy.next
    }

    private fun getKthNode(start: ListNode?, k: Int): ListNode? {
        var curr = start
        for (i in 0 until k) {
            curr = curr?.next ?: return null
        }
        return curr
    }
}

fun main() {
    val sol = Solution00025()
    val lists = arrayOf(
        createLinkedList(listOf(1, 2, 3, 4, 5)),
        createLinkedList(listOf(1, 3, 4)),
        createLinkedList(listOf(2, 6))
    )
    for (node in lists) {
        println(sol.reverseKGroup(node, 2))
    }
}
