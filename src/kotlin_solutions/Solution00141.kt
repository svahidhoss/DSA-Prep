package kotlin_solutions

import ListNode
import createListNodeFromArray

class Solution00141 {
    fun hasCycle(head: ListNode?): Boolean {
        val visitedNodeSet = mutableSetOf<ListNode>()
        var h = head

        while (h != null && !visitedNodeSet.contains(h)) {
            visitedNodeSet.add(h)
            h = h.next
        }

        h?.let { return true } ?: return false
    }

    fun hasCycle2(head: ListNode?): Boolean {
        var h = head

        while (h != null && h.`val` != Int.MAX_VALUE) {
            h.`val` = Int.MAX_VALUE
            h = h.next
        }

        h?.let { return true } ?: return false
    }

    fun detectCycle(head: ListNode?): ListNode? {
        if (head?.next == null) return null

        var p = head.next
        var q = head.next?.next

        while (q?.next != null && p != q) {
            p = p?.next
            q = q.next?.next
        }
        q ?: return null

        q = head
        while (p != q) {
            p = p?.next
            q = q?.next
        }

        return p
    }
}

fun main() {
    val sample = createListNodeFromArray(intArrayOf(3, 2, 0, -4))
    val secondNode = sample?.next
    sample?.next?.next?.next?.next = secondNode

    val sol = Solution00141()
    val result = sol.detectCycle(sample)
    print(result?.`val`)
}