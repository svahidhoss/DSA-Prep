import kotlin.math.*

/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
class Solution00110 {
    fun isBalanced(root: TreeNode?): Boolean {
        return getHeightAndBalance(root) != -1
    }

    private fun getHeightAndBalance(root: TreeNode?): Int {
        if (root == null) return 0
        val left = getHeightAndBalance(root.left)
        if (left == -1) return -1

        val right = getHeightAndBalance(root.right)
        if (right == -1) return -1

        return if (abs(left - right) <= 1) (1 + max(left, right))
        else -1
    }
}

fun main() {
    val solution = Solution00110()

    // Test Case 1: Balanced Tree
    val root1 = TreeNode(1)
    root1.left = TreeNode(2)
    root1.right = TreeNode(3)
    root1.left?.left = TreeNode(4)
    root1.left?.right = TreeNode(5)
    root1.right?.right = TreeNode(6)
    root1.left?.left?.left = TreeNode(7)
    println(solution.isBalanced(root1)) // Expected output: true

    // Test Case 2: Unbalanced Tree
    val root2 = TreeNode(1)
    root2.left = TreeNode(2)
    root2.left?.left = TreeNode(3)
    root2.left?.left?.left = TreeNode(4)
    println(solution.isBalanced(root2)) // Expected output: false

    // Test Case 3: Single Node Tree
    val root3 = TreeNode(1)
    println(solution.isBalanced(root3)) // Expected output: true

    // Test Case 4: Empty Tree
    val root4: TreeNode? = null
    println(solution.isBalanced(root4)) // Expected output: true

    // Test Case 5: Complex Balanced Tree
    val root5 = TreeNode(1)
    root5.left = TreeNode(2)
    root5.right = TreeNode(2)
    root5.left?.left = TreeNode(3)
    root5.right?.right = TreeNode(3)
    root5.left?.left?.left = TreeNode(4)
    root5.right?.right?.right = TreeNode(4)
    println(solution.isBalanced(root5)) // Expected output: false
}
