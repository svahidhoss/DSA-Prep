package main.java

/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 */
/*class ListNode {
    var `val` = 0
    var next: ListNode? = null

    internal constructor()

    internal constructor(`val`: Int) {
        this.`val` = `val`
    }

    internal constructor(`val`: Int, next: ListNode?) {
        this.`val` = `val`
        this.next = next
    }

    override fun toString(): String {
        var node: ListNode? = this
        val sb = StringBuilder()
        while (node != null) {
            sb.append(node.`val`.toString() + " -> ")
            node = node.next
        }
        sb.append("\n")
        return sb.toString()
    }
}*/

/**
 * Here's the improved version of the above code:
 * Use property syntax: Kotlin allows for more concise property definitions.
 * You can define properties with default values directly in the primary constructor.
 *
 * Remove redundant internal constructors: Kotlin provides default values and
 * named arguments, which can reduce the need for multiple overloaded constructors.
 *
 * Improve the toString method: The toString method can be simplified using
 * Kotlin's built-in string templates and collection joining operations.
 */
data class ListNode(var `val`: Int = 0, var next: ListNode? = null) {

    override fun toString(): String {
        var node: ListNode? = this
        val values = mutableListOf<Int>()
        while (node != null) {
            values.add(node.`val`)
            node = node.next
        }
        return values.joinToString(" -> ") + "\n"
    }
}


fun createListNodeFromArray(values: IntArray): ListNode? {
    if (values.isEmpty()) return null
    var p: ListNode? = ListNode()
    // For keeping access to the head
    val head = ListNode(0, p)
    for (i in values.indices) {
        if (i == 0) {
            p!!.`val` = values[i]
        } else {
            val temp = ListNode(values[i])
            p!!.next = temp
            p = p.next
        }
    }
    return head.next
}


fun println(listNode: ListNode?) {
    var l = listNode
    while (l != null) {
        print(l.`val`.toString() + " -> ")
        l = l.next
    }
    println()
}

fun createLinkedList(values: List<Int>): ListNode? {
    if (values.isEmpty()) return null

    val head = ListNode(values[0])
    var current = head

    for (i in 1 until values.size) {
        current.next = ListNode(values[i])
        current = current.next!!
    }

    return head
}
