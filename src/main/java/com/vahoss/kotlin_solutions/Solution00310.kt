package com.vahoss.kotlin_solutions

class Solution00310 {
    fun findMinHeightTrees(n: Int, edges: Array<IntArray>): List<Int> {
        if (n == 1) return listOf(0)

        val degree = IntArray(n)
        val graph = mutableMapOf<Int, MutableList<Int>>()

        for ((a, b) in edges) {
            degree[a]++
            degree[b]++
            graph.getOrPut(a) { mutableListOf() }.add(b)
            graph.getOrPut(b) { mutableListOf() }.add(a)
        }

        // Get the leaves
        var leaves = degree.indices.filter { degree[it] == 1 }

        // we’re really looking for the center(s) of the tree
        var remaining = n
        // run the bfs until the last 1 or 2 nodes remains after removing all the outer layers
        while (remaining > 2) {
            remaining -= leaves.size
            val newLeaves = mutableListOf<Int>()
            for(node in leaves) {
                // visit all the parents if not already visited
                for (parent in graph[node]!!) {
                    // remove the parent connection to current node
                    degree[parent]--
                    // if it's become leave now
                    if(degree[parent] == 1) {
                        newLeaves.add(parent)
                    }
                }
            }
            leaves = newLeaves
        }

        // return the final leaves
        return leaves
    }
}

fun main() {
    val s = Solution00310() // or however you're accessing your findMinHeightTrees function

    val testCases = listOf(
        Triple(
            1,
            arrayOf(),
            listOf(0)
        ),
        Triple(
            4, arrayOf(
                intArrayOf(1, 0),
                intArrayOf(1, 2),
                intArrayOf(1, 3)
            ), listOf(1)
        ),

        Triple(
            6, arrayOf(
                intArrayOf(0, 1),
                intArrayOf(0, 2),
                intArrayOf(0, 3),
                intArrayOf(3, 4),
                intArrayOf(4, 5)
            ), listOf(3)
        ),


        Triple(
            5, arrayOf(
                intArrayOf(0, 1),
                intArrayOf(1, 2),
                intArrayOf(2, 3),
                intArrayOf(3, 4)
            ), listOf(2)
        )
    )

    for ((i, test) in testCases.withIndex()) {
        val (n, edges, expected) = test
        val result = s.findMinHeightTrees(n, edges).sorted()
        val expectedSorted = expected.sorted()
        println("Test ${i + 1}: ${if (result == expectedSorted) "✅ Passed" else "❌ Failed"} (Expected: $expectedSorted, Got: $result)")
    }
}
