package com.vahoss.kotlin_solutions


import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class Solution00733Test {
    private lateinit var solution: Solution00733

    @BeforeEach
    fun setUp() {
        solution = Solution00733()
    }

    @AfterEach
    fun tearDown() {
    }

    // Helper function to create a deep copy of a 2D array
    private fun copyImage(image: Array<IntArray>): Array<IntArray> {
        return Array(image.size) { i -> image[i].copyOf() }
    }

    // Helper function to print the image for debugging
    private fun printImage(image: Array<IntArray>) {
        for (row in image) {
            println(row.joinToString(" "))
        }
        println()
    }

    @Test
    fun testBasicFloodFill() {
        // Test Case 1: Basic example from the problem description
        val image1 = arrayOf(
            intArrayOf(1, 1, 1),
            intArrayOf(1, 1, 0),
            intArrayOf(1, 0, 1)
        )
        val sr1 = 1
        val sc1 = 1
        val color1 = 2
        val expected1 = arrayOf(
            intArrayOf(2, 2, 2),
            intArrayOf(2, 2, 0),
            intArrayOf(2, 0, 1)
        )

        val result1 = solution.floodFill(copyImage(image1), sr1, sc1, color1)

        println("Original: ")
        printImage(image1)
        println("Expected: ")
        printImage(expected1)
        println("Result: ")
        printImage(result1)
        assertArrayEquals(expected1, result1)
    }

    @Test
    fun testSingleCellImage() {
        // Test Case 2: Single cell image
        val image2 = arrayOf(intArrayOf(5))
        val sr2 = 0
        val sc2 = 0
        val color2 = 7
        val expected2 = arrayOf(intArrayOf(7))


        val result2 = solution.floodFill(copyImage(image2), sr2, sc2, color2)

        assertArrayEquals(expected2, result2)
    }

    @Test
    fun testSameColorNoChange() {
        // Test Case 3: Original color is the same as target color
        val image3 = arrayOf(
            intArrayOf(3, 3, 3),
            intArrayOf(3, 3, 3),
            intArrayOf(3, 3, 3)
        )
        val sr3 = 1
        val sc3 = 1
        val color3 = 3
        val expected3 = arrayOf(
            intArrayOf(3, 3, 3),
            intArrayOf(3, 3, 3),
            intArrayOf(3, 3, 3)
        )


        val result3 = solution.floodFill(copyImage(image3), sr3, sc3, color3)

        assertArrayEquals(expected3, result3)
    }

    @Test
    fun testComplexPattern() {
        // Test Case 4: More complex pattern with islands
        val image4 = arrayOf(
            intArrayOf(1, 1, 1, 1, 1),
            intArrayOf(1, 2, 2, 2, 1),
            intArrayOf(1, 2, 3, 2, 1),
            intArrayOf(1, 2, 2, 2, 1),
            intArrayOf(1, 1, 1, 1, 1)
        )
        val sr4 = 2
        val sc4 = 2
        val color4 = 4
        val expected4 = arrayOf(
            intArrayOf(1, 1, 1, 1, 1),
            intArrayOf(1, 2, 2, 2, 1),
            intArrayOf(1, 2, 4, 2, 1),
            intArrayOf(1, 2, 2, 2, 1),
            intArrayOf(1, 1, 1, 1, 1)
        )


        val result4 = solution.floodFill(copyImage(image4), sr4, sc4, color4)

        assertArrayEquals(expected4, result4)
    }

    @Test
    fun testEdgeCell() {
        // Test Case 5: Start from edge cell
        val image5 = arrayOf(
            intArrayOf(5, 5, 5, 5),
            intArrayOf(5, 9, 9, 5),
            intArrayOf(5, 9, 9, 5),
            intArrayOf(5, 5, 5, 5)
        )
        val sr5 = 0
        val sc5 = 0
        val color5 = 8
        val expected5 = arrayOf(
            intArrayOf(8, 8, 8, 8),
            intArrayOf(8, 9, 9, 8),
            intArrayOf(8, 9, 9, 8),
            intArrayOf(8, 8, 8, 8)
        )


        val result5 = solution.floodFill(copyImage(image5), sr5, sc5, color5)

        assertArrayEquals(expected5, result5)
    }

    @Test
    fun testDisconnectedCells() {
        // Test Case 6: Fill with disconnected cells of the same color
        val image6 = arrayOf(
            intArrayOf(1, 2, 1, 2, 1),
            intArrayOf(2, 1, 2, 1, 2),
            intArrayOf(1, 2, 1, 2, 1),
            intArrayOf(2, 1, 2, 1, 2)
        )
        val sr6 = 0
        val sc6 = 0
        val color6 = 3
        val expected6 = arrayOf(
            intArrayOf(3, 2, 1, 2, 1),
            intArrayOf(2, 1, 2, 1, 2),
            intArrayOf(1, 2, 1, 2, 1),
            intArrayOf(2, 1, 2, 1, 2)
        )


        val result6 = solution.floodFill(copyImage(image6), sr6, sc6, color6)

        assertArrayEquals(expected6, result6)
    }

    @Test
    fun testLargeArea() {
        // Test Case 7: Large area to fill
        val image7 = arrayOf(
            intArrayOf(0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0)
        )
        val sr7 = 2
        val sc7 = 3
        val color7 = 7
        val expected7 = arrayOf(
            intArrayOf(7, 7, 7, 7, 7, 7, 7),
            intArrayOf(7, 7, 7, 7, 7, 7, 7),
            intArrayOf(7, 7, 7, 7, 7, 7, 7),
            intArrayOf(7, 7, 7, 7, 7, 7, 7),
            intArrayOf(7, 7, 7, 7, 7, 7, 7)
        )


        val result7 = solution.floodFill(copyImage(image7), sr7, sc7, color7)

        assertArrayEquals(expected7, result7)
    }

    @Test
    fun testIrregularShape() {
        // Test Case 8: Irregular shape
        val image8 = arrayOf(
            intArrayOf(1, 1, 1, 1, 1),
            intArrayOf(1, 2, 2, 2, 1),
            intArrayOf(1, 2, 1, 2, 1),
            intArrayOf(1, 2, 2, 2, 1),
            intArrayOf(1, 1, 1, 1, 1)
        )
        val sr8 = 1
        val sc8 = 1
        val color8 = 3
        val expected8 = arrayOf(
            intArrayOf(1, 1, 1, 1, 1),
            intArrayOf(1, 3, 3, 3, 1),
            intArrayOf(1, 3, 1, 3, 1),
            intArrayOf(1, 3, 3, 3, 1),
            intArrayOf(1, 1, 1, 1, 1)
        )


        val result8 = solution.floodFill(copyImage(image8), sr8, sc8, color8)

        assertArrayEquals(expected8, result8)
    }
}