
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

    /*public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // Create the node that will hold the final list's head
        ListNode head = new ListNode(-1);
        ListNode cur = head;

        while(list1 != null && list2 != null) {
            if (list1.getVal() <= list2.getVal()) {
                cur.setVal(list1.getVal());
                list1 = list1.getNext();
            } else {
                cur.setVal(list2.getVal());
                list2 = list2.getNext();
            }
            cur.setNext(new ListNode(-1));
            cur = cur.getNext();
        }

        return head;
    }*/
}


