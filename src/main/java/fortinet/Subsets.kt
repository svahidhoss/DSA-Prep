package main.java.fortinet

fun generateSubsets(nums: IntArray): List<List<Int>> {
    // Start with an empty list of lists (which represents the set of subsets)
    val subsets = mutableListOf<List<Int>>()
    // Add the empty subset to start with
    subsets.add(emptyList())

    // Iterate over each number in the given set
    for (num in nums) {
        // Determine the current number of subsets
        val n = subsets.size
        // For each existing subset, create a new subset that includes the current number
        for (i in 0 until n) {
            // Create a new list from the existing subset
            val set = ArrayList(subsets[i])
            // Add the current number to the new subset
            set.add(num)
            // Add the new subset to the list of subsets
            subsets.add(set)
        }
    }

    // Return the complete set of subsets
    return subsets
}


// Example usage
fun main() {
    val nums = intArrayOf(1, 5, 3)
    val subsets = generateSubsets(nums)
    println("All subsets:")
    subsets.forEach { subset ->
        println(subset)
    }
}
