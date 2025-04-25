package com.vahoss.java_solutions;

public class Solution00141 {
    /**
     * Floyd's Cycle-Finding Algorithm
     * (also known as the "tortoise and hare" algorithm)
     *
     * @param head beg of the linked list
     * @return if the linked list has a cycle in it
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;

        var p = head;
        var q = head.next;

        while (q != null && p != q) {
            p = p.next;
            q = q.next.next;
        }

        return p == q;
    }

    /**
     * This modifies the original list structure by changing
     * node values. This could be problematic if the list needs
     * to be preserved in its original state.
     *
     * @param head beg of the linked list
     * @return if the linked list has a cycle in it
     */
    public boolean hasCycle2(ListNode head) {
        if (head == null || head.next == null) return false;

        // -10^5 <= Node.val <= 10^5
        while (head != null && head.val != Integer.MIN_VALUE) {
            head.val = Integer.MIN_VALUE;
            head = head.next;
        }

        return head != null;
    }

    public static void main(String[] args) {
        var solution = new Solution00141();

        // Test case 1: No cycle, single node
        ListNode node1 = new ListNode(1);
        boolean expected1 = false;
        boolean result1 = solution.hasCycle(node1);
        System.out.println("Test 1 (No cycle, single node): \nExpected: " + expected1 + ", Got: " + result1);

        // Test case 2: No cycle, multiple nodes
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;
        boolean expected2 = false;
        boolean result2 = solution.hasCycle(node1);
        System.out.println("Test 2 (No cycle, multiple nodes): \nExpected: " + expected2 + ", Got: " + result2);

        // Test case 3: Cycle exists
        node3.next = node1;  // Creates a cycle
        boolean expected3 = true;
        boolean result3 = solution.hasCycle(node1);
        System.out.println("Test 3 (Cycle exists): \nExpected: " + expected3 + ", Got: " + result3);

        // Test case 4: Cycle exists, single node pointing to itself
        ListNode node4 = new ListNode(4);
        node4.next = node4;  // Creates a cycle
        boolean expected4 = true;
        boolean result4 = solution.hasCycle(node4);
        System.out.println("Test 4 (Cycle exists, single node): \nExpected: " + expected4 + ", Got: " + result4);

        // Test case 5: Empty list
        boolean expected5 = false;
        boolean result5 = solution.hasCycle(null);
        System.out.println("Test 5 (Empty list): \nExpected: " + expected5 + ", Got: " + result5);
    }
}