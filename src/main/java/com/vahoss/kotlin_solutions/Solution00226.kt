package com.vahoss.kotlin_solutions

import com.vahoss.TreeNode
import java.util.ArrayDeque
import java.util.Queue

class Solution00226 {
    fun invertTreeNew(root: TreeNode?): TreeNode? {
        val queue: Queue<TreeNode> = ArrayDeque()
        root?.let { queue.add(it) }
        // Use a BFS approach
        while (queue.isNotEmpty()) {
            val currentNode = queue.poll()
            // swap the right node with the left node
            val temp = currentNode.left
            currentNode.left = currentNode.right
            currentNode.right = temp

            currentNode.left?.let { queue.add(it) }
            currentNode.right?.let { queue.add(it) }
        }
        return root
    }

    fun invertTree(root: TreeNode?): TreeNode? {
        val queue = ArrayDeque<TreeNode>()
        root?.let { queue.add(it) }
        while (queue.isNotEmpty()) {
            val node = queue.removeFirst()
            val left = node.left
            val right = node.right
            node.left = right
            right?.let {
                queue.add(it)
            }
            node.right = left
            left?.let {
                queue.add(it)
            }
        }

        return root
    }

    /**
     * This implementation of invertTree() uses a DFS
     * (Depth-First Search) approach, specifically a post-order traversal:
     *
     * 1.Recursive Stack Usage: The code uses the call stack through
     * recursion to traverse the tree, which is characteristic of
     * DFS implementations.
     * 2.Complete Subtree Processing: Before returning to the parent node,
     * the function completely processes the left and right subtrees.
     * 3.Post-Order Traversal Pattern: The code follows a post-order traversal pattern:
     * -First recursively process the right subtree: invertTree(root.right)
     * -Then recursively process the left subtree: invertTree(temp)
     * (where temp holds the original left subtree)
     * -Finally process the current node (swapping the children)
     *
     * Time and space complexity:
     * -The algorithm has O(n) time complexity where
     * n is the number of nodes.
     * -It uses O(h) space complexity for the recursion stack,
     * where h is the height of the tree.
     */
    fun invertTreeRecursive(root: TreeNode?): TreeNode? {
        if (root == null) return null

        val newLeft = invertTreeRecursive(root.left)
        val newRight = invertTreeRecursive(root.right)

        root.left = newRight
        root.right = newLeft
        return root
    }
}

fun main() {
    var tree = TreeNode(4)
    tree.left = TreeNode(2)
    tree.right = TreeNode(7)
    tree.left!!.left = TreeNode(1)
    tree.left!!.right = TreeNode(3)

    tree.right!!.left = TreeNode(6)
    tree.right!!.right = TreeNode(9)
    println(tree)

    val sol = Solution00226()
    var result = sol.invertTree(tree)

    println(result)
    println(sol.invertTreeRecursive(tree))

    tree = TreeNode(2)
    tree.left = TreeNode(1)
    tree.right = TreeNode(3)
    result = sol.invertTree(tree)
    println(result)
    println(sol.invertTreeRecursive(tree))

    tree = TreeNode(1)
    tree.left = TreeNode(2)
    result = sol.invertTree(tree)
    println(result)
    println(sol.invertTreeRecursive(tree))
}