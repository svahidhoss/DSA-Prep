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
     */
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
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

        val temp = h?.next
        h?.next = temp?.next

        return head
    }
}

fun main() {
    val sol = Solution00019()
    // Example 1
    val list1 = createLinkedList(listOf(1, 2, 3, 4, 5))
    println("Example 1: (expected output: [1,2,3,5])")
    println(sol.removeNthFromEnd(list1, 2))

    // Example 2
    val list2 = createLinkedList(listOf(1))
    println("Example 2: (expected output: [])")
    println(sol.removeNthFromEnd(list2, 1))

    // Example 3
    val list3 = createLinkedList(listOf(1, 2))
    println("Example 3: (expected output: [1])")
    println(sol.removeNthFromEnd(list3, 1))

    // Example 4
    val list4 = createLinkedList(listOf(1, 2))
    println("Example 4: (expected output: [2])")
    println(sol.removeNthFromEnd(list4, 2))
}
