/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
class Solution00023 {
    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        val result = ListNode(-1)
        // TODo
        // create a min heap


        return result.next
    }
}

fun main(args: Array<String>) {
    val sol = Solution00023()
    val lists = arrayOf(
        createLinkedList(listOf(1, 4, 5)),
        createLinkedList(listOf(1, 3, 4)),
        createLinkedList(listOf(2, 6))
    )
    println(sol.mergeKLists(lists))
}
