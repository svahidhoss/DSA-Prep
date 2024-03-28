package amazon

import TreeNode

class Solution00572 {
    fun isSubtree(root: TreeNode?, subRoot: TreeNode?): Boolean {
        if (isIdentical(root, subRoot)) return true

        return isSubtree(root?.left, subRoot) || isSubtree(root?.right, subRoot)
    }

    private fun isIdentical(root1: TreeNode?, root2: TreeNode?): Boolean {
        if (root1 == null && root2 == null) return true
//        if (root1 == null || root2 == null) return false
        return root1?.`val` == root2?.`val`
                && isIdentical(root1?.left, root2?.left)
                && isIdentical(root1?.right, root2?.right)
    }

    fun isSubtree3(root: TreeNode?, subRoot: TreeNode?): Boolean {
        if (root == null) return subRoot == null
        if (isSame(root, subRoot)) return true

        return (isSubtree3(root?.left, subRoot) || isSubtree3(root?.right, subRoot))
    }

    fun isSame(root: TreeNode?, subRoot: TreeNode?): Boolean {
        if (root == null && subRoot == null) return true
        if (root == null || subRoot == null) return false
        if (root.`val` == subRoot.`val`) {
            val sameLeft = isSame(root.left, subRoot.left)
            val sameRight = isSame(root.right, subRoot.right)
            return sameLeft && sameRight
        }
        return false
    }

    fun isSubtree2(root: TreeNode?, subRoot: TreeNode?): Boolean {
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

    val tree2 = TreeNode(4)
    tree2.left = TreeNode(1)
    tree2.right = TreeNode(2)

    /*val tree3 = TreeNode(4)
    tree3.left = TreeNode(1)
    tree3.right = TreeNode(2)

    println(s.isSame(tree3, tree2))*/
    println(s.isSubtree3(tree, tree2))
}