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
    /**
     *     2
     *    / \
     *   2   2
     */
    val root6 = TreeNode(2)
    root6.left = TreeNode(2)
    root6.right = TreeNode(2)
    println(isValidBSTInOrder(root6)) // false

    /**
     *     2
     *    / \
     *   1   3
     */
    val root1 = TreeNode(2)
    root1.left = TreeNode(1)
    root1.right = TreeNode(3)
    println(isValidBSTInOrder(root1)) // true

    /**
     *     5
     *    / \
     *   1   4
     *      / \
     *     3   6
     */
    val root2 = TreeNode(5)
    root2.left = TreeNode(1)
    root2.right = TreeNode(4)
    root2.right?.left = TreeNode(3)
    root2.right?.right = TreeNode(6)
    println(isValidBSTInOrder(root2)) // false

    /**
     *     0
     *    /
     *  -1
     */
    val root3 = TreeNode(0)
    root3.left = TreeNode(-1)
    println(isValidBSTInOrder(root3))

    val root4: TreeNode? = null
    println(isValidBSTInOrder(root4)) // true

    /**
     *     5
     *    / \
     *   4   6
     *      / \
     *     3   7
     *
     */
    val root5 = TreeNode(5)
    root5.left = TreeNode(4)
    root5.right = TreeNode(6)
    root5.right?.left = TreeNode(3)
    root5.right?.right = TreeNode(7)
    println(isValidBSTInOrder(root5)) // true

}
