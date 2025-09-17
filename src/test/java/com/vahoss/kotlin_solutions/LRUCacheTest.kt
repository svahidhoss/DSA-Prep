package com.vahoss.kotlin_solutions

import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test

class LRUCacheTest {

    @Test
    fun `test basic get and put operations`() {
        val cache = LRUCache(2)

        // Test put and get
        cache.put(1, 10)
        assertEquals(10, cache.get(1))

        // Test getting non-existent key
        assertEquals(-1, cache.get(2))
    }

    @Test
    fun `test capacity limit with eviction`() {
        val cache = LRUCache(2)

        cache.put(1, 10)
        cache.put(2, 20)
        cache.put(3, 30) // Should evict key 1

        assertEquals(-1, cache.get(1)) // Key 1 should be evicted
        assertEquals(20, cache.get(2))
        assertEquals(30, cache.get(3))
    }

    @Test
    fun `test LRU eviction order`() {
        val cache = LRUCache(3)

        cache.put(1, 10)
        cache.put(2, 20)
        cache.put(3, 30)

        // Access key 1 to make it recently used
        cache.get(1)

        // Add new key, should evict key 2 (least recently used)
        cache.put(4, 40)

        assertEquals(10, cache.get(1)) // Should still exist
        assertEquals(-1, cache.get(2)) // Should be evicted
        assertEquals(30, cache.get(3)) // Should still exist
        assertEquals(40, cache.get(4)) // Should still exist
    }

    @Test
    fun `test updating existing key doesn't change capacity`() {
        val cache = LRUCache(2)

        cache.put(1, 10)
        cache.put(2, 20)
        cache.put(1, 15) // Update existing key

        assertEquals(15, cache.get(1)) // Updated value
        assertEquals(20, cache.get(2)) // Should still exist

        // Add new key, should still work within capacity
        cache.put(3, 30)
        assertEquals(-1, cache.get(1)) // Key 1 should be evicted (LRU)
    }

    @Test
    fun `test get operation updates recency`() {
        val cache = LRUCache(2)

        cache.put(1, 10)
        cache.put(2, 20)

        // Access key 1 to make it recently used
        cache.get(1)

        // Add new key, should evict key 2 (now LRU)
        cache.put(3, 30)

        assertEquals(10, cache.get(1)) // Should still exist
        assertEquals(-1, cache.get(2)) // Should be evicted
        assertEquals(30, cache.get(3)) // Should exist
    }

    @Test
    fun `test put operation updates recency for existing keys`() {
        val cache = LRUCache(2)

        cache.put(1, 10)
        cache.put(2, 20)

        // Update key 1, making it recently used
        cache.put(1, 15)

        // Add new key, should evict key 2 (now LRU)
        cache.put(3, 30)

        assertEquals(15, cache.get(1)) // Should still exist with updated value
        assertEquals(-1, cache.get(2)) // Should be evicted
        assertEquals(30, cache.get(3)) // Should exist
    }

    @Test
    fun `test single capacity cache`() {
        val cache = LRUCache(1)

        cache.put(1, 10)
        assertEquals(10, cache.get(1))

        // Adding second item should evict first
        cache.put(2, 20)
        assertEquals(-1, cache.get(1))
        assertEquals(20, cache.get(2))

        // Updating the only item
        cache.put(2, 25)
        assertEquals(25, cache.get(2))
    }

    @Test
    fun `test large capacity cache`() {
        val cache = LRUCache(1000)

        // Fill cache
        for (i in 1..500) {
            cache.put(i, i * 10)
        }

        // Verify all items exist
        for (i in 1..500) {
            assertEquals(i * 10, cache.get(i))
        }

        // Add more items within capacity
        for (i in 501..1000) {
            cache.put(i, i * 10)
        }

        // Verify all items still exist
        for (i in 1..1000) {
            assertEquals(i * 10, cache.get(i))
        }

        // Exceed capacity
        cache.put(1001, 10010)
        assertEquals(-1, cache.get(1)) // First item should be evicted
        assertEquals(10010, cache.get(1001))
    }

    @Test
    fun `test sequential access pattern`() {
        val cache = LRUCache(3)

        cache.put(1, 10)
        cache.put(2, 20)
        cache.put(3, 30)
        cache.put(4, 40) // Evicts 1
        cache.put(5, 50) // Evicts 2
        cache.put(6, 60) // Evicts 3

        assertEquals(-1, cache.get(1))
        assertEquals(-1, cache.get(2))
        assertEquals(-1, cache.get(3))
        assertEquals(40, cache.get(4))
        assertEquals(50, cache.get(5))
        assertEquals(60, cache.get(6))
    }

    @Test
    fun `test alternating access pattern`() {
        val cache = LRUCache(2)

        cache.put(1, 10)
        cache.put(2, 20)

        // Alternate accessing both keys
        cache.get(1) // 1 becomes most recent
        cache.get(2) // 2 becomes most recent
        cache.get(1) // 1 becomes most recent

        cache.put(3, 30) // Should evict 2 (LRU)

        assertEquals(10, cache.get(1))
        assertEquals(-1, cache.get(2))
        assertEquals(30, cache.get(3))
    }

    @Test
    fun `test negative keys and values`() {
        val cache = LRUCache(2)

        cache.put(-1, -10)
        cache.put(-2, -20)

        assertEquals(-10, cache.get(-1))
        assertEquals(-20, cache.get(-2))

        // Test with mix of positive and negative
        cache.put(1, -30) // Should evict -1
        assertEquals(-1, cache.get(-1))
        assertEquals(-20, cache.get(-2))
        assertEquals(-30, cache.get(1))
    }
}