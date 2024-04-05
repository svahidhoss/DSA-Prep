class Solution00206 {
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

    fun reverseListIterative(head: ListNode?): ListNode? {
        if (head?.next == null) return head
        var pre = head
        var h = head.next

        while (h != null) {
            val newH = h.next
            h.next = pre
            pre = h
            h = newH
        }

        // To prevent the cycle in the new tail
        head.next = null
        return pre
    }

    fun reverseList(head: ListNode?): ListNode? {
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
    s.reverseListIterative(createListNodeFromArray(intArrayOf(1,2)))
}