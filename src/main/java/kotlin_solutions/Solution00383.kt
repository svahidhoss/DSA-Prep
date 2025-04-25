package main.java.kotlin_solutions

class Solution00383 {
    fun canConstructOptimized(ransomNote: String, magazine: String): Boolean {
        val magCount = magazine.groupingBy { it }.eachCount().toMutableMap()
        return ransomNote.all { char ->
            magCount[char]?.let { count ->
                if (count > 0) {
                    magCount[char] = count - 1
                    true
                } else false
            } ?: false
        }
    }

    fun canConstruct(ransomNote: String, magazine: String): Boolean {
        // Handle edge cases explicitly
        if (ransomNote.isEmpty()) return true
        if (magazine.isEmpty()) return false

        val magazineMap = mutableMapOf<Char, Int>()
        magazine.forEach {
            magazineMap[it] = magazineMap.getOrDefault(it, 0) + 1
        }

        ransomNote.forEach { char ->
            magazineMap[char]?.let {
                if (it <= 0) return false
                magazineMap[char] = it - 1
            } ?: return false
        }

        return true
    }

    fun canConstruct2(ransomNote: String, magazine: String): Boolean {
        val ransomMap = mutableMapOf<Char, Int>()
        ransomNote.forEach {
            ransomMap[it] = ransomMap.getOrDefault(it, 0) + 1
        }

        val magazineMap = mutableMapOf<Char, Int>()
        magazine.forEach {
            magazineMap[it] = magazineMap.getOrDefault(it, 0) + 1
        }

        ransomMap.forEach { (key, value) ->
            if (magazineMap.getOrDefault(key, 0) < value) return false
        }

        return true
    }
}

fun main(args: Array<String>) {
    val sol = Solution00383()
    println(sol.canConstruct(ransomNote = "a", magazine = "b"))
    println(sol.canConstruct(ransomNote = "aa", magazine = "ab"))
    println(sol.canConstruct(ransomNote = "aa", magazine = "aab"))

    // Some additional test cases
    println("\nAdditional test cases:")
    println(sol.canConstruct("", "abc"))        // true (empty ransom note)
    println(sol.canConstruct("abc", ""))        // false (empty magazine)
    println(sol.canConstruct("aaabbc", "abcabcabc")) // true (repeated chars)
    println(sol.canConstruct("aA", "aAb"))      // true (case sensitivity)
    println(sol.canConstruct("aA", "ab"))       // false (case sensitivity)
    println(sol.canConstruct("hello", "hello world magazine")) // true (longer magazine)
}