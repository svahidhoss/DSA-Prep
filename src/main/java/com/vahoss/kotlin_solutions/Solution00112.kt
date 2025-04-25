package com.vahoss.kotlin_solutions

import com.vahoss.TreeNode
import java.util.ArrayDeque
import java.util.Queue

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
class Solution00112 {
    /**
     * Time complexity : we visit each node exactly once,
     * thus the time complexity is O(N), where N is the number of nodes.
     * Space complexity : in the worst case, the tree is completely unbalanced,
     * e.g. each node has only one child node, the recursion call would occur
     * N times (the height of the tree), therefore the storage to keep the call
     * stack would be O(N). But in the best case (the tree is completely balanced),
     * the height of the tree would be log(N). Therefore, the space complexity in
     * this case would be O(log(N)).
     */
    fun hasPathSum(root: TreeNode?, targetSum: Int): Boolean {
        if (root == null) return false
        // if reached a leaf calculate and return the result
        if (root.left == null && root.right == null) return root.`val` == targetSum
        return hasPathSum(root.left, targetSum - root.`val`)
                || hasPathSum(root.right, targetSum - root.`val`)
    }

    /**
     * The algorithm will find a path sum if it exists.
     * Approach:
     * Uses BFS with a queue to traverse the tree.
     * Subtracts the target sum from the root and propagates the difference
     * down the tree. It modifies the input tree, which is generally not recommended.
     *
     * Time Complexity:
     * O(n), where n is the number of nodes in the tree. Each node is visited once.
     * Space Complexity:
     * O(w), where w is the maximum width of the tree. In the worst case,
     * this could be O(n) for a complete binary tree.
     *
     * Potential Improvements and Observations:
     * a. Modifying the values in the tree nodes, can lead to unexpected behavior
     * if the tree is used elsewhere after this function call.
     * b. Leaf node check is correctly placed inside the val == 0 condition.
     */
    fun hasPathSumBFSWithModification(root: TreeNode?, targetSum: Int): Boolean {
        root ?: return false
        // do a BFS approach
        val queue: Queue<TreeNode> = ArrayDeque()
        root.`val` -= targetSum
        queue.add(root)
        while (queue.isNotEmpty()) {
            val curr = queue.remove()
            if (curr.`val` == 0) {
                // leaf node check
                if (curr.left == null && curr.right == null) return true
            } else {
                curr.left?.let {
                    it.`val` += curr.`val`
                    queue.add(it)
                }
                curr.right?.let {
                    it.`val` += curr.`val`
                    queue.add(it)
                }
            }
        }

        return false
    }

    /**
     * This BFS algorithm, doesn't modify the input tree.
     * Uses a Pair to store both the node and the remaining sum in the queue.
     * Simplifies the overall structure of the loop.
     * Maintains the same time and space complexity.
     */
    fun hasPathSumBFS(root: TreeNode?, targetSum: Int): Boolean {
        root ?: return false
        // do a BFS approach
        val queue: Queue<Pair<TreeNode, Int>> = ArrayDeque()
        queue.add(root to targetSum)
        while (queue.isNotEmpty()) {
            val (curr, remainingSum) = queue.remove()
            val newSum = remainingSum - curr.`val`
            if (newSum == 0) {
                // leaf node check
                if (curr.left == null && curr.right == null) return true
            }
            curr.left?.let {
                queue.add(it to newSum)
            }
            curr.right?.let {
                queue.add(it to newSum)
            }
        }

        return false
    }
}

fun main() {
    val solution = Solution00112()

    val root1 = TreeNode(5)
    root1.left = TreeNode(4)
    root1.right = TreeNode(8)

    root1.left?.left = TreeNode(11)
    root1.left?.left?.left = TreeNode(7)
    root1.left?.left?.right = TreeNode(2)

    root1.right?.left = TreeNode(13)
    root1.right?.right = TreeNode(4)
    root1.right?.right?.right = TreeNode(1)
    println(solution.hasPathSum(root1, 22)) // Expected output: true
    println(solution.hasPathSumBFSWithModification(root1, 22)) // Expected output: true

    val root2 = TreeNode(1)
    root2.left = TreeNode(2)
    root2.right = TreeNode(3)
    println(solution.hasPathSum(root2, 5)) // Expected output: false
    println(solution.hasPathSumBFS(root2, 5)) // Expected output: false

    // Test Case 3: Single Node Tree
    val root3 = TreeNode(1)
    println(solution.hasPathSum(root3, 0)) // Expected output: false
    println(solution.hasPathSumBFS(root3, 0)) // Expected output: false
    println(solution.hasPathSum(root3, 1)) // Expected output: true
    println(solution.hasPathSumBFS(root3, 1)) // Expected output: true

    // Test Case 4: Empty Tree
    val root4: TreeNode? = null
    println(solution.hasPathSum(root4, 0)) // Expected output: false
    println(solution.hasPathSumBFS(root4, 0)) // Expected output: false

    // Test Case 5: Tree with negative values
    val root5 = TreeNode(-2)
    root5.right = TreeNode(-3)
    println(solution.hasPathSum(root5, -5)) // Expected output: true
    println(solution.hasPathSumBFS(root5, -5)) // Expected output: true

    // Test Case 6: Tree with negative values where no need to stop when reaching 0
    val root6 = TreeNode(1)
    root6.left = TreeNode(-2)
    root6.right = TreeNode(-3)

    root6.left?.left = TreeNode(1)
    root6.left?.right = TreeNode(3)

    root6.right?.left = TreeNode(-2)

    root6.left?.left?.left = TreeNode(-1)
    println(solution.hasPathSum(root6, -1)) // Expected output: true
    println(solution.hasPathSumBFS(root6, -1)) // Expected output: true
}
