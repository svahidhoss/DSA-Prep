package main.java.kotlin_solutions

import main.java.TreeNode

class Solutions00165 {

    fun sortedArrayToBST(nums: IntArray): TreeNode? {
        return buildBST(nums, 0, nums.size - 1)
    }

    private fun buildBST(nums: IntArray, beg: Int, end: Int): TreeNode? {
        if (beg > end) return null

        // To avoid potential integer overflow
        val index = beg + (end - beg) / 2
        val midNode = TreeNode(nums[index])
        midNode.left = buildBST(nums, beg, index - 1)
        midNode.right = buildBST(nums, index + 1, end)
        return midNode
    }

    fun sortedArrayToBST2(nums: IntArray): TreeNode? {
        val index = (nums.size - 1) / 2
        val root = TreeNode(nums[index])
        root.left = sortedArrayToBST(nums, 0, index - 1)
        root.right = sortedArrayToBST(nums, index + 1, nums.size - 1)
        return root
    }

    private fun sortedArrayToBST(nums: IntArray, beg: Int, end: Int): TreeNode? {
        if (beg > end) return null
        if (beg == end) return TreeNode(nums[beg])

        val index = (beg + end) / 2
        val midNode = TreeNode(nums[index])
        midNode.left = sortedArrayToBST(nums, beg, index - 1)
        midNode.right = sortedArrayToBST(nums, index + 1, end)
        return midNode
    }
}

fun main() {
    val sol = Solutions00165()
    var nums = intArrayOf(-10, -3, 0, 5, 9)
    println(sol.sortedArrayToBST(nums))
    nums = intArrayOf(1, 3)
    println(sol.sortedArrayToBST(nums))
}