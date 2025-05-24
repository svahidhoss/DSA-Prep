package com.vahoss.kotlin_solutions

import com.vahoss.TreeNode
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class Solution00104Test {
    private lateinit var solution: Solution00104

    @BeforeEach
    fun setUp() {
        solution = Solution00104()
    }


    @AfterEach
    fun tearDown() {
    }

    @Test
    fun testEmptyTree() {
        val root: TreeNode? = null
        assertEquals(0, solution.maxDepth(root))
        assertEquals(0, solution.maxDepth(root))
    }

    @Test
    fun testSingleNodeTree() {
        val root = TreeNode(1)
        assertEquals(1, solution.maxDepth(root))
        assertEquals(1, solution.maxDepth(root))
    }

    @Test
    fun testLeftSkewedTree() {
        val root = TreeNode(1).apply {
            left = TreeNode(2).apply {
                left = TreeNode(3).apply {
                    left = TreeNode(4)
                }
            }
        }
        assertEquals(4, solution.maxDepth(root))
        assertEquals(4, solution.maxDepth(root))
    }

    @Test
    fun testRightSkewedTree() {
        val root = TreeNode(1).apply {
            right = TreeNode(2).apply {
                right = TreeNode(3).apply {
                    right = TreeNode(4)
                }
            }
        }
        assertEquals(4, solution.maxDepth(root))
        assertEquals(4, solution.maxDepth(root))
    }

    @Test
    fun testBalancedTree() {
        val root = TreeNode(1).apply {
            left = TreeNode(2).apply {
                left = TreeNode(4)
                right = TreeNode(5)
            }
            right = TreeNode(3).apply {
                left = TreeNode(6)
                right = TreeNode(7)
            }
        }
        assertEquals(3, solution.maxDepth(root))
        assertEquals(3, solution.maxDepth(root))
    }

    @Test
    fun testUnbalancedTree() {
        val root = TreeNode(1).apply {
            left = TreeNode(2).apply {
                right = TreeNode(3).apply {
                    right = TreeNode(4)
                }
            }
        }
        assertEquals(4, solution.maxDepth(root))
        assertEquals(4, solution.maxDepth(root))
    }


}