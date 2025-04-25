package java.kotlin_solutions

import java.ListNode
import java.createLinkedList
import java.println

/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
class Solution00019 {
    /**
    head = [1,2,3,4,5], n = 2
    len = 5
    i = 5 - 2 = 3
    1,2,3,5

    o(n)
    o(1)

    q
    1, 2, 3, 4, 5
    p

     */
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        val dummy = ListNode(0)
        dummy.next = head
        var ahead = dummy
        var behind = dummy

        // Move 'ahead' pointer n steps forward
        repeat(n) {
            ahead = ahead.next ?: return head
        }

        // Move both pointers until 'ahead' reaches the last node
        while (ahead.next != null) {
            ahead = ahead.next!!
            behind = behind.next!!
        }

        // Remove the nth node from the end
        behind.next = behind.next?.next

        return dummy.next
    }

    fun removeNthFromEndAttempt(head: ListNode?, n: Int): ListNode? {
        var p = head
        var q = head
        for (i in 1..n + 1) {
            // if null n == size of the list node
            p = p?.next ?: return head?.next
        }
        while (p != null) {
            p = p.next
            q = q?.next
        }
        q?.next = q?.next?.next

        return head
    }

    fun removeNthFromEnd2(head: ListNode?, n: Int): ListNode? {
        var h = head
        var len = 0
        while (h != null) {
            len++
            h = h.next
        }

        if (n == len) return head?.next
        var i = len - n - 1
        h = head
        while (i > 0) {
            h = h?.next
            i--
        }

        h?.next = h?.next?.next

        return head
    }

    fun removeNthFromEnd2Improved(head: ListNode?, n: Int): ListNode? {
        var h = head
        var len = 0

        // Count the length of the list
        while (h != null) {
            len++
            h = h.next
        }

        // If n equals the length, remove the first node
        if (n == len) return head?.next

        // Find the node before the one to be removed
        val targetIndex = len - n - 1
        h = head
        repeat(targetIndex) {
            h = h?.next
        }

        // Remove the nth node from the end
        h?.next = h?.next?.next

        return head
    }
}

fun main() {
    val sol = Solution00019()
    // Example 1
    val list1 = createLinkedList(listOf(1, 2, 3, 4, 5))
    println("Example 1: $list1 (expected output: [1,2,3,5])")
    println(sol.removeNthFromEnd(list1, 2))

    // Example 2
    val list2 = createLinkedList(listOf(1))
    println("Example 2: $list2 (expected output: [])")
    println(sol.removeNthFromEnd(list2, 1))

    // Example 3
    val list3 = createLinkedList(listOf(1, 2))
    println("Example 3: $list3 (expected output: [1])")
    println(sol.removeNthFromEnd(list3, 1))

    // Example 4
    val list4 = createLinkedList(listOf(1, 2))
    println("Example 4: $list4 (expected output: [2])")
    println(sol.removeNthFromEnd(list4, 2))
}
