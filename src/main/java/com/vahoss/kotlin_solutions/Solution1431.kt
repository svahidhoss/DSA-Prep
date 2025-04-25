package com.vahoss.kotlin_solutions

class Solution1431 {
    fun kidsWithCandies(candies: IntArray, extraCandies: Int): BooleanArray {
        val maxCandiesNum = candies.max()
        return BooleanArray(candies.size) { i -> candies[i] + extraCandies > maxCandiesNum }
    }
}