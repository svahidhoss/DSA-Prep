package kotlin_solutions

import TreeNode

class Solution00700 {
    fun searchBST(root: TreeNode?, `val`: Int): TreeNode? {
        if (root == null || root.`val` == `val`) return root
        // Makes use of the BST properties:
        return if (root.`val` > `val`) {
            searchBST(root.left, `val`)
        } else {
            searchBST(root.right, `val`)
        }
    }

    fun searchBST2(root: TreeNode?, `val`: Int): TreeNode? {
        if (root == null || root.`val` == `val`) return root

        val left = searchBST(root.left, `val`)
        left?.let { return it }
        val right = searchBST(root.right, `val`)
        right?.let { return it }

        return null
    }
}

fun main() {
    val tree = TreeNode(4)
    tree.left = TreeNode(2)
    tree.right = TreeNode(7)
    tree.left!!.left = TreeNode(1)
    tree.left!!.right = TreeNode(3)

    val s = Solution00700()
    println(s.searchBST(tree, 2)?.`val`)
    println(s.searchBST(tree, 5)?.`val`)
}
