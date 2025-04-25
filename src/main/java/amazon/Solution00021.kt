package main.java.amazon

import main.java.ListNode
import main.java.createListNodeFromArray

class Solution00021 {

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
    fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
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
}