package kotlin_solutions

import TreeNode

class Solution00104 {
    fun maxDepthIterative(root: TreeNode?): Int {
        root ?: return 0

        var maxDepth = 0
        val stack = ArrayDeque<TreeNodeDepth>()
        stack.add(TreeNodeDepth(root, 0))
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

    fun maxDepth(root: TreeNode?): Int {
        if (root == null) return 0
        return maxOf(maxDepth(root.left), maxDepth(root.right)) + 1
    }
}