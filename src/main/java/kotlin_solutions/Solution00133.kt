package main.java.kotlin_solutions

import main.java.GraphNode
import java.util.*

/**
 * Definition for a Node.
 * class Node(var `val`: Int) {
 *     var neighbors: ArrayList<Node?> = ArrayList<Node?>()
 * }
 */
fun cloneGraph(node: GraphNode?): GraphNode? {
    if (node == null) return null

    val map = mutableMapOf<GraphNode, GraphNode>()
    val queue = ArrayDeque<GraphNode>()

    // handle the root node
    val result = GraphNode(node.`val`)
    map[node] = result
    queue.offer(node)

    while (queue.isNotEmpty()) {
        val polledNode = queue.poll()

        polledNode?.neighbors?.forEach { neighbor ->
            // if map does not contain it, add it to the queue
            if (neighbor !in map) {
                // Create a new node for unseen neighbors
                val newNode = GraphNode(neighbor!!.`val`)
                map[neighbor] = newNode
                queue.offer(neighbor)
            }
            // Add the cloned neighbor to the current cloned node's neighbors
            map[polledNode]?.neighbors?.add(map[neighbor])
        }
    }

    return result
}

fun main() {
    println("Running graph clone tests...")

    // Test 1: Empty Graph
    println("\nTest 1: Empty Graph")
    val result1 = cloneGraph(null)
    println("Result: ${if (result1 == null) "PASS" else "FAIL"}")

    // Test 2: Single Node Graph
    println("\nTest 2: Single Node Graph")
    val node = GraphNode(1)
    val result2 = cloneGraph(node)
    println("Result: ${if (result2?.`val` == 1 && result2.neighbors.isEmpty()) "PASS" else "FAIL"}")

    // Test 3: Two Node Graph
    println("\nTest 3: Two GraphNode Graph")
    val node1 = GraphNode(1)
    val node2 = GraphNode(2)
    node1.neighbors.add(node2)
    node2.neighbors.add(node1)
    val result3 = cloneGraph(node1)
    println(
        "Result: ${
            if (result3?.`val` == 1 &&
                result3.neighbors.size == 1 &&
                result3.neighbors[0]?.`val` == 2 &&
                result3.neighbors[0]!!.neighbors[0] == result3
            ) "PASS" else "FAIL"
        }"
    )

    // Test 4: Cyclic Graph
    println("\nTest 4: Cyclic Graph")
    val node3 = GraphNode(3)
    node1.neighbors.add(node3)
    node2.neighbors.add(node3)
    node3.neighbors.addAll(listOf(node1, node2))
    val result4 = cloneGraph(node1)
    println("Result: ${
        if (result4?.`val` == 1 &&
            result4.neighbors.size == 2 &&
            result4.neighbors.any { it?.`val` == 2 } &&
            result4.neighbors.any { it?.`val` == 3 } &&
            result4.neighbors.all { it?.neighbors?.size == 2 } &&
            result4.neighbors.all { it?.neighbors!!.contains(result4) }
        ) "PASS" else "FAIL"
    }"
    )

    println("\nAll tests completed.")
}
