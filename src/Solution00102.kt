import java.util.*
import kotlin.collections.ArrayList


class Solution00102 {
    fun levelOrderBFS(root: TreeNode?): List<List<Int>> {
        val result = ArrayList<ArrayList<Int>>()
        val queue = ArrayDeque<NodeWithDepth>()

        var rowList = ArrayList<Int>()

        var currentRow = 0
        root?.let {
            queue.add(NodeWithDepth(it, currentRow))
        }

        while (queue.isNotEmpty()) {
            val currentNode = queue.removeFirst()
            if (currentNode.depth != currentRow) {
                // Add the last row
                result.add(rowList)
                rowList = ArrayList()
                currentRow++
            }
            // add the current node's value to the list
            rowList.add(currentNode.node.`val`)

            currentNode.node.left?.let {
                queue.add(NodeWithDepth(it, currentNode.depth + 1))
            }
            currentNode.node.right?.let {
                queue.add(NodeWithDepth(it, currentNode.depth + 1))
            }
        }
        // Add the last row
        if (rowList.isNotEmpty()) result.add(rowList)

        return result
    }

    fun levelOrder(root: TreeNode?): List<List<Int>> {
        val result = mutableListOf<MutableList<Int>>()
        val queue: Queue<NodeWithDepth> = LinkedList()
        root?.let {
            queue.add(NodeWithDepth(it, 0))
        }

        while (queue.isNotEmpty()) {
            val nodeWithDepth = queue.poll()
            val node = nodeWithDepth.node

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

    data class NodeWithDepth(val node: TreeNode, val depth: Int)

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

fun main() {
    val s = Solution00102()
    var t = TreeNode(1)
    t.left = TreeNode(2)
    t.right = TreeNode(3)
    t.left?.left = TreeNode(4)
    t.left?.right = TreeNode(5)

    println(s.levelOrder(t))
    println(s.levelOrderBFS(t))

    t = TreeNode(3)
    t.left = TreeNode(9)
    t.right = TreeNode(20)
    t.right?.left = TreeNode(15)
    t.right?.right = TreeNode(7)

    println(s.levelOrder(t))
    println(s.levelOrderBFS(t))
}
