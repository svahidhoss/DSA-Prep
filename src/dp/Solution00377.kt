package dp

class Solution00377 {
    fun combinationSum4(nums: IntArray, target: Int): Int {
        nums.sort()
        val memory = IntArray(target + 1)
        memory[0] = 1
        for (t in 1 until memory.size) {
            var combinationSum = 0
            for (num in nums) {
                if (num > t) break
                combinationSum += memory[t - num]
            }
            memory[t] = combinationSum
        }

        return memory[target]
    }

    fun combinationSum4TopDown(nums: IntArray, target: Int): Int {
        // contains the n
        val memo = mutableMapOf<Int, Int>()
        memo[0] = 1 // Base case: there is 1 way to make up the target 0
        return dp(nums.sortedArray(), target, memo)
    }

    private fun dp(nums: IntArray, target: Int, memo: MutableMap<Int, Int>): Int {
        return memo.getOrPut(target) {
            var combinationCount = 0
            for (num in nums) {
                if (num > target) break // No need to continue if num exceeds the target
                combinationCount += dp(nums, target - num, memo)
            }
            combinationCount
        }
    }

    fun reverseString(s: CharArray): Unit {
        if (s.size <= 1)
            return
        val temp = s[0]
        s[0] = s[s.size - 1]
        s[s.size - 1] = temp
        // slice is creating a new array :( -> won't work properly
        reverseString(s.sliceArray(1 .. s.size - 2))
    }
}

fun main() {
    val s = Solution00377()
    var charArray = charArrayOf('h', 'e', 'l', 'l', 'o')
    s.reverseString(charArray)
    charArray = charArrayOf('H', 'a', 'n', 'n', 'a', 'h')
    s.reverseString(charArray)

    println(s.combinationSum4(intArrayOf(1, 2, 3), target = 4))
    println(s.combinationSum4(intArrayOf(9), 3))
    println(s.combinationSum4(intArrayOf(3, 1, 2, 4), 4))
}