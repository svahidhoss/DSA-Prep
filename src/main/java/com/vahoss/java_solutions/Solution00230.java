package com.vahoss.java_solutions;

import java.util.ArrayList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
public class Solution00230 {

    /**
     * we know that: 1 <= k <= n <= 104
     *
     * @param root The root
     * @param k    the index of the smallest value
     * @return The kth smallest value in BST
     */
    public int kthSmallest(TreeNode root, int k) {
        var counter = new int[]{0};
        return kthSmallestHelper(root, k, counter);
    }

    /**
     * DFS in order traversal:
     */
    public int kthSmallestHelper(TreeNode root, int k, int[] counter) {
        // if null, skip the node
        if (root == null) return -1;

        // left
        int leftResult = kthSmallestHelper(root.left, k, counter);
        if (leftResult != -1) return leftResult;

        // node
        counter[0]++;
        if (counter[0] >= k) return root.val;

        // right
        return kthSmallestHelper(root.right, k, counter);
    }


    /**
     * we know that: 1 <= k <= n <= 104
     *
     * @param root The root
     * @param k    the index of the smallest value
     * @return The kth smallest value in BST
     */
    public int kthSmallest2(TreeNode root, int k) {
        List<Integer> values = new ArrayList<>();
        kthSmallestHelper(root, k, values);

        if (values.size() == k)
            return values.get(values.size() - 1);
        else
            throw new IllegalStateException("No valid answer was found!");
    }

    /**
     * DFS in order traversal:
     */
    public int kthSmallestHelper(TreeNode root, int k, List<Integer> values) {
        // if null, skip the node
        if (root == null) return -1;

        // left
        int leftResult = kthSmallestHelper(root.left, k, values);
        if (leftResult != -1) return leftResult;

        // node
        values.add(root.val);
        if (values.size() == k) return root.val;

        // right
        return kthSmallestHelper(root.right, k, values);
    }

    public static void main(String[] args) {
        Solution00230 solution = new Solution00230();

        // Test case 1 & 2: Basic BST
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(4);
        root1.left.right = new TreeNode(2);
        System.out.println("Test 1: Expected: 1, Got: " + solution.kthSmallest(root1, 1));
        System.out.println("Test 2: Expected: 3, Got: " + solution.kthSmallest(root1, 3));

        // Test case 3 & 4: Larger BST
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(3);
        root2.right = new TreeNode(6);
        root2.left.left = new TreeNode(2);
        root2.left.right = new TreeNode(4);
        root2.left.left.left = new TreeNode(1);
        System.out.println("Test 3: Expected: 3, Got: " + solution.kthSmallest(root2, 3));
        System.out.println("Test 4: Expected: 6, Got: " + solution.kthSmallest(root2, 6));

        // Test case 5: Single node tree
        TreeNode root3 = new TreeNode(1);
        System.out.println("Test 5: Expected: 1, Got: " + solution.kthSmallest(root3, 1));

        // Test case 6: k greater than number of nodes
        try {
            solution.kthSmallest(root1, 5);
            System.out.println("Test 6: Failed - Should throw exception");
        } catch (IllegalStateException e) {
            System.out.println("Test 6: Passed - Correctly threw exception");
        }

        // Test case 7: Empty tree
        try {
            solution.kthSmallest(null, 1);
            System.out.println("Test 7: Failed - Should throw exception");
        } catch (IllegalStateException e) {
            System.out.println("Test 7: Passed - Correctly threw exception");
        }
    }
}
