package java

internal object MainKotlin {
    @JvmStatic
    fun main(args: Array<String>) {
        println("Vahid Hosseinioun")

        // Create a list node based on the given array
//        val values = intArrayOf(1, 2, 3, 4, 5)
//        val values = intArrayOf(1, 2, 3, 4, 5, 6)
//        val values = intArrayOf()
        val values = intArrayOf(1)

        val testListNode: ListNode? = createListNodeFromArray(values)
        val sol = Solution00024()
//        val sol = Solution0876()
//        val result = sol.middleNode(testListNode)
//        val arr = arrayOf(
//            intArrayOf(36, 3),
//            intArrayOf(1, 5),
//            intArrayOf(12, 8),
//            intArrayOf(25, 9),
//            intArrayOf(53, 11),
//            intArrayOf(29, 12),
//            intArrayOf(52, 14)
//        )
//        println(Solution.hardestWorker(70, arr))

        val message = sol.swapPairsIterative(testListNode)
        println(message)
    }
}
