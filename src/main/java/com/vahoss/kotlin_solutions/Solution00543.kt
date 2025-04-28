package com.vahoss.kotlin_solutions

import com.vahoss.TreeNode
import kotlin.math.max

/**
 * This makes the assumption that the pass goes thorough
 * the root which is not necessarily correct.
 */
fun diameterOfBinaryTree2(root: TreeNode?): Int {
    if (root == null) return 0

    val left = diameterOfBinaryTree2(root.left)
    val right = diameterOfBinaryTree2(root.right)
    return 1 + max(left, right)
}

class Solution00543 {
    fun diameterOfBinaryTree(root: TreeNode?): Int {
        // Reset to 0 each time you call this function
        val diameter = intArrayOf(0) // Mutable container
        recursiveDiameter(root, diameter)
        return diameter[0]
    }

    private fun recursiveDiameter(root: TreeNode?, diameter: IntArray): Int {
        if (root == null) return 0

        val left = recursiveDiameter(root.left, diameter)
        val right = recursiveDiameter(root.right, diameter)
        // calculate the diameter of the current node
        diameter[0] = max(diameter[0], left + right)

        // return the height
        return max(left, right) + 1
    }

    var max = 0

    /**
     * Does a post order traversal of binary trees.
     */
    fun diameterOfBinaryTree2(root: TreeNode?): Int {
        recursiveDiameter2(root)
        return max
    }

    private fun recursiveDiameter2(root: TreeNode?): Int {
        if (root == null) return 0

        val left = recursiveDiameter2(root.left)
        val right = recursiveDiameter2(root.right)
        max = max(max, left + right)
        return 1 + max(left, right)
    }
}

fun main() {
    val solution = Solution00543()

    var treeNode = TreeNode(1)
    treeNode.left = TreeNode(2)
    treeNode.right = TreeNode(3)

    treeNode.left?.left = TreeNode(4)
    treeNode.left?.right = TreeNode(5)

    println(solution.diameterOfBinaryTree(treeNode))
    solution.max = 0

    treeNode = TreeNode(1)
    treeNode.left = TreeNode(2)
    println(solution.diameterOfBinaryTree(treeNode))
}
