package com.vahoss.amazon

import com.vahoss.ListNode
import com.vahoss.createListNodeFromArray

class Solution00021 {

    /**
     * the trade-off: The recursive solution is O(n + m) time but also
     * O(n + m) space due to the call stack. If asked about optimizing space,
     * the iterative approach with a dummy head node brings it to O(1) space
     */
    fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
        // if one is null return the other
        if (list1 == null) return list2
        if (list2 == null) return list1

        if (list1.`val` <= list2.`val`) {
            val result = mergeTwoLists(list1.next, list2)
            list1.next = result
            return list1
        } else {
            val result = mergeTwoLists(list1, list2.next)
            list2.next = result
            return list2
        }
    }

    fun mergeTwoListsRecursive(list1: ListNode?, list2: ListNode?): ListNode? {
        if (list1 == null) return list2
        if (list2 == null) return list1

        if (list1.`val` - list2.`val` <= 0) {
            list1.next = mergeTwoListsRecursive(list1.next, list2)
            return list1
        } else {
            list2.next = mergeTwoListsRecursive(list2.next, list1)
            return list2
        }
    }

    /**
     * Improved iterative version.
     */
    fun mergeTwoLists1(list1: ListNode?, list2: ListNode?): ListNode? {
        val result = ListNode(-1)
        var current = result

        var p = list1
        var q = list2
        while (p != null && q != null) {
            if (p.`val` <= q.`val`) {
                current.next = p
                p = p.next
            } else {
                current.next = q
                q = q.next
            }
            current = current.next!!
        }
        current.next = p ?: q

        return result.next
    }

    fun mergeTwoLists2(list1: ListNode?, list2: ListNode?): ListNode? {
        val result = ListNode(-1)
        var h = result

        var p = list1
        var q = list2
        while (p != null || q != null) {
            if ((p?.`val` ?: Int.MAX_VALUE) <= (q?.`val` ?: Int.MAX_VALUE)) {
                h.next = ListNode(p!!.`val`)
                p = p.next
            } else {
                h.next = ListNode(q!!.`val`)
                q = q.next
            }

            h = h.next!!
        }

        return result.next
    }
}

fun main() {
    val s = Solution00021()
    println(
        s.mergeTwoListsRecursive(
            createListNodeFromArray(intArrayOf(1, 2, 4)),
            createListNodeFromArray(intArrayOf(1, 3, 4))
        )
    )
    println(
        s.mergeTwoListsRecursive(
            createListNodeFromArray(intArrayOf()),
            createListNodeFromArray(intArrayOf())
        )
    )
    println(
        s.mergeTwoListsRecursive(
            createListNodeFromArray(intArrayOf()),
            createListNodeFromArray(intArrayOf(0))
        )
    )
    println(
        s.mergeTwoListsRecursive(
            createListNodeFromArray(intArrayOf(0)),
            createListNodeFromArray(intArrayOf())
        )
    )
}