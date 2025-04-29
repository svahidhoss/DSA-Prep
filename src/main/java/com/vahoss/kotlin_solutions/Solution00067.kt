package com.vahoss.kotlin_solutions

class Solution0067 {
    fun addBinary(a: String, b: String): String {
        val sb = StringBuilder()

        var carry = 0
        var i = a.length - 1
        var j = b.length - 1
        while (carry > 0 || i >= 0 || j >= 0) {
            val aVal = if (i < 0) 0 else a[i--].digitToInt()
            val bVal = if (j < 0) 0 else b[j--].digitToInt()
            val sum = aVal + bVal + carry
            sb.append(sum % 2)
            carry = sum / 2
        }

        return sb.reverse().toString()
    }
}

fun main() {
    val s = Solution0067()
    println(s.addBinary("11", "1"))
    println(s.addBinary(a = "1010", b = "1011"))
}