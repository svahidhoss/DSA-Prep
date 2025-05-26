package com.vahoss.kotlin_solutions

class Solution00039 {

    // Final list of valid combinations
    val result = mutableListOf<List<Int>>()

    fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
        recursiveCombinationSum(candidates.sortedArray(), target, 0, mutableListOf())
        return result
    }

    private fun recursiveCombinationSum(
        candidates: IntArray,
        target: Int,
        begIndex: Int,
        current: MutableList<Int>
    ): Boolean {
        // Base case: overshot the target, stop this path
        if (target < 0) return false
        // Base case: exact match, store the combination
        if (target == 0) {
            result.add(ArrayList(current)) // Add a copy to prevent mutation
            return true
        }

        // Explore each candidate starting from the current index
        for (i in begIndex until candidates.size) {
            current.add(candidates[i])
            // Recurse with the same index `i` to allow unlimited reuse of the same number
            val shouldContinue = recursiveCombinationSum(candidates, target - candidates[i], i, current)
            // Backtrack: remove last element to explore new path
            current.removeAt(current.lastIndex)
            // Optional pruning: if a path was invalid (e.g., candidate too large), stop further exploration
            if (!shouldContinue) break
        }

        // Return true to allow upper levels to keep exploring
        return true
    }

    fun combinationSum2(candidates: IntArray, target: Int): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        val sortedCandidates = candidates.sortedArray()

        fun backtrack(start: Int, remaining: Int, current: MutableList<Int>) {
            if (remaining == 0) {
                result.add(ArrayList(current))
                return
            }

            for (i in start until sortedCandidates.size) {
                val candidate = sortedCandidates[i]
                if (candidate > remaining) break  // Early pruning

                current.add(candidate)
                backtrack(i, remaining - candidate, current)
                current.removeAt(current.lastIndex)  // Backtrack
            }
        }

        backtrack(0, target, mutableListOf())
        return result
    }


}

fun main() {
    val sol = Solution00039()
    println(sol.combinationSum(intArrayOf(2, 3, 6, 7), 7))
    println(sol.combinationSum(intArrayOf(2, 3, 5), 8))
}
