package kotlin_solutions

import java.util.*
import kotlin.collections.ArrayDeque

class MaxStack {
    private val stack = ArrayDeque<Element>()
    private val maxHeap = PriorityQueue<Element>(compareBy({ -it.value }, { -it.id }))
    private val deletedIdSet = mutableSetOf<Int>()
    private var id = 0

    fun push(x: Int) {
        val newElement = Element(x, id++)
        stack.add(newElement)
        maxHeap.add(newElement)
    }

    fun pop(): Int {
        // Make sure that the last stack element has already not been marked for deletion
        extractStack()

        // O(n) operation - Skipping this
//        maxHeap.remove(stack.last())

        println("pop is called with ${stack.last()}")
        deletedIdSet.add(stack.last().id)
        return stack.removeLast().value
    }

    fun top(): Int {
        // Make sure that the last stack element has already not been marked for deletion
        extractStack()
        println("Top is called with ${stack.last()}")
        return stack.last().value
    }

    fun peekMax(): Int {
        // Make sure that the top max heap element has not been marked for deletion
        extractMaxHeap()
        println("Peak max is called with ${maxHeap.peek()}")
        return maxHeap.peek().value
    }

    fun popMax(): Int {
        // Make sure that the top max heap element has not been marked for deletion
        extractMaxHeap()

        // val maxElement = maxHeap.peek()
        // O(n) operation - skip this
        /*for (i in stack.size - 1 downTo 0) {
            if (stack[i] == maxElement) {
                stack.removeAt(i)
                break
            }
        }*/
        println("pop max is called with ${maxHeap.peek()}")
        deletedIdSet.add(maxHeap.peek().id)
        return maxHeap.poll().value
    }

    private fun extractStack() {
        while (deletedIdSet.contains(stack.last().id))
            stack.removeLast()
    }

    private fun extractMaxHeap() {
        while (deletedIdSet.contains(maxHeap.peek().id))
            maxHeap.poll()
    }

    data class Element(val value: Int, val id: Int)
}

/**
 * Your MaxStack object will be instantiated and called as such:
 * var obj = MaxStack()
 * obj.push(x)
 * var param_2 = obj.pop()
 * var param_3 = obj.top()
 * var param_4 = obj.peekMax()
 * var param_5 = obj.popMax()
 */
fun main() {
    val maxStack = MaxStack()
    maxStack.push(5)
    maxStack.push(1)
    maxStack.push(5)

    // "top", "popMax", "top", "peekMax", "pop"
    println(maxStack.top())
    println(maxStack.popMax())
    println(maxStack.top())
    println(maxStack.peekMax())
    println(maxStack.pop())
    println(maxStack.top())
}