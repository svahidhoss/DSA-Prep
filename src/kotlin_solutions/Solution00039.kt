package kotlin_solutions

class Solution00039 {

    val result = mutableListOf<List<Int>>()

    fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
        recursiveCombinationSum(candidates, target, 0, 0, 0, mutableListOf())
        return result
    }

    private fun recursiveCombinationSum(
        candidates: IntArray,
        target: Int,
        begIndex: Int,
        currentIndex: Int,
        sum: Int,
        potentialCandidate: MutableList<Int>
    ) {
        val newSum = sum + candidates[currentIndex]
        potentialCandidate.add(candidates[currentIndex])
        if (newSum == target) {
            result.add(potentialCandidate)
        } else if (newSum > target) {
            // reached the end of the candidates
            if (begIndex == candidates.size - 1) return
            // reset to the new index
            else recursiveCombinationSum(candidates, target, begIndex + 1, begIndex + 1, 0, mutableListOf())
        } else {
            recursiveCombinationSum(candidates, target, begIndex, currentIndex + 1, newSum, potentialCandidate)
        }
    }
}

fun main() {
    val sol = Solution00039()
    println( sol.combinationSum(intArrayOf(2,3,6,7), 7))
    println( sol.combinationSum(intArrayOf(2,3,5), 8))
}
