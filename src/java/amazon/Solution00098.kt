package java.amazon

import java.TreeNode

class Solution00098 {
    private var preVal: Int? = null

    fun isValidBST(root: TreeNode?): Boolean {
        // Let's do an inorder traversal in which we make sure the values are
        // increasing
        return inorder(root)
    }

    private fun inorder(root: TreeNode?): Boolean {
        if (root == null) return true

        // Traverse the left subtree
        if (!inorder(root.left)) return false

        // Check the current node
        preVal?.let {
            if (root.`val` <= it) return false
        }
        preVal = root.`val`

        // Traverse the right subtree and check its validity
        return inorder(root.right)
    }
}

fun main() {
    var tree = TreeNode(5)
    tree.left = TreeNode(1)
    tree.right = TreeNode(6)
//    tree.left!!.left = TreeNode(1)
//    tree.left!!.right = TreeNode(3)

    tree.right!!.left = TreeNode(4)
    tree.right!!.right = TreeNode(7)

    val sol = Solution00098()
    println(sol.isValidBST(tree))

    tree = TreeNode(2)
    tree.left = TreeNode(2)
    tree.right = TreeNode(2)
    println(sol.isValidBST(tree))

    tree = TreeNode(5)
    tree.left = TreeNode(1)
    tree.right = TreeNode(4)
//    tree.left!!.left = TreeNode(1)
//    tree.left!!.right = TreeNode(3)

    tree.right!!.left = TreeNode(3)
    tree.right!!.right = TreeNode(6)
    println(sol.isValidBST(tree))
}
