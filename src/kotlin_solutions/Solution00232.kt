import java.util.*
import kotlin.collections.ArrayDeque

class MyQueue2 {
    /**
     * Holds the incoming values in a regular
     * way they should be in a stack.
     */
    private val stack1 = Stack<Int>()

    /**
     * opposite of stack 1.
     */
    private val stack2 = Stack<Int>()

    fun push(x: Int) {
        stack1.push(x)

    }

    fun pop(): Int {
        if (stack2.isEmpty()) transferFrom1to2()

        return stack2.pop()
    }

    fun peek(): Int {
        if (stack2.isEmpty()) transferFrom1to2()

        return stack2.peek()
    }

    fun empty(): Boolean {
        return stack1.isEmpty() && stack2.isEmpty()
    }

    private fun transferFrom1to2() {
        // move all elements of stack1 to stack2
        while (stack1.isNotEmpty())
            stack2.push(stack1.pop())
    }
}

class MyQueue<T> {
    /**
     * Holds values as they are inserted.
     */
    private val stack1 = ArrayDeque<T>()

    /**
     * This will hold the t values in the reverse
     * order of their insertions.
     */
    private val stack2 = ArrayDeque<T>()

    fun push(t: T) {
        stack1.add(t)
    }

    fun pop(): T {
        if (stack2.isEmpty()) {
            while (stack1.isNotEmpty()) stack2.addLast(stack1.removeLast())
        }

        return stack2.removeLast()
    }

    fun peek(): T {
        if (stack2.isEmpty()) {
            while (stack1.isNotEmpty()) stack2.addLast(stack1.removeLast())
        }

        return stack2.last()
    }

    fun empty(): Boolean {
        return stack1.isEmpty() && stack2.isEmpty()
    }

}

/**
 * Your MyQueue object will be instantiated and called as such:
 * var obj = MyQueue()
 * obj.push(x)
 * var param_2 = obj.pop()
 * var param_3 = obj.peek()
 * var param_4 = obj.empty()
 */
fun main() {
    // Create an instance of MyQueue
    val myQueue = MyQueue<Int>()

    // Test Case 1: Push elements and Peek
    myQueue.push(1) // Queue: [1]
    myQueue.push(2) // Queue: [1, 2]
    println("Test Case 1: Peek")
    println("Expected: 1, Actual: ${myQueue.peek()}") // Expected: 1

    // Test Case 2: Pop elements
    println("\nTest Case 2: Pop")
    println("Expected: 1, Actual: ${myQueue.pop()}")  // Expected: 1
    println("Expected: 2, Actual: ${myQueue.pop()}")  // Expected: 2

    // Test Case 3: Check if Queue is Empty
    println("\nTest Case 3: Queue Empty")
    println("Expected: true, Actual: ${myQueue.empty()}") // Expected: true

    // Test Case 4: Push and Pop with alternating operations
    myQueue.push(3) // Queue: [3]
    myQueue.push(4) // Queue: [3, 4]
    println("\nTest Case 4: Push and Pop")
    println("Expected: 3, Actual: ${myQueue.pop()}")  // Expected: 3
    println("Expected: 4, Actual: ${myQueue.peek()}") // Expected: 4
    println("Expected: false, Actual: ${myQueue.empty()}") // Expected: false

    // Test Case 5: Push multiple elements and Pop them all
    myQueue.push(5) // Queue: [4, 5]
    myQueue.push(6) // Queue: [4, 5, 6]
    println("\nTest Case 5: Push multiple and Pop")
    println("Expected: 4, Actual: ${myQueue.pop()}")  // Expected: 4
    println("Expected: 5, Actual: ${myQueue.pop()}")  // Expected: 5
    println("Expected: 6, Actual: ${myQueue.pop()}")  // Expected: 6
    println("Expected: true, Actual: ${myQueue.empty()}") // Expected: true

    // Test Case 6: Handling consecutive empty checks
    println("\nTest Case 6: Consecutive Empty Checks")
    println("Expected: true, Actual: ${myQueue.empty()}") // Expected: true
}
