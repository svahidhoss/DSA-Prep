package kotlin_solutions

import java.util.*

class Solution0026 {

    fun strStr(haystack: String, needle: String): Int {
        // use a sliding window
        for (i in 0..haystack.length - needle.length) {
            val hWindow = haystack.substring(i, i + needle.length)
            if (hWindow == needle) return i
        }
        return -1
    }

    fun strStr2(haystack: String, needle: String): Int {
        var result = -1
        var p = 0
        var q = 0

        while (p < haystack.length) {
            val hChar = haystack[p]
            val nChar = needle[q]
            println("nchar is $nChar and hChar is $hChar")
            if (hChar == nChar) {
                if (q == 0) result = p
                // A match was found
                if (q == needle.length - 1) return result
            }

            q = if (hChar != nChar) 0 else q + 1
            p++
        }

        return -1
    }
}

class Solution00026 {

    fun removeDuplicates(nums: IntArray): Int {
        var p = 0
        for (n in nums) {
            if (n != nums[p]) nums[++p] = n
        }

        return p + 1
    }

    fun removeDuplicates2(nums: IntArray): Int {
        // the nums is sorted by default
        val set = nums.toSet()
        set.forEachIndexed { index, value -> nums[index] = value }
        return set.size
    }

    fun removeDuplicates3(nums: IntArray): Int {
        val set = mutableSetOf<Int>()

        for (n in nums) set.add(n)

        for ((i, v) in set.withIndex()) nums[i] = v

        return set.size
    }
}

fun main() {
    val s = Solution00026()
    var nums = intArrayOf(1)
    println(s.removeDuplicates(nums))
    println(Arrays.toString(nums))

    nums = intArrayOf(1, 1, 2)
    println(s.removeDuplicates(nums))
    println(Arrays.toString(nums))

    nums = intArrayOf(0, 0, 1, 1, 1, 2, 2, 3, 3, 4)
    println(s.removeDuplicates(nums))
    println(Arrays.toString(nums))

    /*val sol = Solution0026()
    println(sol.strStr("sadbutsad", "sad"))
    println(sol.strStr("leetcode", "leeto"))
    println(sol.strStr("hello", "ll"))
    println(sol.strStr("mississippi", "issip"))*/
}
