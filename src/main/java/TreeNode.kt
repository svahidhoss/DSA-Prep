package main.java

import kotlin.math.max


/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 */
class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

/**
 * Definition for a Node with a parent.
 */
class Node(var `val`: Int) {
    var left: Node? = null
    var right: Node? = null
    var parent: Node? = null
}

/**
 * Definition for a graph node.
 */
class GraphNode(var `val`: Int) {
    var neighbors: ArrayList<GraphNode?> = ArrayList<GraphNode?>()
}


fun isFull(tree: TreeNode?): Boolean {
    if (tree == null) return true
    if ((tree.left != null && tree.right == null) || (tree.left == null && tree.right != null)) return false
    return isFull(tree.left) && isFull(tree.right)
}

fun isPerfect(root: TreeNode?): Boolean {
    // TODO implemet it recursively
//    val d = depth(root)
    return isPerfect(root, depth(root), 0)
}

/**
 * Check if the tree is perfect binary tree
 */
fun isPerfect(root: TreeNode?, d: Int, level: Int): Boolean {
    // Check if the tree is empty
    if (root == null) return true

    // If for children
    if (root.left == null && root.right == null) return d == level + 1
    return if (root.left == null || root.right == null) false
    else isPerfect(root.left, d, level + 1)
            && isPerfect(
        root.right, d, level + 1
    )
}


fun height(tree: TreeNode?): Int {
    tree?.let {
        return max(height(tree.left), height(tree.right)) + 1
    }
    return 0
}

// Calculate the depth
fun depth(node: TreeNode?): Int {
    var n = node
    var d = 0
    while (n != null) {
        d++
        n = n.left
    }
    return d
}

fun main() {
    val tree = TreeNode(1)
    tree.left = TreeNode(2)
    tree.right = TreeNode(3)
    tree.left!!.left = TreeNode(4)
    tree.left!!.right = TreeNode(5)
    tree.right!!.left = TreeNode(6)
    tree.right!!.right = TreeNode(7)
    println(isFull(tree))
    println(isPerfect(tree))

    tree.left!!.left!!.left = TreeNode(8)
    tree.left!!.left!!.right = TreeNode(9)
    println(isFull(tree))
    println(isPerfect(tree))
}
