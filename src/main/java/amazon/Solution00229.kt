package main.java.amazon

class Solution00229 {
    fun majorityElement(nums: IntArray): List<Int> {
        val result = mutableListOf<Int>()
        var c1 = -1
        var count1 = 0
        nums.forEach {
            if (count1 == 0) c1 = it
            if (c1 == it) count1++ else count1--
        }

        var c2 = -1
        var count2 = 0
        nums.forEach loop@{
            if (it == c1) return@loop
            if (count2 == 0) c2 = it
            if (c2 == it) count2++ else count2--
        }

        count1 = 0
        count2 = 0
        nums.forEach {
            if (it == c1) count1++
            if (c2 == it) count2++
        }
        if (count1 > nums.size / 3) result.add(c1)
        if (count2 > nums.size / 3) result.add(c2)

        return result
    }
}

fun main() {
    val s = Solution00229()
    println(s.majorityElement(intArrayOf(2,1,1,3,1,4,5,6)))
    println(s.majorityElement(intArrayOf(3, 2, 3)))
    println(s.majorityElement(intArrayOf(1)))
    println(s.majorityElement(intArrayOf(1, 2)))
    println(s.majorityElement(intArrayOf(1, 2, 3)))
}