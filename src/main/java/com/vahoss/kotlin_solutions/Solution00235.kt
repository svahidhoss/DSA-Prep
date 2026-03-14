import com.vahoss.TreeNode

/**
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int = 0) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
class Solution00235 {
    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        // stop condition
        if (root == null || root == p || root == q) return root

        val left = lowestCommonAncestor(root.left, p, q)
        val right = lowestCommonAncestor(root.right, p, q)


        if (left != null && right != null) return root
        return left ?: right

        // return when {
        //     left != null && right != null -> root
        //     else -> left ?: right
        // }
    }
}

fun main() {
    val s = Solution00235()

    // Helper to build a TreeNode
    fun node(v: Int) = TreeNode(v)

    // Build tree:
    //         3
    //        / \
    //       5   1
    //      / \ / \
    //     6  2 0  8
    //       / \
    //      7   4
    val n3 = node(3)
    val n5 = node(5)
    val n1 = node(1)
    val n6 = node(6)
    val n2 = node(2)
    val n0 = node(0)
    val n8 = node(8)
    val n7 = node(7)
    val n4 = node(4)
    n3.left = n5; n3.right = n1
    n5.left = n6; n5.right = n2
    n1.left = n0; n1.right = n8
    n2.left = n7; n2.right = n4

    fun check(label: String, result: TreeNode?, expected: TreeNode?) {
        val pass = result == expected
        println("${if (pass) "✓" else "✗"} $label → got ${result?.`val`}, expected ${expected?.`val`}")
    }

    // Case 1: p and q in different subtrees → LCA is root
    check(
        "p=5, q=1 → LCA=3",
        s.lowestCommonAncestor(n3, n5, n1), n3
    )

    // Case 2: p is ancestor of q → LCA is p
    check(
        "p=5, q=4 → LCA=5",
        s.lowestCommonAncestor(n3, n5, n4), n5
    )

    // Case 3: p and q deep in same subtree
    check(
        "p=6, q=4 → LCA=5",
        s.lowestCommonAncestor(n3, n6, n4), n5
    )

    // Case 4: p and q are siblings
    check(
        "p=7, q=4 → LCA=2",
        s.lowestCommonAncestor(n3, n7, n4), n2
    )

    // Case 5: one node is root
    check(
        "p=3, q=4 → LCA=3",
        s.lowestCommonAncestor(n3, n3, n4), n3
    )

    // Case 6: p and q are same node (edge case)
    check(
        "p=5, q=5 → LCA=5",
        s.lowestCommonAncestor(n3, n5, n5), n5
    )

    // Case 7: single node tree
    val single = node(1)
    check(
        "single node, p=q=1 → LCA=1",
        s.lowestCommonAncestor(single, single, single), single
    )
}