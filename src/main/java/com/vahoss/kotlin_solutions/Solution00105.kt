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
 * preorder =
 * [3,9, 8,20,15,7]
 *    |
 * inorder =
 * [8,9,3,15,20,7]
 *    <-
 *         3
 *       9   20
 *     8    15  7
 */
class Solution00105 {
    fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
        if (preorder.isEmpty() || inorder.isEmpty()) return null
        val root = TreeNode(preorder[0])
        if (preorder.size == 1 && inorder.size == 1) return root

        val inorderRootIndex = inorder.indexOf(preorder[0])

        root.left = buildTree(
            preorder.copyOfRange(1, inorderRootIndex + 1),
            inorder.copyOfRange(0, inorderRootIndex)
        )
        root.right = buildTree(
            preorder.copyOfRange(inorderRootIndex + 1, preorder.size),
            inorder.copyOfRange(inorderRootIndex + 1, inorder.size)
        )

        return root
    }

    // TODO:Optional optimization (if you're curious):
    //If you want to improve performance, you can avoid slicing arrays every time (which is costly).
    // Instead, you can pass indices and use a preorderIndex pointer to track the current root.
}

fun main() {
    val solution = Solution00105()
    println(solution.buildTree(intArrayOf(3, 9, 8, 20, 15, 7), intArrayOf(8, 9, 3, 15, 20, 7)))
}
