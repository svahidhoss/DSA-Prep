import java.util.*


class Solution0102 {
    fun levelOrder(root: TreeNode?): List<List<Int>> {
        val result = mutableListOf<MutableList<Int>>()
        val queue: Queue<NodeWithDepth> = LinkedList()
        root?.let {
            queue.add(NodeWithDepth(it, 0))
        }

        while (queue.isNotEmpty()) {
            val nodeWithDepth = queue.poll()
            val node = nodeWithDepth.treeNode

            if (result.size != nodeWithDepth.depth + 1) result.add(mutableListOf())
            result[nodeWithDepth.depth].add(node.`val`)

            node.left?.let {
                queue.add(NodeWithDepth(it, nodeWithDepth.depth + 1))
            }
            node.right?.let {
                queue.add(NodeWithDepth(it, nodeWithDepth.depth + 1))
            }
        }

        return result
    }

    data class NodeWithDepth(val treeNode: TreeNode, val depth: Int)

    fun levelOrderDFS(root: TreeNode?): List<List<Int>>? {
        val result = mutableListOf<MutableList<Int>>()
        root?.let { levelHelper(result, it, 0) }
        return result
    }

    private fun levelHelper(result: MutableList<MutableList<Int>>, node: TreeNode, d: Int) {
        while (result.size < d + 1) result.add(mutableListOf())
        result[d].add(node.`val`)

        node.left?.let { levelHelper(result, it, d + 1) }
        node.right?.let { levelHelper(result, it, d + 1) }
    }
}