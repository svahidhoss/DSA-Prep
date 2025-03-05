package kotlin_solutions

class Solution00329 {
    fun isSubsequence(s: String, t: String): Boolean {
        var p = 0
        var q = 0
        val sChars = s.toCharArray()
        val tChars = t.toCharArray()

        while (p < s.length && q < t.length) {
            if (sChars[p] == tChars[q]) p++
            q++
        }

        // This will cover the empty s strings
        return (p == s.length)
    }
}

fun main() {
    val sol = Solution00329()
    println(sol.isSubsequence("b", "abc"))
    println(sol.isSubsequence("abc", "ahbgdc"))
    println(sol.isSubsequence("", "ahbgdc"))
    println(sol.isSubsequence("abc", ""))
    println(sol.isSubsequence("", ""))
}