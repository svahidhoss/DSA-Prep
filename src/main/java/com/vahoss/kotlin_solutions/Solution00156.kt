package com.vahoss.kotlin_solutions

import com.vahoss.TreeNode

class Solution0156 {
    fun upsideDownBinaryTreeRecursive(root: TreeNode?): TreeNode? {
        if (root == null || root.left == null && root.right == null)
            return root

        val leftResult = upsideDownBinaryTreeRecursive(root.left)

        root.left!!.left = root.right
        root.left!!.right = root

        root.left = null
        root.right = null

        return leftResult
    }

    fun upsideDownBinaryTree2(root: TreeNode?): TreeNode? {
        var curr = root
        var next: TreeNode? = null
        var temp: TreeNode? = null
        var prev: TreeNode? = null
        while (curr != null) {
            next = curr.left

            // swapping nodes now, need temp to keep the previous right child
            curr.left = temp
            temp = curr.right
            curr.right = prev
            prev = curr
            curr = next
        }
        return prev
    }

    fun upsideDownBinaryTree(root: TreeNode?): TreeNode? {
        if (root == null) return null
        val parentsMap = mutableMapOf<TreeNode, TreeNode?>()

        parentsMap[root] = null
        var node = root
        while (node?.left != null) {
            parentsMap[node.left!!] = node
            node = node.left
        }

        val result = node
        while (parentsMap.containsKey(node)) {
            parentsMap[node]?.let {
                node!!.left = it.right
                node!!.right = it
                node = it
//                it.left = null
//                it.right = null
            } ?: break
        }
        // Make sure the old root is a leaf now
        node?.left = null
        node?.left = null

        return result
    }

    fun upsideDownBinaryTreeFast(root: TreeNode?): TreeNode? {
        var node = root
        // var pre = root
        var pre: TreeNode? = null
        var preRight: TreeNode? = null

        while(node != null) {
            val temp = node!!.left

            node.left = preRight
            preRight = node.right
            node.right = pre

            pre = node
            node = temp
        }

        return pre
    }
}

fun main() {
    val tree = TreeNode(1)
    tree.left = TreeNode(2)
    tree.right = TreeNode(3)
    tree.left!!.left = TreeNode(4)
    tree.left!!.right = TreeNode(5)

    val sol = Solution0156()
//    println(sol.upsideDownBinaryTree(tree)?.`val`)
    println(sol.upsideDownBinaryTreeFast(tree)?.`val`)
}