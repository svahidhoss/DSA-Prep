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
class Solution00112 {
    /**
     * Time complexity : we visit each node exactly once,
     * thus the time complexity is O(N), where N is the number of nodes.
     * Space complexity : in the worst case, the tree is completely unbalanced,
     * e.g. each node has only one child node, the recursion call would occur
     * N times (the height of the tree), therefore the storage to keep the call
     * stack would be O(N). But in the best case (the tree is completely balanced),
     * the height of the tree would be log(N). Therefore, the space complexity in
     * this case would be O(log(N)).
     */
    fun hasPathSum(root: TreeNode?, targetSum: Int): Boolean {
        if (root == null) return false
        // if reached a leaf calculate and return the result
        if (root.left == null && root.right == null) return root.`val` == targetSum
        return hasPathSum(root.left, targetSum - root.`val`)
                || hasPathSum(root.right, targetSum - root.`val`)
    }
}

fun main() {
    val solution = Solution00112()

    val root1 = TreeNode(5)
    root1.left = TreeNode(4)
    root1.right = TreeNode(8)

    root1.left?.left = TreeNode(11)
    root1.left?.left?.left = TreeNode(7)
    root1.left?.left?.right = TreeNode(2)

    root1.right?.left = TreeNode(13)
    root1.right?.right = TreeNode(4)
    root1.right?.right?.right = TreeNode(1)
    println(solution.hasPathSum(root1, 22)) // Expected output: true

    val root2 = TreeNode(1)
    root2.left = TreeNode(2)
    root2.right = TreeNode(3)
    println(solution.hasPathSum(root2, 5)) // Expected output: false

    // Test Case 3: Single Node Tree
    val root3 = TreeNode(1)
    println(solution.hasPathSum(root3, 0)) // Expected output: true
    println(solution.hasPathSum(root3, 1)) // Expected output: true

    // Test Case 4: Empty Tree
    val root4: TreeNode? = null
    println(solution.hasPathSum(root4, 0)) // Expected output: true
}
