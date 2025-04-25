package java

class CustomQueue<T> {

    private val innerList = mutableListOf<T>()

    fun enqueue(t: T) {
        innerList.add(t)
    }

    fun dequeue(): T? {
        if (innerList.isEmpty())
            return null
        return innerList.removeAt(0)
    }

    fun isEmpty(): Boolean {
        return innerList.size == 0
    }

    fun peek(): T? {
        if (innerList.isEmpty())
            return null
        return innerList.first()
    }

    fun size() = innerList.size
}

fun main(args: Array<String>) {
    val cQ = CustomQueue<Int>()
    cQ.enqueue(45)
    cQ.enqueue(35)
    println(cQ.peek())
    println(cQ.dequeue())
    println(cQ.peek())
    println(cQ.dequeue())
    println(cQ.dequeue())
}
