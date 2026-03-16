import com.vahoss.TreeNode

/**
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int = 0) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
class Solution00235 {
    /**
     * |---|---|---|---|---|---| Recursive | Iterative (parent map) |
     *
     * | **Time**               | O(n) | O(n) |
     * | **Space — best case**  | O(log n) | O(n) |
     * | **Space — worst case** | O(n) | O(n) |
     * | **Space — average**    | O(log n) | O(n) |
     *
     *
     * The time complexity is identical — both visit each node at most once.
     *
     * Space is where they diverge. The recursive solution's space is
     * bounded by the **call stack depth** which equals tree height — O(log n)
     * for a balanced tree, O(n) for a degenerate (linked-list shaped) tree.
     *
     * The iterative solution always pays O(n) space because the
     * `parentMap` stores an entry for every node visited regardless
     * of tree shape. The stack itself adds O(n) in the worst case too.
     * ```
     * Balanced tree (h = log n):
     *   Recursive → O(log n) stack frames      ✓ winner
     *   Iterative → O(n) parentMap entries
     *
     * Skewed tree (h = n):
     *   Recursive → O(n) stack frames (risk: StackOverflow for very large n)
     *   Iterative → O(n) parentMap entries     ✓ winner (no stack overflow risk)
     *
     */
    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        // stop condition
        if (root == null || root == p || root == q) return root

        val left = lowestCommonAncestor(root.left, p, q)
        val right = lowestCommonAncestor(root.right, p, q)

        // LCA must be post-order: you can't decide at the current node
        // until you know what both children found.
        // Pre-order would mean deciding before you have that information
        if (left != null && right != null) return root
        return left ?: right

        // return when {
        //     left != null && right != null -> root
        //     else -> left ?: right
        // }
    }

    /**
     * The iterative approach trades average-case space efficiency for
     * stack overflow safety. For very deep trees (n = 100,000 in a skewed case),
     * the recursive solution can blow the call stack — the iterative
     * solution won't. That's the practical reason to prefer iterative
     * in production, even though it looks worse on paper.
     */
    fun lowestCommonAncestor0(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        if (root == p && root == q) return root

        val stack = ArrayDeque<TreeNode>()
        val parentMap = mutableMapOf<TreeNode, TreeNode>()
        root?.let { stack.addLast(it) }

        while (stack.isNotEmpty() && !(parentMap.containsKey(p) && parentMap.containsKey(q))) {
            val node = stack.removeLast()
            println("node ${node.`val`} popped")

            node.left?.let {
                parentMap[it] = node
                stack.addLast(it)
                println("node ${it.`val`} pushed")
            }
            node.right?.let {
                parentMap[it] = node
                stack.addLast(it)
                println("node ${it.`val`} pushed")
            }
        }

        val pAncestors = mutableSetOf<TreeNode>()
        var cur = p
        while (cur != null) {
            println("p parent is ${cur.`val`}")
            pAncestors.add(cur)

            // keep going up
            cur = parentMap[cur]
            // break quickly here
            if (cur == q) return cur
        }

        cur = q
        while (cur != null) {
            println("q parent is ${cur.`val`}")
            if (pAncestors.contains(cur)) return cur
            // keep going up
            cur = parentMap[cur]
        }

        throw IllegalStateException("p and q must exist in the tree")
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
        println("********* ${if (pass) "✓" else "✗"} $label → got ${result?.`val`}, expected ${expected?.`val`}")
    }

    // Case 1: p and q in different subtrees → LCA is root
//    check(
//        "p=5, q=1 → LCA=3",
//        s.lowestCommonAncestor(n3, n5, n1), n3
//    )

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