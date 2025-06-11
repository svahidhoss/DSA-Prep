package com.vahoss.kotlin_solutions

import Solution00242
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import kotlin.time.measureTime

class Solution00242Test {

    private lateinit var solution: Solution00242

    @BeforeEach
    fun setUp() {
        solution = Solution00242()
    }

    fun isAnagram(s: String, t: String): Boolean {
        return solution.isAnagram(s, t)
    }

    @Nested
    @DisplayName("Basic Anagram Tests")
    inner class BasicAnagramTests {

        @Test
        @DisplayName("Should detect valid anagrams")
        fun testValidAnagrams() {
            assertTrue(isAnagram("listen", "silent"), "listen and silent should be anagrams")
            assertTrue(isAnagram("evil", "vile"), "evil and vile should be anagrams")
            assertTrue(isAnagram("a", "a"), "single character should be anagram of itself")
            assertTrue(isAnagram("abc", "bca"), "simple 3-letter anagram")
            assertTrue(isAnagram("stressed", "desserts"), "stressed and desserts should be anagrams")
        }

        @Test
        @DisplayName("Should reject invalid anagrams")
        fun testInvalidAnagrams() {
            assertFalse(isAnagram("rat", "car"), "rat and car should not be anagrams")
            assertFalse(isAnagram("hello", "world"), "hello and world should not be anagrams")
            assertFalse(isAnagram("python", "java"), "python and java should not be anagrams")
        }
    }

    @Nested
    @DisplayName("Edge Cases")
    inner class EdgeCaseTests {

        @Test
        @DisplayName("Should handle empty strings")
        fun testEmptyStrings() {
            assertTrue(isAnagram("", ""), "two empty strings should be anagrams")
            assertFalse(isAnagram("a", ""), "non-empty and empty should not be anagrams")
            assertFalse(isAnagram("", "a"), "empty and non-empty should not be anagrams")
        }

        @Test
        @DisplayName("Should handle different lengths")
        fun testDifferentLengths() {
            assertFalse(isAnagram("abc", "abcd"), "strings of different lengths cannot be anagrams")
            assertFalse(isAnagram("abcd", "abc"), "strings of different lengths cannot be anagrams")
            assertFalse(isAnagram("short", "muchlongerstring"), "very different lengths should return false")
        }

        @Test
        @DisplayName("Should handle single characters")
        fun testSingleCharacters() {
            assertTrue(isAnagram("a", "a"), "same single character")
            assertFalse(isAnagram("a", "b"), "different single characters")
            assertTrue(isAnagram("z", "z"), "last alphabet character")
        }
    }

    @Nested
    @DisplayName("Character Frequency Tests")
    inner class CharacterFrequencyTests {

        @Test
        @DisplayName("Should detect different character frequencies")
        fun testDifferentFrequencies() {
            assertFalse(isAnagram("aab", "aabb"), "different frequencies of same characters")
            assertFalse(isAnagram("abc", "aabc"), "extra character in second string")
            assertFalse(isAnagram("aabc", "abc"), "extra character in first string")
        }

        @Test
        @DisplayName("Should handle repeated characters correctly")
        fun testRepeatedCharacters() {
            assertTrue(isAnagram("aabbcc", "abcabc"), "same characters with different arrangements")
            assertTrue(isAnagram("aab", "baa"), "simple repeated character anagram")
            assertTrue(isAnagram("aaabbbccc", "abcabcabc"), "multiple repeated characters")
            assertFalse(isAnagram("aaab", "aabb"), "different frequency distribution")
        }
    }

    @Nested
    @DisplayName("Alphabet Coverage Tests")
    inner class AlphabetCoverageTests {

        @Test
        @DisplayName("Should handle full alphabet")
        fun testFullAlphabet() {
            val alphabet = "abcdefghijklmnopqrstuvwxyz"
            val reversed = "zyxwvutsrqponmlkjihgfedcba"
            assertTrue(isAnagram(alphabet, reversed), "full alphabet should be anagram of its reverse")
        }

        @Test
        @DisplayName("Should handle boundary characters")
        fun testBoundaryCharacters() {
            assertFalse(isAnagram("a", "z"), "first and last letters are not anagrams")
            assertTrue(isAnagram("az", "za"), "first and last letters rearranged")
        }
    }

//    @Nested
//    @DisplayName("Error Handling Tests")
//    inner class ErrorHandlingTests {
//
//        @Test
//        @DisplayName("Should handle uppercase letters (current limitation)")
//        fun testUppercaseLetters() {
//            // Note: Current implementation will throw exception for uppercase
//            assertThrows<StringIndexOutOfBoundsException> {
//                isAnagram("Listen", "Silent")
//            }
//        }
//
//        @Test
//        @DisplayName("Should handle special characters (current limitation)")
//        fun testSpecialCharacters() {
//            // Note: Current implementation will throw exception for non-alphabetic characters
//            assertThrows<StringIndexOutOfBoundsException> {
//                isAnagram("hello!", "!hello")
//            }
//        }
//    }

    @Nested
    @DisplayName("Performance Tests")
    inner class PerformanceTests {

        @Test
        @DisplayName("Should handle large strings efficiently")
        fun testLargeStrings() {
            val largeString1 = "abc".repeat(10000)  // 30,000 characters
            val largeString2 = "bca".repeat(10000)  // Same characters, different order

            val executionTime = measureTime {
                assertTrue(isAnagram(largeString1, largeString2), "large anagrams should be detected")
            }

            println("Execution time for 30k character strings: $executionTime")
            // Should complete in reasonable time (typically < 100ms)
        }

        @Test
        @DisplayName("Should quickly reject different length strings")
        fun testQuickRejectionDifferentLengths() {
            val shortString = "abc"
            val longString = "a".repeat(100000)

            val executionTime = measureTime {
                assertFalse(isAnagram(shortString, longString), "different lengths should be quickly rejected")
            }

            println("Execution time for different length rejection: $executionTime")
        }
    }
}
