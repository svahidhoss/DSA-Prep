class Solution00876 {
    fun middleNode(head: ListNode?): ListNode? {
        if (head?.next == null) return head

        var quickNode = head
        var slowNode = head
        while (quickNode?.next != null) {
//            if (quickNode.next == null) {
//                return slowNode
//            }
            quickNode = quickNode.next!!.next

            slowNode = slowNode?.next
        }

        return slowNode
    }
}
