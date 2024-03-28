class Solution00328 {
    fun oddEvenList(head: ListNode?): ListNode? {
        if (head?.next == null) return head

        // Reach the end of the linked list
        var origEnd = head
        while (origEnd?.next != null) {
            origEnd = origEnd.next
        }

        var p = head
        var q: ListNode = origEnd!!
        println("p is ${p!!.`val`}")
        println("q is ${q.`val`}")
        println(head)
        while (!(p == origEnd || p == origEnd.next)) {
            val pNext = p!!.next
            // Move the even node to the end
            q.next = pNext
            p.next = pNext!!.next
            pNext.next = null
            q = pNext

            p = p.next
            println("p is ${p!!.`val`}")
            println("q is ${q.`val`}")
            println("origend is ${origEnd.`val`}")
            println("origend.next is ${origEnd.next?.`val`}")
            println(head)
            println()
        }

        return head
    }

    fun oddEvenList2(head: ListNode?): ListNode? {
        if (head?.next == null) return head

        var h = head
        val evenListHead = head.next
        var evenList = evenListHead

        while (evenList?.next != null) {
            h?.next = evenList.next
            h = h?.next
            evenList.next = h?.next
            evenList = evenList.next
        }

        h?.next = evenListHead

        return head
    }

//    fun oddEvenListRecursive(head: ListNode?): ListNode? {
//        if (head?.next == null) return head
//
//    }

}

fun main() {
    val sol = Solution00328()
    var nums = intArrayOf(1, 2, 3, 4, 5)
    println(sol.oddEvenList2(createListNodeFromArray(nums)))
    nums = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8)
    println(sol.oddEvenList2(createListNodeFromArray(nums)))
}