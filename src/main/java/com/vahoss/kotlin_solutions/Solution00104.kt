package com.vahoss.kotlin_solutions

import com.vahoss.TreeNode

class Solution00104 {

    /**
     * Iterative DFS using an explicit stack, tracking depth alongside each node.
     * Time: O(n) — visits every node once
     * Space: O(h) — stack depth equals tree height; O(log n) balanced, O(n) skewed
     */
    fun maxDepthIterative(root: TreeNode?): Int {
        root ?: return 0

        var maxDepth = 0
        val stack = ArrayDeque<TreeNodeDepth>()
        stack.add(TreeNodeDepth(root, 1))
        while (stack.isNotEmpty()) {
            val node = stack.removeLast()
            maxDepth = maxOf(maxDepth, node.depth)
            node.tn.left?.let {
                stack.add(TreeNodeDepth(it, node.depth + 1))
            }
            node.tn.right?.let {
                stack.add(TreeNodeDepth(it, node.depth + 1))
            }
        }

        return maxDepth
    }

    data class TreeNodeDepth(val tn: TreeNode, val depth: Int)

    /**
     * Recursive DFS — depth is 1 + max depth of left and right subtrees.
     * Time: O(n) — visits every node once
     * Space: O(h) — call stack depth equals tree height; O(log n) balanced, O(n) skewed
     */
    fun maxDepth(root: TreeNode?): Int {
        root ?: return 0

        return maxOf(maxDepth(root.left), maxDepth(root.right)) + 1
    }

    /**
     * BFS level-order traversal — counts levels until the queue is empty.
     * Time: O(n) — visits every node once
     * Space: O(w) — queue holds one level at a time; O(n) for a balanced tree (last level ~n/2 nodes)
     *
     * (w is the maximum width of the tree — the number of nodes in the widest level)
     */
    fun maxDepthBFS(root: TreeNode?): Int {
        root ?: return 0

        var maxDepth = 0
        val queue = ArrayDeque<TreeNode>()
        queue.add(root)
        while (queue.isNotEmpty()) {
            repeat(queue.size) {
                val node = queue.removeFirst()
                node.left?.let { queue.add(it) }
                node.right?.let { queue.add(it) }
            }
            maxDepth++
        }

        return maxDepth
    }
}
