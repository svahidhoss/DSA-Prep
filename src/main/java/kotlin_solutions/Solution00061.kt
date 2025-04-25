package main.java.kotlin_solutions

import main.java.ListNode
import main.java.createListNodeFromArray
import main.java.println

class Solution0061 {
    fun rotateRight(head: ListNode?, k: Int): ListNode? {
        if (head?.next == null) return head

        var endNode = head
        var firstNode = head

        var nodeCount = 0
        while (endNode?.next != null) {
            endNode = endNode.next
            nodeCount++
        }

        // The number of rotations should be reversed in relation to the original k
        val correctK = ++nodeCount - (k % nodeCount)
        // make one big loop
        endNode?.next = firstNode
        // traverse the list enough
        for (i in 1..correctK) {
            firstNode = firstNode?.next
            endNode = endNode?.next
        }
        // un-loop
        endNode?.next = null

        return firstNode
    }
}

fun main() {
    var values = intArrayOf(1, 2, 3, 4, 5)
    var testListNode: ListNode? = createListNodeFromArray(values)
    val sol = Solution0061()
    var result = sol.rotateRight(testListNode, 2)
    println(result)

    values = intArrayOf(0, 1, 2)
    testListNode = createListNodeFromArray(values)
    result = sol.rotateRight(testListNode, 4)
    println(result)

    values = intArrayOf(0)
    testListNode = createListNodeFromArray(values)
    result = sol.rotateRight(testListNode, 7)
    println(result)

    result = sol.rotateRight(null, 7)
    println(result)

    values = intArrayOf(1, 2)
    testListNode = createListNodeFromArray(values)
    result = sol.rotateRight(testListNode, 0)
    println(result)

    result = sol.rotateRight(testListNode, 1)
    println(result)
}