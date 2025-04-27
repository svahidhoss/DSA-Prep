package com.vahoss.kotlin_solutions

import com.vahoss.TreeNode
import kotlin.collections.ArrayDeque
import kotlin.collections.ArrayList


class Solution00102 {
    fun levelOrderBFS(root: TreeNode?): List<List<Int>> {
        val result = ArrayList<List<Int>>()
        val queue = ArrayDeque<TreeNode>()

        root?.let {
            queue.add(it)
        }

        while (queue.isNotEmpty()) {
            val levelSize = queue.size
            val currentRow = mutableListOf<Int>()
            repeat(levelSize) {
                val current = queue.removeFirst()
                currentRow.add(current.`val`)
                current.left?.let { queue.add(it) }
                current.right?.let { queue.add(it) }
            }
            result.add(currentRow.toList())
        }

        return result
    }

    fun levelOrder(root: TreeNode?): List<List<Int>> {
        val result = ArrayList<ArrayList<Int>>()
        val queue = ArrayDeque<NodeWithDepth>()

        root?.let {
            queue.add(NodeWithDepth(it, 0))
        } ?: return result

        while (!queue.isEmpty()) {
            val current = queue.removeFirst()
            // Ensure result has a list for this depth
            if (result.size <= current.depth) result.add(ArrayList())

            // Add current node's value to its level's list
            result[current.depth].add(current.treeNode.`val`)

            current.treeNode.left?.let {
                queue.add(NodeWithDepth(it, current.depth + 1))
            }
            current.treeNode.right?.let {
                queue.add(NodeWithDepth(it, current.depth + 1))
            }
        }

        return result
    }

    data class NodeWithDepth(val treeNode: TreeNode, val depth: Int)

    fun levelOrderDFS(root: TreeNode?): List<List<Int>> {
        val result = mutableListOf<MutableList<Int>>()
        root?.let { levelHelper(result, it, 0) }
        return result
    }

    private fun levelHelper(result: MutableList<MutableList<Int>>, node: TreeNode, d: Int) {
        while (result.size - 1 < d) result.add(mutableListOf())
        result[d].add(node.`val`)

        node.left?.let { levelHelper(result, it, d + 1) }
        node.right?.let { levelHelper(result, it, d + 1) }
    }
}

fun main() {
    val s = Solution00102()
    var t = TreeNode(1)
    t.left = TreeNode(2)
    t.right = TreeNode(3)
    t.left?.left = TreeNode(4)
    t.left?.right = TreeNode(5)

    println(s.levelOrder(t))
    println(s.levelOrderBFS(t))
    println(s.levelOrderDFS(t))

    t = TreeNode(3)
    t.left = TreeNode(9)
    t.right = TreeNode(20)
    t.right?.left = TreeNode(15)
    t.right?.right = TreeNode(7)

    println(s.levelOrder(t))
    println(s.levelOrderBFS(t))
    println(s.levelOrderDFS(t))
}
