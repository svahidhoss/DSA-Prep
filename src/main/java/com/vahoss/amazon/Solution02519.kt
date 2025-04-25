package com.vahoss.amazon

import java.util.PriorityQueue
import java.util.ArrayDeque

class Solution02519 {

    fun kBigIndices(nums: IntArray, k: Int): Int {
        require(nums.size >= k) { "k must be less than or equal to the size of the input array" }
        // Every element is trivially larger than all elements in an empty window.
        if (k == 0) return nums.size
        // Use a deque to keep track of the max element's index in the window.
        val deque = ArrayDeque<Int>()
        var count = 0

        // Initialize the deque with the first k elements' indices.
        for (i in 0 until k) {
            while (deque.isNotEmpty() && nums[deque.last] <= nums[i]) {
                deque.removeLast()
            }
            deque.add(i)
        }

        for (i in k until nums.size) {
            // The element at the front of the deque is the largest in the window ending at i - 1.
            val maxInWindow = nums[deque.first]

            // Check if the current element is greater than the max in both k-sized windows.
            if (nums[i] > maxInWindow && (i < nums.size - k || nums[i] > nums[deque.first + k])) {
                count++
            }

            // Remove indices that are out of the current window.
            while (deque.isNotEmpty() && deque.first <= i - k) {
                deque.removeFirst()
            }

            // Remove elements that are smaller than the current element from the end of the deque.
            while (deque.isNotEmpty() && nums[deque.last] <= nums[i]) {
                deque.removeLast()
            }

            // Add the current element's index to the deque.
            deque.addLast(i)
        }

        // Check the last k elements.
        for (i in nums.size - k until nums.size) {
            if (nums[i] > nums[deque.first]) {
                count++
            }
            if (deque.first == i - k + 1) {
                deque.removeFirst()
            }
        }

        return count
    }


    fun kBigIndices2(nums: IntArray, k: Int): Int {
        if (nums.size < k || k == 0) return 0

        // Create and add the initial needed values to both heaps
        val maxHeap1 = PriorityQueue<Int>(k, Comparator.reverseOrder()).apply { addAll(nums.take(k)) }
        val maxHeap2 = PriorityQueue<Int>(k, Comparator.reverseOrder()).apply { addAll(nums.takeLast(k)) }

        val isBiggerArray1 = BooleanArray(nums.size)
        val isBiggerArray2 = BooleanArray(nums.size)

        var forward = k
        var backward = nums.size - k - 1
        while (forward <= nums.size - k - 1 && backward >= k) {
            val forwardVal = nums[forward]
            val backwardVal = nums[backward]
            // when both indexes point to the same location
            if (forward == backward) {
                val isBigger = forwardVal > maxHeap2.peek() && forwardVal > maxHeap1.peek()
                isBiggerArray1[forward] = isBigger
                isBiggerArray2[forward] = isBigger
            } else {
                if (forwardVal > maxHeap1.peek())
                    isBiggerArray1[forward] = true
                else {
                    maxHeap1.poll()
                    maxHeap1.add(forwardVal)
                }

                if (backwardVal > maxHeap2.peek())
                    isBiggerArray2[backward] = true
                else {
                    maxHeap2.poll()
                    maxHeap2.add(backwardVal)
                }
            }

            backward--
            forward++
        }

        val andResult = isBiggerArray1.mapIndexed { i, v -> v && isBiggerArray2[i] }
        return andResult.count { it }
    }
}

fun main() {
    val sol = Solution02519()
    println(sol.kBigIndices(intArrayOf(2, 3, 6, 5, 2, 3), k = 2))
    println(sol.kBigIndices(intArrayOf(1, 1, 1), k = 3))
    println(sol.kBigIndices2(intArrayOf(5, 2, 3, 5, 1, 7, 6), k = 3))
    println(sol.kBigIndices(intArrayOf(3, 8, 4, 2, 5, 3, 8, 6), 1))
    println(sol.kBigIndices(intArrayOf(4, 11, 14, 18, 16, 10, 15, 6, 2, 6, 9, 16, 6, 18, 5, 16, 3, 6), k = 3))
    println(
        sol.kBigIndices2(
            intArrayOf(
                92,
                84,
                37,
                19,
                16,
                85,
                20,
                79,
                25,
                89,
                55,
                67,
                84,
                3,
                79,
                38,
                16,
                44,
                2,
                54,
                58,
                94,
                69,
                71,
                14,
                24,
                13,
                21,
                14,
                18,
                84,
                37,
                2,
                13,
                26,
                54,
                82,
                30,
                53,
                72,
                56,
                94,
                72,
                67,
                38,
                14,
                80,
                92,
                65,
                85,
                70,
                58,
                81,
                39,
                43,
                31,
                53,
                43,
                87,
                19,
                93,
                67,
                27,
                12,
                80,
                38,
                94,
                92,
                67,
                54,
                56,
                20,
                83,
                85,
                78,
                83,
                85,
                85,
                72,
                52,
                32,
                24,
                6,
                3,
                89,
                3,
                5,
                3,
                55,
                93,
                1,
                13,
                88,
                47,
                48
            ), 12
        )
    )
}