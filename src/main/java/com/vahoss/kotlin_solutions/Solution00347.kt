package com.vahoss.kotlin_solutions

import java.util.*

class Solution00347 {
    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        // put everything in a map first:
        val map = mutableMapOf<Int, Int>()
        for (v in nums) {
            map[v] = map.getOrDefault(v, 0) + 1
        }

        // put everything into a min heap using their count
        val minHeap = PriorityQueue<Pair<Int, Int>> { a, b -> a.second - b.second }

        // as soon as the size gets to k >= n, start removing the minimum value
        for (entry in map.entries) {
            if (minHeap.size == k) {
                // compare against the min heap val
                if (minHeap.peek().second < entry.value) {
                    // replace the min value
                    minHeap.poll()
                    minHeap.offer(Pair(entry.key, entry.value))
                }
            } else {
                minHeap.offer(Pair(entry.key, entry.value))
            }
        }

        return minHeap.map { it.first }.toIntArray()
    }

    fun topKFrequentOptimized(nums: IntArray, k: Int): IntArray {
        // Count frequencies
        val map = mutableMapOf<Int, Int>()
        for (v in nums) {
            map[v] = map.getOrDefault(v, 0) + 1
        }

        // Min heap to keep top k frequent elements
        val minHeap = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })

        for (entry in map.entries) {
            minHeap.offer(Pair(entry.key, entry.value))

            // Remove least frequent if heap size exceeds k
            if (minHeap.size > k) {
                minHeap.poll()
            }
        }

        return minHeap.map { it.first }.toIntArray()
    }
}

fun main(args: Array<String>) {
    val sol = Solution00347()
    println(Arrays.toString(sol.topKFrequent(intArrayOf(1, 1, 1, 2, 2, 3), k = 2)))
    println(Arrays.toString(sol.topKFrequent(intArrayOf(1), 1)))
    println(Arrays.toString(sol.topKFrequent(intArrayOf(3, 0, 1, 0), k = 1)))
}