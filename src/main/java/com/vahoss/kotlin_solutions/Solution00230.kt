package com.vahoss.kotlin_solutions

import com.vahoss.TreeNode

class Solution0230 {
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
    private var count = 0
    private var result: Int = -1

    private val resultList = mutableListOf<Int>()

    fun kthSmallest(root: TreeNode?, k: Int): Int {
//        resultList.clear()
        // val resultList = mutableListOf<Int>()
//        rec(root, k, resultList)
//        return resultList[k - 1]
        count = 0
        rec(root, k)
        return result
    }

    private fun rec(root: TreeNode?, k: Int) {
        if (root == null || count > k) return

        rec(root.left, k)

        if (++count == k) result = root.`val`

        rec(root.right, k)
    }

    private fun rec(root: TreeNode?, k: Int, list: MutableList<Int>) {
        if (root == null || resultList.size == k) return

        rec(root.left, k, list)
        list.add(root.`val`)
        rec(root.right, k, list)
    }
}


fun main() {
    val sol = Solution0230()
    var tree = TreeNode(3)
    tree.left = TreeNode(1)
    tree.right = TreeNode(4)
    tree.left!!.right = TreeNode(2)

    println(sol.kthSmallest(tree, 1))

    tree = TreeNode(5)
    tree.left = TreeNode(3)
    tree.right = TreeNode(6)
    tree.left!!.left = TreeNode(2)
    tree.left!!.right = TreeNode(4)
    tree.left!!.left!!.left = TreeNode(1)

    println(sol.kthSmallest(tree, 3))
}