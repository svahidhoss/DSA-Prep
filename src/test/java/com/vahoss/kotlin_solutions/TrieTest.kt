package com.vahoss.kotlin_solutions

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class TrieTest {

    private lateinit var trie: Trie

    @BeforeEach
    fun setUp() {
        trie = Trie()
    }

    @Nested
    @DisplayName("Basic Insert and Search Operations")
    inner class BasicOperations {

        @Test
        @DisplayName("Insert and search single word")
        fun testSingleWord() {
            trie.insert("hello")

            assertTrue(trie.search("hello"))
            assertFalse(trie.search("hell"))
            assertFalse(trie.search("helloworld"))
        }

        @Test
        @DisplayName("Insert multiple unrelated words")
        fun testMultipleUnrelatedWords() {
            trie.insert("cat")
            trie.insert("dog")
            trie.insert("bird")

            assertTrue(trie.search("cat"))
            assertTrue(trie.search("dog"))
            assertTrue(trie.search("bird"))
            assertFalse(trie.search("fish"))
        }

        @Test
        @DisplayName("Insert words with shared prefixes")
        fun testSharedPrefixes() {
            trie.insert("car")
            trie.insert("card")
            trie.insert("care")
            trie.insert("careful")

            assertTrue(trie.search("car"))
            assertTrue(trie.search("card"))
            assertTrue(trie.search("care"))
            assertTrue(trie.search("careful"))
            assertFalse(trie.search("ca"))
            assertFalse(trie.search("caring"))
        }

        @Test
        @DisplayName("Insert same word multiple times")
        fun testDuplicateInsert() {
            trie.insert("test")
            trie.insert("test")
            trie.insert("test")

            assertTrue(trie.search("test"))
            // Should still work correctly with duplicates
        }

        @Test
        @DisplayName("Search non-existent words")
        fun testSearchNonExistent() {
            trie.insert("apple")

            assertFalse(trie.search(""))
            assertFalse(trie.search("app"))
            assertFalse(trie.search("application"))
            assertFalse(trie.search("orange"))
        }
    }

    @Nested
    @DisplayName("Prefix Operations")
    inner class PrefixOperations {

        @Test
        @DisplayName("StartsWith basic functionality")
        fun testStartsWith() {
            trie.insert("application")
            trie.insert("apple")
            trie.insert("apply")

            assertTrue(trie.startsWith("app"))
            assertTrue(trie.startsWith("appl"))
            assertTrue(trie.startsWith("apple"))
            assertTrue(trie.startsWith("application"))
            assertFalse(trie.startsWith("applications"))
            assertFalse(trie.startsWith("banana"))
        }

        @Test
        @DisplayName("Empty prefix should return true if trie not empty")
        fun testEmptyPrefix() {
            trie.insert("word")
            assertTrue(trie.startsWith(""))
        }

        @Test
        @DisplayName("Prefix that is also a complete word")
        fun testPrefixAsWord() {
            trie.insert("car")
            trie.insert("card")

            assertTrue(trie.startsWith("car"))
            assertTrue(trie.search("car"))
            assertTrue(trie.startsWith("card"))
            assertTrue(trie.search("card"))
        }
    }

    @Nested
    @DisplayName("Edge Cases")
    inner class EdgeCases {

        @Test
        @DisplayName("Empty string operations")
        fun testEmptyString() {
            trie.insert("")

            assertTrue(trie.search(""))
            assertTrue(trie.startsWith(""))
        }

        @Test
        @DisplayName("Single character words")
        fun testSingleCharacter() {
            trie.insert("a")
            trie.insert("b")
            trie.insert("z")

            assertTrue(trie.search("a"))
            assertTrue(trie.search("b"))
            assertTrue(trie.search("z"))
            assertFalse(trie.search("c"))
        }

        @Test
        @DisplayName("Very long word")
        fun testLongWord() {
            val longWord = "a".repeat(1000)
            trie.insert(longWord)

            assertTrue(trie.search(longWord))
            assertTrue(trie.startsWith(longWord.substring(0, 500)))
            assertFalse(trie.search(longWord + "x"))
        }

        @Test
        @DisplayName("Words that are prefixes of each other")
        fun testNestedWords() {
            trie.insert("a")
            trie.insert("ab")
            trie.insert("abc")
            trie.insert("abcd")

            assertTrue(trie.search("a"))
            assertTrue(trie.search("ab"))
            assertTrue(trie.search("abc"))
            assertTrue(trie.search("abcd"))

            assertTrue(trie.startsWith("a"))
            assertTrue(trie.startsWith("ab"))
            assertTrue(trie.startsWith("abc"))
            assertTrue(trie.startsWith("abcd"))

            assertFalse(trie.search("abcde"))
            assertFalse(trie.startsWith("abcde"))
        }
    }

    @Nested
    @DisplayName("Case Sensitivity Tests")
    inner class CaseSensitivity {

        @Test
        @DisplayName("Case sensitive by default")
        fun testCaseSensitive() {
            trie.insert("Hello")

            assertTrue(trie.search("Hello"))
            assertFalse(trie.search("hello"))
            assertFalse(trie.search("HELLO"))

            assertTrue(trie.startsWith("Hell"))
            assertFalse(trie.startsWith("hell"))
        }

        @Test
        @DisplayName("Mixed case words")
        fun testMixedCase() {
            trie.insert("CamelCase")
            trie.insert("camelcase")
            trie.insert("CAMELCASE")

            assertTrue(trie.search("CamelCase"))
            assertTrue(trie.search("camelcase"))
            assertTrue(trie.search("CAMELCASE"))
            assertFalse(trie.search("Camelcase"))
        }
    }

    @Nested
    @DisplayName("Special Characters")
    inner class SpecialCharacters {

        @Test
        @DisplayName("Numbers in words")
        fun testNumbers() {
            trie.insert("test123")
            trie.insert("456test")
            trie.insert("mix3d")

            assertTrue(trie.search("test123"))
            assertTrue(trie.search("456test"))
            assertTrue(trie.search("mix3d"))

            assertTrue(trie.startsWith("test"))
            assertTrue(trie.startsWith("456"))
            assertTrue(trie.startsWith("mix"))
        }

        @Test
        @DisplayName("Special characters")
        fun testSpecialChars() {
            trie.insert("hello-world")
            trie.insert("test_case")
            trie.insert("email@domain.com")

            assertTrue(trie.search("hello-world"))
            assertTrue(trie.search("test_case"))
            assertTrue(trie.search("email@domain.com"))

            assertTrue(trie.startsWith("hello-"))
            assertTrue(trie.startsWith("test_"))
            assertTrue(trie.startsWith("email@"))
        }
    }

    @Nested
    @DisplayName("Stress Tests")
    inner class StressTests {

        @Test
        @DisplayName("Large number of words")
        fun testManyWords() {
            val words = (1..1000).map { "word$it" }

            // Insert all words
            words.forEach { trie.insert(it) }

            // Verify all words can be found
            words.forEach { word ->
                assertTrue(trie.search(word), "Should find $word")
            }

            // Verify prefixes work
            assertTrue(trie.startsWith("word"))
            assertTrue(trie.startsWith("word1"))
            assertTrue(trie.startsWith("word999"))
        }

        @Test
        @DisplayName("Words with common prefixes")
        fun testCommonPrefixes() {
            val commonPrefix = "prefix"
            val words = (1..100).map { "$commonPrefix$it" }

            words.forEach { trie.insert(it) }

            // All should be searchable
            words.forEach { word ->
                assertTrue(trie.search(word))
            }

            // Common prefix should work
            assertTrue(trie.startsWith(commonPrefix))

            // Non-existent variations should fail
            assertFalse(trie.search("${commonPrefix}999"))
        }
    }

    @Nested
    @DisplayName("Integration Scenarios")
    inner class IntegrationScenarios {

        @Test
        @DisplayName("Dictionary-like usage")
        fun testDictionaryUsage() {
            val dictionary = listOf(
                "apple", "application", "apply", "approach", "approximate",
                "banana", "band", "bandana", "bandwidth",
                "cat", "car", "card", "care", "careful", "careless"
            )

            dictionary.forEach { trie.insert(it) }

            // Test all words exist
            dictionary.forEach { word ->
                assertTrue(trie.search(word), "Dictionary should contain '$word'")
            }

            // Test prefix searches
            assertTrue(trie.startsWith("app"))  // Should find apple, application, etc.
            assertTrue(trie.startsWith("ban"))  // Should find banana, band, etc.
            assertTrue(trie.startsWith("car"))  // Should find car, card, etc.

            // Test non-dictionary words
            assertFalse(trie.search("applications"))
            assertFalse(trie.search("caring"))
            assertFalse(trie.search("band-aid"))
        }

        @Test
        @DisplayName("Autocomplete simulation")
        fun testAutocompleteSimulation() {
            val words = listOf("search", "seat", "seattle", "see", "seed", "seem", "seen")
            words.forEach { trie.insert(it) }

            // User types "se" - should have prefix
            assertTrue(trie.startsWith("se"))

            // User types "sea" - should have prefix
            assertTrue(trie.startsWith("sea"))

            // User types "sear" - should have prefix
            assertTrue(trie.startsWith("sear"))

            // User types "searc" - should have prefix
            assertTrue(trie.startsWith("searc"))

            // User types "search" - should be complete word
            assertTrue(trie.search("search"))
            assertTrue(trie.startsWith("search"))

            // Invalid prefixes
            assertFalse(trie.startsWith("sz"))
            assertFalse(trie.startsWith("searching"))
        }
    }

    @Test
    @DisplayName("Comprehensive mixed operations test")
    fun testMixedOperations() {
        // Build a complex trie
        val testData = mapOf(
            "the" to true,
            "a" to true,
            "there" to true,
            "answer" to true,
            "any" to true,
            "by" to true,
            "bye" to true,
            "their" to true
        )

        testData.keys.forEach { trie.insert(it) }

        // Test all positive cases
        testData.forEach { (word, expected) ->
            assertEquals(expected, trie.search(word), "Search for '$word'")
        }

        // Test prefixes
        assertTrue(trie.startsWith("th"))  // the, there, their
        assertTrue(trie.startsWith("an"))  // answer, any
        assertTrue(trie.startsWith("by"))  // by, bye

        // Test negative cases
        assertFalse(trie.search("them"))
        assertFalse(trie.search("answers"))
        assertFalse(trie.search("b"))
        assertFalse(trie.startsWith("z"))
        assertFalse(trie.startsWith("them"))
    }
}