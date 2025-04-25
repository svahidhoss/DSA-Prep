package com.vahoss.amazon

import com.vahoss.TreeNode

class Solution00572 {
    fun isSubtree(root: TreeNode?, subRoot: TreeNode?): Boolean {
        if (root == null) return (subRoot == null)
        // An empty tree is always a subtree
        if (subRoot == null) return true

        return if (isIdentical(root, subRoot)) true
        else isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot)
    }

    private fun isIdentical(root: TreeNode?, subRoot: TreeNode?): Boolean {
        if (root == null && subRoot == null) return true
        if (root == null || subRoot == null) return false

        return root.`val` == subRoot.`val`
                && isIdentical(root.left, subRoot.left)
                && isIdentical(root.right, subRoot.right)
    }

    fun isSubtree2(root: TreeNode?, subRoot: TreeNode?): Boolean {
        if (isIdentical(root, subRoot)) return true

        return isSubtree2(root?.left, subRoot) || isSubtree2(root?.right, subRoot)
    }

    /**
     * wrong approach:
     *
     * Incorrect approach: Using only inorder traversal is not sufficient to
     * determine if one tree is a subtree of another. Two different trees can
     * have the same inorder traversal.
     *    1          3
     *     \        /
     *      2      1
     *     /        \
     *    3          2
     * Ignoring structure: The current implementation only checks if all
     * values in the subtree exist in the main tree, without considering
     * their order or structure.
     * There's a TO DO comment acknowledging that the order needs to be considered.
     * Inefficiency: This approach creates full lists of all nodes for both trees,
     * which is unnecessary and inefficient for large trees.
     * False positives: This method will return true for cases where all values of
     * the subtree exist in the main tree, even if they're not in the correct structure.
     */
    fun isSubtreeInorder(root: TreeNode?, subRoot: TreeNode?): Boolean {
        val rootResult = mutableListOf<Int>()
        inorder(root, rootResult)
        val subRootResult = mutableListOf<Int>()
        inorder(subRoot, subRootResult)
        // tODO got to change this and consider the order as well
        return rootResult.containsAll(subRootResult)
    }

    private fun inorder(r: TreeNode?, list: MutableList<Int>) {
        if (r == null) return

        r.left?.let {
            inorder(it, list)
        }
        list.add(r.`val`)
        r.right?.let {
            inorder(it, list)
        }
    }
}

fun main() {
    val s = Solution00572()

    val tree = TreeNode(3)
    tree.left = TreeNode(4)
    tree.right = TreeNode(5)
    tree.left!!.left = TreeNode(1)
    tree.left!!.right = TreeNode(2)
    tree.left!!.right!!.left = TreeNode(0)

    val subtree = TreeNode(4)
    subtree.left = TreeNode(1)
    subtree.right = TreeNode(2)

    println(s.isSubtree(tree, subtree))
    println(s.isSubtree2(tree, subtree))

    val tree2 = TreeNode(4)
    tree2.left = TreeNode(1)
    tree2.right = TreeNode(2)

    println(s.isSubtree(tree2, subtree))
    println(s.isSubtree2(tree2, subtree))

    val tree3 = TreeNode(1)
    tree3.left = TreeNode(1)
    val subtree2 = TreeNode(1)

    println(s.isSubtree(tree3, subtree2))
    println(s.isSubtree2(tree3, subtree2))
}
