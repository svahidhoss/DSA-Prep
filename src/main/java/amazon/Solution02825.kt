package main.java.amazon

class Solution02825 {
    fun canMakeSubsequence(str1: String, str2: String): Boolean {
        var p = 0
        for (c in str1) {
            if (p == str2.length) break

            val diff = charDiff(c, str2[p])
            if (diff == 0 || diff == 1) p++
        }

        return p == str2.length
    }

    private fun charDiff(c1: Char, c2: Char): Int {
        return if (c1 == 'z' && c2 == 'a') 1 else c2 - c1
    }
}

fun main() {
    val s = Solution02825()
    println(s.canMakeSubsequence("abc", "ad"))
    println(s.canMakeSubsequence("zc", str2 = "ad"))
    println(s.canMakeSubsequence("ab", str2 = "d"))
}
