import java.util.*

class Solution00767 {
    data class CharCount(val char: Char, val count: Int)

    fun reorganizeString(s: String): String {
        val heap = PriorityQueue<CharCount> { a, b -> b.count - a.count }
//        val heap = PriorityQueue<CharCount> { a, b -> b.count.compareTo(a.count) }
        val countArray = IntArray(26)
        var max = 0
        // Get the count of every char in the string
        s.forEach { c ->
            val i = c - 'a'
            countArray[i]++
            if (countArray[i] > max) max = countArray[i]
        }

        // if any char is repeated more than half the size of the string
        if (max > (s.length + 1) / 2) return ""

        // Put them all in a Heap:
        countArray.forEachIndexed { i, count ->
            val charCount = CharCount((i + 'a'.toInt()).toChar(), count)
            heap.offer(charCount)
        }

        val result = CharArray(s.length)
        var currentIndex = 0
        // Start going through the heap and fill the result
        while (heap.isNotEmpty()) {
            heap.poll()?.let {
                for (i in 0 until it.count) {
                    if (currentIndex >= s.length) currentIndex = 1
                    result[currentIndex] = it.char
                    currentIndex += 2
                }
            }
        }

        return String(result)
    }

    fun reorganizeString1(s: String): String {
        val countArray = IntArray(26)
        var max = 0
        var maxChar: Char? = null
        // Get the count of every char in the string
        s.forEach { c ->
            val i = c - 'a'
            countArray[i]++
            if (countArray[i] > max) {
                max = countArray[i]
                maxChar = c
            }
        }

        // if a specific char is repeated more than half the size of the string
        if (max > (s.length + 1) / 2) return ""

        val result = CharArray(s.length)
        // First put the max repeated char
        var i = 0
        while (countArray[maxChar!! - 'a'] > 0) {
            result[i] = maxChar!!
            i += 2
            countArray[maxChar!! - 'a']--
        }

        for (j in countArray.indices) {
            while (countArray[j] > 0) {
                if (i >= s.length) i = 1
                result[i] = (j + 'a'.toInt()).toChar()
                i += 2
                countArray[j]--
            }
        }

        return String(result)
    }

    /**
     * The provided code seems to be trying to reorganize a
     * string such that no two adjacent characters are the same.
     * However, there are a few issues with the code:
     *
     * Swapping Logic: The code attempts to swap characters when
     * it encounters two different characters (newS[p] != newS[q])
     * and the distance between p and q is more than 1 (q - p > 1).
     * However, this logic doesn't guarantee that the resulting
     * string will have no two adjacent characters the same. It
     * only swaps characters when it encounters a different character,
     * but it doesn't handle the case where the same character
     * appears later in the string.
     *
     * Return Condition: The function returns an empty string if
     * the final distance between p and q is more than 1 (q - p > 1).
     * However, this condition doesn't accurately determine whether
     * it's possible to reorganize the string such that no two adjacent
     * characters are the same. It's possible that the string can still
     * be reorganized even if q - p > 1 at the end of the loop.
     *
     * Inefficient Solution: The solution has a time complexity of
     * O(n^2) because in the worst-case scenario, it could end up
     * swapping every pair of characters in the string. There are
     * more efficient algorithms for this problem, such as counting
     * the occurrences of each character and placing the most frequent
     * characters first.
     */
    fun reorganizeString2(s: String): String {
        if (s.length <= 1) return s

        val newS = s.toCharArray()

        var p = 0
        var q = 1
        while (q < newS.size) {
            if (newS[p] != newS[q]) {
                if (q - p > 1) {
                    val temp = newS[++p]
                    newS[p] = newS[q]
                    newS[q] = temp
                }
                p++
                q++
            } else q++
        }

        return if (q - p > 1) "" else String(newS)
    }
}

fun main() {
    val sol = Solution00767()
    println(sol.reorganizeString("aab"))
    println(sol.reorganizeString1("aab"))
    println(sol.reorganizeString("aaab"))
    println(sol.reorganizeString1("aaab"))
    // This won't work
    println(sol.reorganizeString("baaba"))
    println(sol.reorganizeString1("baaba"))
    println(sol.reorganizeString("zqugrfbsznyiwbokwkpvpmeyvaosdkedbgjogzdpwawwl"))
    println(sol.reorganizeString1("zqugrfbsznyiwbokwkpvpmeyvaosdkedbgjogzdpwawwl"))
}