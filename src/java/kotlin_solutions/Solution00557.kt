package java.kotlin_solutions

class Solution00557 {
    fun reverseWords2(s: String): String {
        val words = s.split(" ")
        val reversedWords = words.map { it.reversed() }
        return reversedWords.joinToString(" ")
    }

    fun reverseWords(s: String): String {
        val result = StringBuilder(s)
        val n = result.length
        var p = 0
        var q = 0
        while (p < n) {
            // find the end of the current word
            try {
                while (result[q] != ' ') q++
            } catch (e: StringIndexOutOfBoundsException) {

            }

            var newP = p
            var newQ = q - 1
            while (newP < newQ) {
                val temp = result[newP]
                result[newP] = result[newQ]
                result[newQ] = temp
                newP++
                newQ--
            }
            p = ++q
        }
        return result.toString()
    }

}

fun main() {
    val sol = Solution00557()

    println(sol.reverseWords2("Let's take LeetCode contest"))
    println(sol.reverseWords2("Mr Ding"))
}
