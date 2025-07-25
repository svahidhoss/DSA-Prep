package com.vahoss.kotlin_solutions

class Solution00046 {

    fun permute(nums: IntArray): List<List<Int>> {
        // Stores all permutations
        val result = mutableListOf<List<Int>>()
        // Current permutation path being built
        val currentPermutation = mutableListOf<Int>()
        // Tracks which elements are already used
        val used = BooleanArray(nums.size)

        // Start the recursive backtracking process
        permute(nums, used, currentPermutation, result)
        return result
    }

    private fun permute(
        nums: IntArray,
        used: BooleanArray,
        path: MutableList<Int>,
        result: MutableList<List<Int>>
    ) {
        // BASE CASE: If current permutation is complete
        if (path.size == nums.size) {
            result.add(ArrayList(path))
            return
        }

        // RECURSIVE CASE: Try adding each unused element to current permutation
        for (i in nums.indices) {
            if (used[i]) continue
            path.add(nums[i])
            used[i] = true
            permute(nums, used, path, result)
            // backtrack: Undo the choice to try other possibilities
            // This is crucial - we remove the element we just added
            path.removeLast()
            used[i] = false
        }
    }
}

fun main() {
    val sol = Solution00046()
    println(sol.permute(intArrayOf(1)))
    println(sol.permute(intArrayOf(0, 1)))
    println(sol.permute(intArrayOf(1, 2, 3)))
}
