import java.lang.IllegalStateException
import java.util.*
import kotlin.math.min

class Solution00397 {
    // Default cases + 2^31-1
    private val map = mutableMapOf(1 to 0, 2 to 1, Integer.MAX_VALUE to 32)

    fun integerReplacement(n: Int): Int {
        // If the result is already in the map, return it
        map[n]?.let { return it }

        val result = if (n % 2 == 0) integerReplacement(n / 2) + 1
        else min(integerReplacement(n - 1), integerReplacement(n + 1)) + 1

        map[n] = result
        return result
    }

    fun integerReplacementRec(n: Int): Int {
        if (n == 1) return 0
        if (n == 2) return 1
        if (n == Int.MAX_VALUE) return 32
        return if (n % 2 == 0) integerReplacementRec(n / 2) + 1
            else 1 + min(integerReplacementRec(n - 1), integerReplacementRec(n + 1))
    }

    val lookup = mutableMapOf(1 to 0, 2 to 1) // skip computations already done

    fun integerReplacement2(n: Int): Int {
        lookup[n]?.let { return it } // done

        val half = n/2
        val response = if (n % 2 == 0) {
            integerReplacement2(half) + 1
        } else {
            val right = integerReplacement2(half + 1) // + 1 / 2 | when x is odd: floor(x/2) + 1 == (x+1) / 2
            val left = integerReplacement2(half) // - 1 / 2  | when x is odd: floor(x/2) == (x-1) / 2
            (if (left < right) left else right) + 2 // the runner doesn't like the max function, so...
        }
        lookup[n] = response
        return response
    }

    /**
     * The stack overflow error occurs because the recursive calls
     * in the integerReplacement function cause the call stack to
     * grow too deep for large input values. To avoid this issue,
     * you can use an iterative approach with a queue (BFS) instead
     * of recursion. This will prevent the call stack from growing
     * too deep and eliminate the stack overflow error.
     *
     * Here's an implementation of the integerReplacement
     * function using an iterative BFS approach:
     */
    fun integerReplacementIterative(n: Int): Int {
        val queue: Queue<Pair<Long, Int>> = LinkedList()
        queue.offer(Pair(n.toLong(), 0))

        while (queue.isNotEmpty()) {
            val (current, steps) = queue.poll()

            // Returns the one that reaches 1 faster
            if (current == 1L) {
                return steps
            }

            if (current % 2 == 0L) {
                queue.offer(Pair(current / 2, steps + 1))
            } else {
                queue.offer(Pair(current - 1, steps + 1))
                queue.offer(Pair(current + 1, steps + 1))
            }
        }

        throw IllegalStateException("This line should never be reached")
    }
}

fun main(args: Array<String>) {
    val sol = Solution00397()
    println(sol.integerReplacementIterative(8))
    println(sol.integerReplacementIterative(7))
    println(sol.integerReplacementIterative(4))
    println(sol.integerReplacementIterative(44))
    println(sol.integerReplacementIterative(2147483647))
}