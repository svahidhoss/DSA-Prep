class MinStack() {
    private val minStack = ArrayDeque<Int>()
    private val minOnlyStack = ArrayDeque<Int>()

    fun push(`val`: Int) {
        minStack.add(`val`)
        try {
            // It's important to use equal to keep hold
            // of minimums that are arriving
            if (`val` <= minOnlyStack.last())
                minOnlyStack.add(`val`)
        } catch (e: NoSuchElementException) {
            minOnlyStack.add(`val`)
        }
    }

    fun pop() {
        if (minStack.last() == minOnlyStack.last()) minOnlyStack.removeLast()
        minStack.removeLast()
    }

    fun top(): Int {
        return minStack.last()
    }

    fun getMin(): Int {
        return minOnlyStack.last()
    }
}

class MinStack2 {
    private val a = ArrayDeque<Int>()
    private val m = ArrayDeque<Int>()

    fun push(`val`: Int) {
        a.add(`val`)
        // At each point it's only important to keep track of the minimum values
        m.lastOrNull()?.let {
            if (`val` <= it) m.add(`val`)
        } ?: m.add(`val`)
    }

    fun pop() {
        if (a.last() == m.last()) m.removeLast()
        a.removeLast()
    }

    fun top(): Int {
        return a.last()
    }

    fun getMin(): Int {
        return m.last()
    }
}

fun main() {
    val minStack = MinStack2()
    minStack.push(-2)
    minStack.push(0)
    minStack.push(-3)
    println(minStack.getMin())// return -3
    minStack.pop()
    println(minStack.top()) // return 0
    println(minStack.getMin()) // return -2
}