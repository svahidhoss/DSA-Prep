class Solution00098 {
    private var prev: Int? = null

    /**
     * Time and Space Complexity
     * Time Complexity: O(n) where n is the number of nodes in the tree.
     * Each node is visited exactly once.
     *
     * Space Complexity: O(h) where h is the height of the tree.
     * This is due to the recursion stack. In the worst case (for a skewed tree),
     * the space complexity is O(n). For a balanced tree, it is O(log n).
     */
    fun isValidBST(root: TreeNode?): Boolean {
        if (root == null) return true
        return inOrder(root)
    }

    private fun inOrder(root: TreeNode): Boolean {
        // Traverse the left subtree
        root.left?.let {
            if (!inOrder(it)) return false
        }

        // Check the current node
        if (prev != null && root.`val` <= prev!!) return false
        prev = root.`val`

        // Traverse the right subtree
        root.right?.let {
            if (!inOrder(it)) return false
        }

        return true
    }
}

fun isValidBSTInOrder(root: TreeNode?): Boolean {
    val values = mutableListOf<Int>()
    inOrder(root, values)
    // check if the order is correct
    for (i in 1 until values.size) {
        if (values[i] <= values[i - 1]) return false
    }
    return true
}

fun inOrder(root: TreeNode?, values: MutableList<Int>) {
    if (root == null) return
    root.left?.let {
        inOrder(it, values)
    }
    values.add(root.`val`)
    root.right?.let {
        inOrder(it, values)
    }
}

fun isValidBSTWrong(root: TreeNode?): Boolean {
    if (root == null || (root.left == null && root.right == null)) return true

    var left = true
    root.left?.let {
        left = (root.`val` > it.`val`) && isValidBSTInOrder(it)
    }
    var right = true
    root.right?.let {
        right = (root.`val` < it.`val`) && isValidBSTInOrder(it)
    }

    return left && right
}

fun main() {
    val s = Solution00098()

    /**
     *     2
     *    / \
     *   1   3
     */
    var root = TreeNode(2)
    root.left = TreeNode(1)
    root.right = TreeNode(3)
    println("${s.isValidBST(root)} expecting true") // true

    /**
     *     5
     *    / \
     *   1   4
     *      / \
     *     3   6
     */
    root = TreeNode(5)
    root.left = TreeNode(1)
    root.right = TreeNode(4)
    root.right?.left = TreeNode(3)
    root.right?.right = TreeNode(6)
    println("${s.isValidBST(root)} expecting false") // false

    /**
     *     0
     *    /
     *  -1
     */
    root = TreeNode(0)
    root.left = TreeNode(-1)
    println("${s.isValidBST(root)} expecting true") // true


    println("${s.isValidBST(null)} expecting true") // true

    /**
     *     5
     *    / \
     *   4   6
     *      / \
     *     3   7
     *
     */
    root = TreeNode(5)
    root.left = TreeNode(4)
    root.right = TreeNode(6)
    root.right?.left = TreeNode(3)
    root.right?.right = TreeNode(7)
    println("${s.isValidBST(root)} expecting false") // false


    /**
     *     2
     *    / \
     *   2   2
     */
    root = TreeNode(2)
    root.left = TreeNode(2)
    root.right = TreeNode(2)
    println("${s.isValidBST(root)} expecting false") // false
}
