package com.vahoss.kotlin_solutions

import com.vahoss.ListNode

class Solutions00160 {
    fun getIntersectionNode(headA: ListNode?, headB: ListNode?): ListNode? {
        var a = headA
        var b = headB

        while (a != b) {
            a?.let { a = it.next } ?: apply { a = headB }
            b?.let { b = it.next } ?: apply { b = headA }
        }

        return a
    }
}

fun main() {
    val sol = Solutions00160()
    var nums = intArrayOf(2, 7, 11, 15)
//    println(Arrays.toString(sol.getIntersectionNode(nums, 9)))
}