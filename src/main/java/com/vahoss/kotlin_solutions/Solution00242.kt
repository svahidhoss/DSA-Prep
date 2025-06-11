class Solution00242 {
    fun isAnagram(s: String, t: String): Boolean {
        val charCountArray = IntArray(26)
        s.forEach {
            charCountArray[it - 'a']++
        }
        t.forEach {
            charCountArray[it - 'a']--
        }
        return charCountArray.all { it == 0 }
    }

    fun isAnagramOptimized(s: String, t: String): Boolean {
        if (s.length != t.length) return false
        val charCount = IntArray(26)
        for (i in s.indices) {
            charCount[s[i] - 'a']++
            charCount[t[i] - 'a']--
        }
        return charCount.all { it == 0 }
    }
}
