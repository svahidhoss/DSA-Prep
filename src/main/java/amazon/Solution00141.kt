package main.java.amazon

import main.java.ListNode
import main.java.createListNodeFromArray

/*
Map<Int, ListNode>
1 -> N(1)
1 2 3 4 5
  p     q
1 5 2 4 3
 */

class Solution00141 {
    fun reorderList(head: ListNode?) {
        // Step 1: find the middle node
        var mid = head
        var p = head
        // fast slow two pointer approach
        while (p?.next?.next != null) {
            mid = mid?.next
            p = p.next?.next
        }

        // Step 2: Reverse the second half of the list
        var prev: ListNode? = null
        var current = mid?.next
        while (current != null) {
            val nextTemp = current.next
            current.next = prev
            prev = current
            current = nextTemp
        }
        // Split the list into two parts
        mid?.next = null

        // Step 3: Merge the two halves
        var first = head
        var second = prev
        while (second != null) {
            val temp1 = first?.next
            val temp2 = second.next

            first?.next = second
            second.next = temp1

            first = temp1
            second = temp2
        }
    }

    fun reorderList2(head: ListNode?) {
        val map = mutableMapOf<Int, ListNode>()
        var q = 0
        var currentNode = head

        // Store nodes in the map with their index
        while (currentNode != null) {
            map[q++] = currentNode
            currentNode = currentNode.next
        }

        // Reconstruct the list node
        var p = 0
        q-- // q = n-1
        while (p < q) {
            map[p]?.next = map[q]
            p++

            // necessary to avoid connecting the last node to the first node in case of even number of nodes
            if (p == q) break

            map[q]?.next = map[p]
            q--
        }

        // Close the list at the end
        map[p]?.next = null
    }

    fun reorderList3(head: ListNode?) {
        var h = head
        val map = mutableMapOf<Int, ListNode>()
        var q = 0
        while (h != null) {
            map[q] = h
            h = h.next
            // nullify the next element
            map[q]?.next = null
            q++
        }
        // since q is n
        q--
        var p = 0
        // Recreate the list node
        while (p < q) {
            map[p]?.next = map[q]
            p++
            // For cases where the n is even
            if (p == q) break
            map[q]?.next = map[p]
            q--
        }
    }
}

fun main() {
    val s = Solution00141()
    var listNode = createListNodeFromArray(intArrayOf(1, 2, 3, 4))
    s.reorderList(listNode)
    listNode = createListNodeFromArray(intArrayOf(1, 2, 3, 4, 5))
    s.reorderList(listNode)
}