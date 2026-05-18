package com.vahoss.kotlin_solutions

import java.util.Collections
import java.util.PriorityQueue


/**
 * gap: 2
 * 'A', 'A', 'A', 'B', 'B', 'B'
 * A 3
 * B 3
 *
 * string:
 *
 */

class Solution00621 {

    // Greedy simulation with max-heap — O(tasks.size * log k) time, O(k) space
    // where k = unique task types (≤ 26). Each round fills n+1 slots: we greedily
    // pick the most frequent tasks first, then pad with idle if the heap runs dry.
    fun leastInterval(tasks: CharArray, n: Int): Int {
        val map = mutableMapOf<Char, Int>()
        tasks.forEach { map[it] = map.getOrDefault(it, 0) + 1 }

        // Only frequencies matter — character identity is irrelevant for counting
        val maxHeap = PriorityQueue<Int>(Collections.reverseOrder())
        maxHeap.addAll(map.values)

        var result = 0
        var tasksCounter = 0
        var nCounter = 0
        while (tasksCounter < tasks.size) {
            val toAdd = mutableListOf<Int>()
            // Fill up to n+1 slots with the most frequent remaining tasks
            while (nCounter <= n && maxHeap.isNotEmpty()) {
                val current = maxHeap.poll()
                result++
                tasksCounter++
                nCounter++
                // Only push back if this task type still has remaining work
                if (current - 1 > 0) toAdd.add(current - 1)
            }
            // Heap ran dry mid-round — pad remaining slots with idle
            if (maxHeap.isEmpty() && tasksCounter < tasks.size) {
                result += (n + 1 - nCounter)
            }
            maxHeap.addAll(toAdd)
            nCounter = 0
        }

        return result
    }

    // Mathematical formula — O(tasks.size) time, O(1) space
    // The bottleneck is the most frequent task. It creates (maxFreq-1) full cycles
    // of size (n+1), plus a final partial cycle containing all tasks tied for max
    // frequency. If tasks are dense enough to fill every slot, no idle is needed
    // and the answer is simply tasks.size.
    fun leastIntervalOptimal(tasks: CharArray, n: Int): Int {
        val freq = IntArray(26)
        for (task in tasks) freq[task - 'A']++
        val maxFreq = freq.max()
        val countMax = freq.count { it == maxFreq }
        return maxOf(tasks.size, (maxFreq - 1) * (n + 1) + countMax)
    }
}

fun main() {
    val sol = Solution00621()
    val cases = listOf(
        Pair(charArrayOf('A', 'A', 'A', 'B', 'B', 'B'), 2) to 8,
        Pair(charArrayOf('A', 'C', 'A', 'B', 'D', 'B'), 1) to 6,
        Pair(charArrayOf('A', 'A', 'A', 'B', 'B', 'B'), 3) to 10,
        Pair(charArrayOf('B', 'C', 'D', 'A', 'A', 'A', 'A', 'G'), 1) to 8,
        Pair(charArrayOf('A', 'A', 'A', 'B', 'B', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K'), 7) to 18,
    )
    for ((input, expected) in cases) {
        val (tasks, n) = input
        val heap = sol.leastInterval(tasks, n)
        val formula = sol.leastIntervalOptimal(tasks, n)
        println("heap=$heap formula=$formula expected=$expected ${if (heap == expected && formula == expected) "✓" else "✗"}")
    }
}