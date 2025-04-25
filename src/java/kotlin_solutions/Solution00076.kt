package java.kotlin_solutions

class Solution00076 {
    fun minWindow(s: String, t: String): String {
        if (t.length > s.length) return ""

        val tMap = mutableMapOf<Char, Int>()
        t.forEach { tMap[it] = tMap.getOrDefault(it, 0) + 1 }

        val sMap = mutableMapOf<Char, Int>()
        var minSubLength = Int.MAX_VALUE
        var difference = 0
        var result = ""

        var beg = 0
        var end = 0
        while (end < s.length) {
            val endChar = s[end]
            // check the latest char is part of string t
            if (tMap.containsKey(endChar)) {
                sMap[endChar] = sMap.getOrDefault(endChar, 0) + 1
                if (sMap[endChar]!! <= tMap[endChar]!!) difference++
            }

            // minimize the length of the result substring
            while (difference == t.length) {
                // Update the result
                if (end - beg + 1 < minSubLength) {
                    minSubLength = end - beg + 1
                    result = s.substring(beg, end + 1)
                }

                val begChar = s[beg]
                if (tMap.contains(begChar)) {
                    sMap[begChar] = sMap[begChar]!! - 1
                    if (sMap[begChar]!! < tMap[begChar]!!) difference--
                }

                beg++
            }

            end++
        }

        return result
    }
}

fun main() {
    val s = Solution00076()
//    println(s.minWindow("ADOBECODEBANC", t = "ABC"))
//    println(s.minWindow("a", "a"))
//    println(s.minWindow("a", "aa"))
    println(s.minWindow("bba", "ab"))
}