package com.vahoss.kotlin_solutions

import com.vahoss.TreeNode
import com.vahoss.createListNodeFromArray

/**
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */

class Codec() {
    // Encodes a URL to a shortened URL.
    fun serialize(root: TreeNode?): String {
        // BFS for root
        val queue = ArrayDeque<TreeNode?>()
        val bfsList = mutableListOf<TreeNode?>()
        queue.addLast(root)

        while (queue.isNotEmpty()) {
            val node = queue.removeFirst()
            bfsList.add(node)
            if (node != null) {
                queue.addLast(node.left)
                queue.addLast(node.right)
            }
        }
        // trim the last null elements:
        while (bfsList.isNotEmpty() && bfsList.last() == null) {
            bfsList.removeLast()
        }

        // come up with the final string
        val result = StringBuilder("[")
        bfsList.forEach {
            result.append(it?.`val`)
            result.append(",")
        }
        // only trim the last , if the result is not empty
        if (result.length > 1) result.deleteCharAt(result.length - 1)
        result.append("]")
        return result.toString()
    }

    // Decodes your encoded data to tree.
    fun deserialize(data: String): TreeNode? {
        val nData = data.removeSurrounding("[", "]")
        if (nData.isEmpty()) return null
        val nodeValues = nData.split(",")

        val root = TreeNode(nodeValues.first().toInt())
        val queue = ArrayDeque<TreeNode?>()
        queue.add(root)
        var i = 0
        while (queue.isNotEmpty() && i < nodeValues.size) {
            val node = queue.removeFirst() ?: continue

            if (++i >= nodeValues.size) break
            val left = if (nodeValues[i] == "null") null else TreeNode(nodeValues[i].toInt())
            node.left = left
            queue.add(left)

            if (++i >= nodeValues.size) break
            val right = if (nodeValues[i] == "null") null else TreeNode(nodeValues[i].toInt())
            node.right = right
            queue.add(right)
        }
        return root
    }
}

/**
 * Your Codec object will be instantiated and called as such:
 * var ser = Codec()
 * var deser = Codec()
 * var data = ser.serialize(longUrl)
 * var ans = deser.deserialize(data)
 */

fun main() {
    val sol = Solution0237()
    val l = createListNodeFromArray(intArrayOf(4, 5, 1, 9))

    sol.deleteNode(l?.next)
}
