package java.amazon

class Solution01846 {
    fun maximumElementAfterDecrementingAndRearranging(arr: IntArray): Int {
        arr.sort()
        arr[0] = 1
        for (i in 1..<arr.size) {
            if (arr[i] - arr[i - 1] > 1)
                arr[i] = arr[i - 1] + 1
        }

        return arr[arr.size - 1]
    }
}

fun main() {
    val s = Solution01846()
    println(s.maximumElementAfterDecrementingAndRearranging(intArrayOf(2, 2, 1, 2, 1)))
    println(s.maximumElementAfterDecrementingAndRearranging(intArrayOf(100, 1, 1000)))
    println(s.maximumElementAfterDecrementingAndRearranging(intArrayOf(1, 2, 3, 4, 5)))
}
