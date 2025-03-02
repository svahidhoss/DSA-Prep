package java_solutions;

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
        var head = new ListNode(0);
        var node = new ListNode(0);
        head.next = node;
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
        if (list1 != null) {
            while (list1 != null) {
                node.next = list1;
                list1 = list1.next;
                node = node.next;
            }
        } else {
            while (list2 != null) {
                node.next = list2;
                list2 = list2.next;
                node = node.next;
            }
        }

        return head.next;
    }
}