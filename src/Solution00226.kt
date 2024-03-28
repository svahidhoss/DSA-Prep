class Solution00226 {
    fun invertTree(root: TreeNode?): TreeNode? {
        val queue = ArrayDeque<TreeNode>()
        root?.let { queue.add(it) }
        while (queue.isNotEmpty()) {
            val node = queue.removeFirst()
            val left = node.left
            val right = node.right
            node.left = right
            right?.let {
                queue.add(it)
            }
            node.right = left
            left?.let {
                queue.add(it)
            }
        }

        return root
    }

    fun invertTreeRecursive(root: TreeNode?): TreeNode? {
        if (root == null) return null
//        if (root == null || (root.left == null && root.right == null)) return root

        val invertedLeft = invertTreeRecursive(root.left)
        val invertedRight = invertTreeRecursive(root.right)
        root.left = invertedRight
        root.right = invertedLeft
        return root
    }
}

fun main() {
    var tree = TreeNode(4)
    tree.left = TreeNode(2)
    tree.right = TreeNode(7)
    tree.left!!.left = TreeNode(1)
    tree.left!!.right = TreeNode(3)

    tree.right!!.left = TreeNode(6)
    tree.right!!.right = TreeNode(9)

    val sol = Solution00226()
    var result = sol.invertTree(tree)
    println(result)

    tree = TreeNode(2)
    tree.left = TreeNode(1)
    tree.right = TreeNode(3)
    result = sol.invertTree(tree)
    println(result)

    tree = TreeNode(1)
    tree.left = TreeNode(2)
    result = sol.invertTree(tree)
    println(result)
}