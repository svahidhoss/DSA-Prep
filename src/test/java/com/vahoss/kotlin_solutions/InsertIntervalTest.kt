package com.vahoss.kotlin_solutions

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

class InsertIntervalTest {
    private lateinit var solution: Solution00057

    @BeforeEach
    fun setUp() {
        solution = Solution00057()
    }

    @Test
    fun testExample1() {
        val intervals = arrayOf(intArrayOf(1, 3), intArrayOf(6, 9))
        val newInterval = intArrayOf(2, 5)
        val expected = arrayOf(intArrayOf(1, 5), intArrayOf(6, 9))
        val actual = solution.insert(intervals, newInterval)
        assertTrue(
            expected.contentDeepEquals(actual),
            "Expected: ${expected.contentDeepToString()}, Actual: ${actual.contentDeepToString()}"
        )
    }

    @Test
    fun testExample2() {
        val intervals = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(3, 5),
            intArrayOf(6, 7),
            intArrayOf(8, 10),
            intArrayOf(12, 16)
        )
        val newInterval = intArrayOf(4, 8)
        val expected = arrayOf(intArrayOf(1, 2), intArrayOf(3, 10), intArrayOf(12, 16))
        val actual = solution.insert(intervals, newInterval)
        assertTrue(
            expected.contentDeepEquals(actual),
            "Expected: ${expected.contentDeepToString()}, Actual: ${actual.contentDeepToString()}"
        )
    }

    @Test
    fun testExample3() {
        val intervals = arrayOf(intArrayOf(1, 2), intArrayOf(5, 6))
        val newInterval = intArrayOf(3, 4)
        val expected = arrayOf(intArrayOf(1, 2), intArrayOf(3, 4), intArrayOf(5, 6))
        val actual = solution.insert(intervals, newInterval)
        assertTrue(
            expected.contentDeepEquals(actual),
            "Expected: ${expected.contentDeepToString()}, Actual: ${actual.contentDeepToString()}"
        )
    }

    @Test
    fun testNoOverlapAtEnd() {
        val intervals = arrayOf(intArrayOf(1, 2))
        val newInterval = intArrayOf(3, 4)
        val expected = arrayOf(intArrayOf(1, 2), intArrayOf(3, 4))
        val actual = solution.insert(intervals, newInterval)
        assertTrue(
            expected.contentDeepEquals(actual),
            "Expected: ${expected.contentDeepToString()}, Actual: ${actual.contentDeepToString()}"
        )
    }

    @Test
    fun testNoOverlapAtBeginning() {
        val intervals = arrayOf(intArrayOf(5, 7))
        val newInterval = intArrayOf(1, 3)
        val expected = arrayOf(intArrayOf(1, 3), intArrayOf(5, 7))
        val actual = solution.insert(intervals, newInterval)
        assertTrue(
            expected.contentDeepEquals(actual),
            "Expected: ${expected.contentDeepToString()}, Actual: ${actual.contentDeepToString()}"
        )
    }

    @Test
    fun testFullMerge() {
        val intervals = arrayOf(intArrayOf(2, 3), intArrayOf(5, 7), intArrayOf(8, 10))
        val newInterval = intArrayOf(1, 11)
        val expected = arrayOf(intArrayOf(1, 11))
        val actual = solution.insert(intervals, newInterval)
        assertTrue(
            expected.contentDeepEquals(actual),
            "Expected: ${expected.contentDeepToString()}, Actual: ${actual.contentDeepToString()}"
        )
    }

    @Test
    fun testEmptyIntervals() {
        val intervals = emptyArray<IntArray>()
        val newInterval = intArrayOf(4, 8)
        val expected = arrayOf(intArrayOf(4, 8))
        val actual = solution.insert(intervals, newInterval)
        assertTrue(
            expected.contentDeepEquals(actual),
            "Expected: ${expected.contentDeepToString()}, Actual: ${actual.contentDeepToString()}"
        )
    }
}
