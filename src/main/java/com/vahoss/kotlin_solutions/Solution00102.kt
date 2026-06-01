package com.vahoss.kotlin_solutions

import com.vahoss.TreeNode
import kotlin.collections.ArrayDeque
import kotlin.collections.ArrayList


class Solution00102 {
    /**
     * Recursive DFS: groups nodes by depth via a pre-order traversal.
     *
     * Time:  O(n) every node is visited exactly once.
     * Space: O(h) auxiliary for the call stack, where h is the tree height.
     *        O(log n) for a balanced tree; O(n) worst case for a skewed tree.
     *        O(n) for the output.
     */
    fun levelOrder(root: TreeNode?): List<List<Int>> {
        if (root == null) return emptyList()

        val result = mutableListOf<MutableList<Int>>()
        levelOrderRecursive(root, result, 0)

        return result
    }

    private fun levelOrderRecursive(
        root: TreeNode,
        result: MutableList<MutableList<Int>>,
        level: Int
    ) {
        if (level + 1 > result.size) result.add(mutableListOf())
        result[level].add(root.`val`)
        root.left?.let { levelOrderRecursive(it, result, level + 1) }
        root.right?.let { levelOrderRecursive(it, result, level + 1) }
    }

    /**
     * Iterative BFS: snapshots queue size before each level to group nodes.
     *
     * Time:  O(n) every node is enqueued and dequeued exactly once.
     * Space: O(w) auxiliary for the queue, where w is the maximum tree width.
     *        O(n) worst case for a complete tree (last level holds ≈ n/2 nodes).
     *        O(n) for the output.
     */
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

    t = TreeNode(3)
    t.left = TreeNode(9)
    t.right = TreeNode(20)
    t.right?.left = TreeNode(15)
    t.right?.right = TreeNode(7)

    println(s.levelOrder(t))
    println(s.levelOrderBFS(t))
}