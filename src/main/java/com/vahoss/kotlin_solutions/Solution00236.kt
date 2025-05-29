package com.vahoss.kotlin_solutions

import com.vahoss.Node
import com.vahoss.TreeNode
import java.lang.IllegalStateException

class Solution00236 {
    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        if (root == null || root == q || root == p)
            return root

        val lLCA = lowestCommonAncestor(root.left, p, q)
        val rLCA = lowestCommonAncestor(root.right, p, q)

        if ((lLCA == p && rLCA == q) || (lLCA == q && rLCA == p)) return root

        lLCA?.let {
            return it
        }
        rLCA?.let {
            return it
        }
        return null
    }

    fun lowestCommonAncestorIterative(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        // stack for traversal
        val stack = ArrayDeque<TreeNode>()
        // keeping all parents
        val parentsMap = mutableMapOf<TreeNode, TreeNode?>()

        root?.let {
            stack.add(it)
            parentsMap.put(it, null)
        }

        while (!(parentsMap.containsKey(p) && parentsMap.containsKey(q))) {
            val node = stack.removeLast()
            node.left?.let {
                stack.add(it)
                parentsMap.put(it, node)
            }
            node.right?.let {
                stack.add(it)
                parentsMap.put(it, node)
            }
        }

        // we need to add all ancestors of node p
        val pAncestors = mutableSetOf<TreeNode>()
        var pNode = p
        while (pNode != null) {
            pAncestors.add(pNode)
            pNode = parentsMap[pNode]
        }

        // find the first common one with q ancestors
        var qNode = q
        while (!pAncestors.contains(qNode)) {
            qNode = parentsMap[qNode]
        }

        return qNode
    }
}

class Solution01650 {
    fun lowestCommonAncestorRec(p: Node?, q: Node?): Node? {
        // Find the root
        var root = p
        while(root?.parent != null) {
            root = root.parent
        }

        return lca(p, q, root)
    }

    fun lca(p: Node?, q: Node?, r: Node?): Node? {
        if(r == null || r == p || r == q) return r

        val left = lca(p, q, r.left)
        val right = lca(p, q, r.right)

        // if both sides are not null, return the root!
        if(left != null && right != null) return r
        // what ever side that is not null contains the lca
        left?.let{ return it }
        right?.let{ return it }

        return null
    }

    fun lowestCommonAncestor(p: Node?, q: Node?): Node? {
        val set = mutableSetOf<Int>()
        var nP = p
        var nQ = q
        while (nP != null) {
            set.add(nP.`val`)
            nP = nP.parent
        }

        while (nQ != null) {
            if (set.contains(nQ.`val`)) return nQ
            nQ = nQ.parent
        }

        throw IllegalStateException("No result were found!")
    }
}


fun main() {
    val tree = TreeNode(3)
    tree.left = TreeNode(5)
    tree.right = TreeNode(1)
    tree.left!!.left = TreeNode(6)
    tree.left!!.right = TreeNode(2)
    tree.left!!.right!!.left = TreeNode(7)
    tree.left!!.right!!.right = TreeNode(4)

    tree.right!!.left = TreeNode(0)
    tree.right!!.right = TreeNode(8)
    val sol = Solution00236()
    println(sol.lowestCommonAncestor(tree, tree.left, tree.left!!.right!!.right)?.`val`)
}