package java.java_solutions;

public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    // Helper method to print the linked list
    public static void printList(ListNode head) {
        if (head == null) {
            System.out.println("Empty list");
            return;
        }

        StringBuilder result = new StringBuilder();
        while (head != null) {
            result.append(head.val);
            if (head.next != null) {
                result.append("->");
            }
            head = head.next;
        }
        System.out.println(result.toString());
    }
}
