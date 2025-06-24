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

        val inorderMap = inorder.withIndex().associate { it.value to it.index }

        return buildTree(
            inorder, 0, inorder.lastIndex,
            postorder, 0, postorder.lastIndex,
            inorderMap
        )
    }

    private fun buildTree(
        inorder: IntArray,
        begInorder: Int,
        endInorder: Int,
        postorder: IntArray,
        begPostorder: Int,
        endPostorder: Int,
        map: Map<Int, Int>
    ): TreeNode? {
        // end values are exclusive
        if (begInorder > endInorder || begPostorder > endPostorder) return null
        val root = TreeNode(postorder[endPostorder])
        // Find the index of the root in the inorder array
        val rootIndex = map[root.`val`]!!

        val leftSize = rootIndex - begInorder

        root.left = buildTree(
            inorder, begInorder, rootIndex - 1,
            postorder, begPostorder, begPostorder + leftSize - 1,
            map
        )
        root.right = buildTree(
            inorder, rootIndex + 1, endInorder,
            postorder, begPostorder + leftSize, endPostorder - 1,
            map
        )

        return root
    }

    fun buildTree2(inorder: IntArray, postorder: IntArray): TreeNode? {
        if (inorder.size != postorder.size) throw IllegalArgumentException("The size of the inorder must be the same as postorder")
        if (inorder.isEmpty() || postorder.isEmpty()) return null

        // Root is the last element in postorder traversal
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
