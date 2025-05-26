package com.vahoss.kotlin_solutions

class Solution00077 {
    fun combine(n: Int, k: Int): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        val currentList = mutableListOf<Int>()

        fun recCombine(start: Int) {
            if (currentList.size == k) {
                result.add(ArrayList(currentList))
                return
            }

            for (i in start..n) {
                currentList.add(i)
                recCombine(i+1)
                currentList.removeLast()
            }
        }

        recCombine(1)
        return result
    }
}

fun main() {
    val s = Solution00077()
    println(s.combine(4, 2))
    println(s.combine(1, 1))
}