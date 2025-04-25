package com.vahoss.kotlin_solutions

class Solution00119 {
    fun getRow(rowIndex: Int): List<Int> {
        val row = MutableList(rowIndex + 1) { 1 } // Initialize the list with 1s

        for (i in 1 until rowIndex) {
            // Iterate backward so each value can be calculated using the value before it
            for (j in i downTo 1) {
                row[j] = row[j] + row[j - 1]
            }
        }

        return row
    }

    fun getRow2(rowIndex: Int): List<Int> {
        val row = mutableListOf<Int>()
        val nextRow = mutableListOf<Int>()

        row.add(1)
        for (i in 1..rowIndex) {
            println(row)

            nextRow.add(1)
            for (j in 1 until i) {
                nextRow.add(row[j - 1] + row[j])
            }
            nextRow.add(1)


            row.clear()
            row.addAll(nextRow)
            nextRow.clear()
        }

        return row
    }
}

fun main() {
    val sol = Solution00119()
    println(sol.getRow(5))
}