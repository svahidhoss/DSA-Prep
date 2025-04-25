package com.vahoss.kotlin_solutions

import com.vahoss.ListNode
import com.vahoss.createListNodeFromArray

class Solution0237 {
    fun deleteNode(node: ListNode?) {
        var n = node!!

        n.`val` = n.next!!.`val`
        n = n.next!!
    }
}

fun main() {
    val sol = Solution0237()
    val l = createListNodeFromArray(intArrayOf(4, 5, 1, 9))

    sol.deleteNode(l?.next)
}
