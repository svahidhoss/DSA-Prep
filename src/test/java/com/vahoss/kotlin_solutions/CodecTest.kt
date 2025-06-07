package com.vahoss.kotlin_solutions

import com.vahoss.TreeNode
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CodecTest {

    @BeforeEach
    fun setUp() {
    }

    @AfterEach
    fun tearDown() {
    }

    private val codec = Codec()

    @Test
    fun testSingleNode() {
        val root = TreeNode(1)
        val serialized = codec.serialize(root)
        val deserialized = codec.deserialize(serialized)
        assertEquals(1, deserialized?.`val`)
        assertNull(deserialized?.left)
        assertNull(deserialized?.right)
    }

    @Test
    fun testBalancedTree() {
        val root = TreeNode(1).apply {
            left = TreeNode(2)
            right = TreeNode(3).apply {
                left = TreeNode(4)
                right = TreeNode(5)
            }
        }

        val serialized = codec.serialize(root)
        val deserialized = codec.deserialize(serialized)
        assertEquals(1, deserialized?.`val`)
        assertEquals(2, deserialized?.left?.`val`)
        assertEquals(3, deserialized?.right?.`val`)
        assertEquals(4, deserialized?.right?.left?.`val`)
        assertEquals(5, deserialized?.right?.right?.`val`)
    }

    @Test
    fun testLeftSkewedTree() {
        val root = TreeNode(1).apply {
            left = TreeNode(2).apply {
                left = TreeNode(3)
            }
        }

        val serialized = codec.serialize(root)
        val deserialized = codec.deserialize(serialized)
        assertEquals(1, deserialized?.`val`)
        assertEquals(2, deserialized?.left?.`val`)
        assertEquals(3, deserialized?.left?.left?.`val`)
        assertNull(deserialized?.right)
    }

    @Test
    fun testRightSkewedTree() {
        val root = TreeNode(1).apply {
            right = TreeNode(2).apply {
                right = TreeNode(3)
            }
        }

        val serialized = codec.serialize(root)
        val deserialized = codec.deserialize(serialized)
        assertEquals(1, deserialized?.`val`)
        assertEquals(2, deserialized?.right?.`val`)
        assertEquals(3, deserialized?.right?.right?.`val`)
        assertNull(deserialized?.left)
    }

    @Test
    fun testNullTree() {
        val serialized = codec.serialize(null)
        val deserialized = codec.deserialize(serialized)
        assertNull(deserialized)
    }
}
