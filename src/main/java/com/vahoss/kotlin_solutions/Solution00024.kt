package com.vahoss.kotlin_solutions

import com.vahoss.ListNode

class Solution00024 {
    fun swapPairs(head: ListNode?): ListNode? {
        if (head?.next == null) return head

        val newHead = head.next
        head.next = swapPairs(newHead?.next)

        newHead?.next = head

        return newHead
    }

    fun swapPairsIterative(head: ListNode?): ListNode? {
        if (head?.next == null) return head

        // To keep the original head for final result
        val result = head.next

        var preHead: ListNode? = null
        var h = head
        while (h?.next != null) {
            val newHead = h.next
            h.next = newHead?.next
            newHead?.next = h
            if (preHead != null) preHead.next = newHead
            // move forward for the loop
            preHead = h
            h = h.next
        }

        return result
    }

    fun swapPairs2(head: ListNode?): ListNode? {
        val result = if (head?.next != null) head.next else head
        var h = head
        var preElement: ListNode? = null
        while (true) {
            h?.next?.let {
                preElement?.next = it
                h?.next = it.next
                it.next = h
                // For the next iteration
                preElement = h
                h = h?.next
            } ?: return result
        }
    }


    fun swapPairs3(head: ListNode?): ListNode? {
        if (head?.next == null) return head

        val result = head.next

        swap(head)

        return result
    }

    private fun swap(h: ListNode?): ListNode? {
        if (h?.next == null) return h
        val n = h.next
        val new = h.next?.next

        n?.next = h
        h.next = swap(new)
        return n
    }
}
