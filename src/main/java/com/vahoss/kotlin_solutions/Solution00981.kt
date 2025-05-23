package com.vahoss.kotlin_solutions

class TimeMap() {
    private val valMap = mutableMapOf<String, MutableList<Pair<Int, String>>>()

    fun set(key: String, value: String, timestamp: Int) {
        if (valMap[key] == null) {
            valMap[key] = mutableListOf()
        }
        valMap[key]?.add(timestamp to value)
    }

    fun get(key: String, timestamp: Int): String {
        valMap[key]?.let { list ->
            return binarySearchByFirst(list, timestamp)
        }
        return ""
    }

    private fun binarySearchByFirst(list: List<Pair<Int, String>>, target: Int): String {
        var left = 0
        var right = list.lastIndex
        var result = ""  // default if not found

        while (left <= right) {
            val mid = left + (right - left) / 2
            val midTimestamp = list[mid].first

            if (midTimestamp <= target) {
                result = list[mid].second  // potential answer
                left = mid + 1             // try to find a later timestamp
            } else {
                right = mid - 1
            }
        }

        return result
    }

}

/**
 * Your TimeMap object will be instantiated and called as such:
 * var obj = TimeMap()
 * obj.set(key,value,timestamp)
 * var param_2 = obj.get(key,timestamp)
 */

fun main() {
    val tm = TimeMap()
    tm.set("foo", "bar", 1)
    println(tm.get("foo", 1) == "bar")    // Exact match
    println(tm.get("foo", 3) == "bar")    // Closest ≤ 3 is at 1
    tm.set("foo", "bar2", 4)
    println(tm.get("foo", 4) == "bar2")   // Exact match
    println(tm.get("foo", 5) == "bar2")   // Closest ≤ 5 is at 4
    println(tm.get("foo", 2) == "bar")   // Closest ≤ 5 is at 4
}