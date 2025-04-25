package main.java.kotlin_solutions

class Solution0345 {
    fun reverseVowels(s: String): String {
        val cArray = s.toCharArray()
        var p = 0
        var q = s.length - 1

        var pVowel = false
        var qVowel = false
        while (p < q) {
            if (!pVowel && !isVowel(cArray[p])) p++
            else pVowel = true

            if (!qVowel && !isVowel(cArray[q])) q--
            else qVowel = true

            if (pVowel and qVowel) {
                swap(cArray, p++, q--)
                pVowel = false
                qVowel = false
            }
        }

        return cArray.joinToString("")
    }

    private fun swap(cArray: CharArray, i1: Int, i2: Int) {
        val temp = cArray[i1]
        cArray[i1] = cArray[i2]
        cArray[i2] = temp
    }

    private fun isVowel(c: Char): Boolean {
        return when (c) {
            'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U' -> true
            else -> false
        }
    }

}

fun main(args: Array<String>) {
    val sol = Solution0345()
    var s = "hello"
    println(sol.reverseVowels(s))
    s = "leetcode"
    println(sol.reverseVowels(s))
    s = "Umbreilla"
    println(sol.reverseVowels(s))
}