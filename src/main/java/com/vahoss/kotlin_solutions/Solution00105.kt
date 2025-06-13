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

    /**
     * To improve performance, we avoid slicing arrays every time (which is costly).
     * Instead, we pass indices and use a preorderIndex pointer to track the current root.
     */
    fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
        return buildTree(preorder, 0, preorder.size, inorder, 0, inorder.size)
    }

    /**
     * Custom build tree fn that takes start and end
     * indexes of both preorder and inorder methods.
     */
    private fun buildTree(
        preorder: IntArray,
        preFrom: Int,
        preTo: Int,
        inorder: IntArray,
        inFrom: Int,
        inTo: Int
    ): TreeNode? {
        if (preFrom >= preTo || inFrom >= inTo) return null
        val root = TreeNode(preorder[preFrom])
        if (preFrom + 1 == preTo || inFrom + 1 == inTo) return root

        // val inorderRootIndex = (inFrom until inTo).first { inorder[it] == root.`val` }
        var inorderRootIndex = inFrom
        for (i in inFrom until inTo) {
            if (inorder[i] == root.`val`)
                inorderRootIndex = i
        }

        val leftSize = inorderRootIndex - inFrom

        root.left = buildTree(
            preorder,
            preFrom + 1,
            preFrom + 1 + leftSize,  // ✅ correct range
            inorder,
            inFrom,
            inorderRootIndex
        )
        root.right = buildTree(
            preorder,
            preFrom + 1 + leftSize,
            preTo,
            inorder,
            inorderRootIndex + 1,
            inTo
        )

        return root
    }

    fun buildTreeRecursive(preorder: IntArray, inorder: IntArray): TreeNode? {
        if (preorder.isEmpty() || inorder.isEmpty()) return null
        val root = TreeNode(preorder[0])
        if (preorder.size == 1 && inorder.size == 1) return root

        val inorderRootIndex = inorder.indexOf(preorder[0])

        root.left = buildTreeRecursive(
            preorder.copyOfRange(1, inorderRootIndex + 1),
            inorder.copyOfRange(0, inorderRootIndex)
        )
        root.right = buildTreeRecursive(
            preorder.copyOfRange(inorderRootIndex + 1, preorder.size),
            inorder.copyOfRange(inorderRootIndex + 1, inorder.size)
        )

        return root
    }
}

fun main() {
    val solution = Solution00105()
//    println(solution.buildTreeRecursive(intArrayOf(3, 9, 8, 20, 15, 7), intArrayOf(8, 9, 3, 15, 20, 7)))
    println(solution.buildTree(intArrayOf(3, 9, 8, 20, 15, 7), intArrayOf(8, 9, 3, 15, 20, 7)))
    println(solution.buildTree(intArrayOf(1, 2), intArrayOf(2, 1)))
}
