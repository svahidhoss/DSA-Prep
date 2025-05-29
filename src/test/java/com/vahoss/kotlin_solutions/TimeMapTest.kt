package com.vahoss.kotlin_solutions

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TimeMapTest {
    private lateinit var tm: TimeMap

    @BeforeEach
    fun setup() {
        tm = TimeMap()
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun testBasicExample() {
        tm.set("foo", "bar", 1)
        assertEquals("bar", tm.get("foo", 1))
        assertEquals("bar", tm.get("foo", 3))
        tm.set("foo", "bar2", 4)
        assertEquals("bar2", tm.get("foo", 4))
        assertEquals("bar2", tm.get("foo", 5))
    }

    @Test
    fun testQueryBeforeAnySet() {
        assertEquals("", tm.get("key", 10))
    }

    @Test
    fun testMultipleSetsSameKey() {
        tm.set("test", "a", 5)
        tm.set("test", "b", 10)
        tm.set("test", "c", 15)
        assertEquals("a", tm.get("test", 5))
        assertEquals("b", tm.get("test", 10))
        assertEquals("b", tm.get("test", 13))
        assertEquals("c", tm.get("test", 15))
        assertEquals("c", tm.get("test", 16))
    }

    @Test
    fun testMultipleKeys() {
        tm.set("x", "1", 1)
        tm.set("y", "2", 2)
        tm.set("x", "3", 3)
        assertEquals("1", tm.get("x", 2))
        assertEquals("2", tm.get("y", 2))
        assertEquals("3", tm.get("x", 3))
    }

    @Test
    fun testRepeatedGets() {
        tm.set("alpha", "start", 1)
        assertEquals("start", tm.get("alpha", 1))
        assertEquals("start", tm.get("alpha", 1))
        assertEquals("", tm.get("alpha", 0))
    }
}