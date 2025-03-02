package java_solutions;

import static java_solutions.ListNode.printList;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
public class Solution00021 {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // Create the node that will hold the final list's head
        var head = new ListNode(0);
        var node = head;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                node.next = list1;
                list1 = list1.next;
            } else {
                node.next = list2;
                list2 = list2.next;
            }
            node = node.next;
        }

        // Attach the remaining nodes directly
        node.next = (list1 != null) ? list1 : list2;

        return head.next;
    }

    public static void main(String[] args) {
        var solution = new Solution00021();

        // Test Case 1: Basic merge of two non-empty lists
        System.out.println("Test Case 1: Expected: 1->2->3->4->5->6");
        ListNode list1 = new ListNode(1, new ListNode(3, new ListNode(5)));
        ListNode list2 = new ListNode(2, new ListNode(4, new ListNode(6)));
        printList(solution.mergeTwoLists(list1, list2)); // Expected: 1->2->3->4->5->6

        // Test Case 2: First list is empty
        System.out.println("\nTest Case 2: Expected: 1->2->3");
        list1 = null;
        list2 = new ListNode(1, new ListNode(2, new ListNode(3)));
        printList(solution.mergeTwoLists(list1, list2)); // Expected: 1->2->3

        // Test Case 3: Second list is empty
        System.out.println("\nTest Case 3: Expected: 1->2->3");
        list1 = new ListNode(1, new ListNode(2, new ListNode(3)));
        list2 = null;
        printList(solution.mergeTwoLists(list1, list2)); // Expected: 1->2->3

        // Test Case 4: Both lists are empty
        System.out.println("\nTest Case 4: Expected: null");
        list1 = null;
        list2 = null;
        printList(solution.mergeTwoLists(list1, list2)); // Expected: null

        // Test Case 5: Lists with duplicate values
        System.out.println("\nTest Case 5: Expected: 1->1->2->3->4->4");
        list1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        list2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        printList(solution.mergeTwoLists(list1, list2)); // Expected: 1->1->2->3->4->4
    }

}