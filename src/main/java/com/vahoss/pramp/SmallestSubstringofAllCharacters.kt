package com.vahoss.pramp

fun getShortestUniqueSubstring(arr: CharArray, str: String): String {
    // Set for the array to store unique characters
    val set = arr.toSet()

    // map for the sliding window sub string
    val windowMap = mutableMapOf<Char, Int>()

    var result = ""
    var minLength = Int.MAX_VALUE
    var p = 0
    var q = 0
    var count = 0
    while (q < str.length) {
        val qChar = str[q]
        windowMap[qChar] = windowMap.getOrDefault(qChar, 0) + 1
        if (set.contains(qChar) && windowMap[qChar] == 1) count++

        while (count == set.size) {
            val pChar = str[p]
            if (set.contains(pChar) && windowMap[pChar] == 1) count--

            windowMap[pChar] = windowMap.getOrDefault(pChar, 0) - 1

            val substringLength = q - p + 1
            if (substringLength < minLength) {
                result = str.substring(p, q + 1)
                minLength = substringLength
            }

            p++
        }

        q++
    }


    return result
}

fun main() {
    println(getShortestUniqueSubstring(charArrayOf('x', 'y', 'z'), "xyyzyzyx"))
}