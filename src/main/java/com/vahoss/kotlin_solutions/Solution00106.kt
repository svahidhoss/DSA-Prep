package com.vahoss.kotlin_solutions

import com.vahoss.TreeNode

/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */

/**
 * Input:
 * inorder =   [9,  3,   15,20,7]
 *
 * postorder = [9,15,7,20,3]
 *         3
 *       9   20
 *         15  7
 */
class Solution00106 {

    fun buildTree(inorder: IntArray, postorder: IntArray): TreeNode? {
        if (inorder.size != postorder.size) throw IllegalArgumentException("The size of the inorder must be the same as postorder")
        if (inorder.isEmpty() || postorder.isEmpty()) return null

        val root = TreeNode(postorder.last())

        val leftSize = inorder.indexOf(root.`val`)

        root.left = buildTree(inorder.copyOfRange(0, leftSize), postorder.copyOfRange(0, leftSize))
        root.right = buildTree(
            inorder.copyOfRange(leftSize + 1, inorder.size),
            postorder.copyOfRange(leftSize, postorder.size - 1)
        )


        return root
    }
}

fun main() {
    val solution = Solution00106()
    println(solution.buildTree(intArrayOf(9, 3, 15, 20, 7), intArrayOf(9, 15, 7, 20, 3)))
    println(solution.buildTree(intArrayOf(1, 2), intArrayOf(2, 1)))
    println(solution.buildTree(intArrayOf(-1), intArrayOf(-1)))
}
