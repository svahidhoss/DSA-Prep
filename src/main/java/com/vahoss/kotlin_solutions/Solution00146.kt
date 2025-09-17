package com.vahoss.kotlin_solutions

class LRUCache(val capacity: Int) {

    val deque = ArrayDeque<Int>(capacity)

    val map = HashMap<Int, Int>()

    /**
     * Should provide the value for the provided key if it exists.
     * Should also update the key-value pair to be the most recently used one.
     */
    fun get(key: Int): Int {
        if (map.containsKey(key)) {
            deque.remove(key)
            deque.addFirst(key)
            return map[key]!!
        } else return -1
    }

    fun put(key: Int, value: Int) {
        // if updating an existing element, remove it first
        if (map.containsKey(key)) {
            deque.remove(key)
        }

        // always update the map
        map[key] = value

        // check the capacity constraint
        if (map.size > capacity) {
            map.remove(deque.removeLast())
        }

        deque.addFirst(key)
    }
}
