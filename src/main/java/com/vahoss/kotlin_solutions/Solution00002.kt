package com.vahoss.kotlin_solutions

import com.vahoss.ListNode
import com.vahoss.createListNodeFromArray
import com.vahoss.println

class Solution00002 {

    /**
     * This won't work properly all the time.
     */
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        if (l1 == null && l2 == null) return null

        var num1 = 0L
        var l1New = l1
        var count = 0.0
        while (l1New != null) {
            num1 += l1New.`val` * Math.pow(10.0, count++).toInt()
            l1New = l1New.next
        }

        var num2 = 0L
        var l2New = l2
        count = 0.0
        while (l2New != null) {
            num2 += l2New.`val` * Math.pow(10.0, count++).toInt()
            l2New = l2New.next
        }

        var result: Long = num1 + num2

        val resultListNode = ListNode(0)
        if (result == 0L) return resultListNode
        var node = resultListNode

        while (result > 0) {
            node.next = ListNode(0)
            node = node.next!!
            node.`val` = (result % 10).toInt()

            result /= 10
        }

        return resultListNode.next
    }


    fun addTwoNumbers2(l1: ListNode?, l2: ListNode?): ListNode? {
        val result = ListNode(0)
        var r = result

        var ln1 = l1
        var ln2 = l2
        var carrey = 0
        while (ln1 != null || ln2 != null || carrey == 1) {
            r.next = ListNode(0)
            r = r.next!!

            r.`val` = (ln1?.`val` ?: 0) + (ln2?.`val` ?: 0) + carrey

            carrey = 0
            if (r.`val` >= 10) {
                r.`val` -= 10
                carrey = 1
            }

            ln1 = ln1?.next
            ln2 = ln2?.next
        }

        return result.next
    }
}

fun main() {
    val s = Solution00002()
    var result = s.addTwoNumbers(
        createListNodeFromArray(intArrayOf(2, 4, 3)),
        createListNodeFromArray(intArrayOf(5, 6, 4))
    )
    println(result)

    result = s.addTwoNumbers(
        createListNodeFromArray(intArrayOf(9, 9, 9, 9, 9, 9, 9)),
        createListNodeFromArray(intArrayOf(9, 9, 9, 9))
    )
    println(result)

    result = s.addTwoNumbers(
        createListNodeFromArray(intArrayOf(0)),
        createListNodeFromArray(intArrayOf(0))
    )
    println(result)

    result = s.addTwoNumbers(
        createListNodeFromArray(intArrayOf(9)),
        createListNodeFromArray(intArrayOf(1, 9, 9, 9, 9, 9, 9, 9, 9, 9))
    )
    println(result)
}