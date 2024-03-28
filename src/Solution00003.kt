import kotlin.math.max

class Solution0003 {
    fun lengthOfLongestSubstring(s: String): Int {
        if (s.length < 2) return s.length

        val charsIndexMap = HashMap<Char, Int>()
        val answers = mutableListOf<PossibleAnswer>()

        var i = 0
        var firstIndex = 0
        for (c in s) {
            // Add to the answers if witnessed a rep
            if (charsIndexMap.contains(c)) {
                answers.add(PossibleAnswer(firstIndex, i - firstIndex))
                firstIndex = max(charsIndexMap[c]!! + 1, firstIndex)
            }
            charsIndexMap[c] = i++
        }

        // has reached the end
        answers.add(PossibleAnswer(firstIndex, i - firstIndex))

//        val lengthList = answers.map { it.length }
//        return lengthList.max() ?: 0

        return answers.maxOf { it.length }
    }

    fun lengthOfLongestSubstring2(s: String): Int {
        if (s.length < 2) return s.length
        val charMap = mutableMapOf<Char, Int>()

        var answer = 0
        var p = 0
        var q = 0

        while (q < s.length) {
            if (charMap.contains(s[q])) {
                answer = Math.max(answer, q - p)
                p = Math.max(charMap[s[p]]!! + 1, p)
            }
            charMap[s[q]] = q++
        }
        answer = Math.max(answer, q - p)

        return answer
    }

    data class PossibleAnswer(val start: Int, val length: Int)
}

fun main(args: Array<String>) {
    val sol = Solution0003()
    var s = "a123hdfl"
    println(sol.lengthOfLongestSubstring2(s))  // 8
    s = "bbbbb"
    println(sol.lengthOfLongestSubstring2(s))  // 1
    s = "pwwkew"
    println(sol.lengthOfLongestSubstring2(s))  // 3
    s = "abcabcbb"
    println(sol.lengthOfLongestSubstring2(s))  // 3
    s = " "
    println(sol.lengthOfLongestSubstring2(s))  // 1
    s = ""
    println(sol.lengthOfLongestSubstring2(s))  // 0
    s = "bbabc"
    println(sol.lengthOfLongestSubstring2(s))  // 3
    s = "abba"
    println(sol.lengthOfLongestSubstring2(s))  // 2
}
