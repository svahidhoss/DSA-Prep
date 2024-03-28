package pramp;

import java.util.*;

/***********************************************************
 * CODE INSTRUCTIONS:                                      *
 * 1) The method findLargestSmallerKey you're              *
 *    asked to implement is located at line 36.            *
 * 2) Use the helper code below to implement it.           *
 * 3) In a nutshell, the helper code allows you to         *
 *    to build a Binary Search Tree.                       *
 * 4) Jump to line 82 to see an example for how the        *
 *    helper code is used to test findLargestSmallerKey.   *
 ***********************************************************/


class FindLargestSmallerKey {

    static class Node {
        int key;
        Node left;
        Node right;
        Node parent;

        Node(int key) {
            this.key = key;
            left = null;
            right = null;
            parent = null;
        }
    }

    static class BinarySearchTree {

        Node root;

        int findLargestSmallerKeyInorder(int num) {
            // your code goes here
            // 1. Inorder approach:
            sortedList.clear();
            inorder(root);
            Integer[] arr = sortedList.toArray(new Integer[0]);

            int index = Arrays.binarySearch(arr, num);

            if (index >= 0) {
                // Target found, return the value.
                return arr[index];
            } else {
                // Target not found, calculate the insertion point.
                int insertionPoint = -index - 1;
                // If insertion point is 0, all elements in the array are larger than the target.
                if (insertionPoint == 0) {
                    return -1; // Or any indicator that there's no smaller or equal value.
                } else {
                    // Return the largest smaller value.
                    return arr[insertionPoint - 1];
                }
            }
        }

        List<Integer> sortedList = new ArrayList<>();

        void inorder(Node root) {
            if (root == null) return;
            inorder(root.left);
            sortedList.add(root.key);
            inorder(root.right);
        }

        int findLargestSmallerKey(int num) {
            // your code goes here
            var result = -1;

            var node = root;
            while (root != null) {
                if (root.key == num) return num;
                else if (root.key < num) {
                    result = node.key;
                    node = node.right;
                } else node = node.left;
            }
            return result;
        }

        //  inserts a new node with the given number in the
        //  correct place in the tree
        void insert(int key) {

            // 1) If the tree is empty, create the root
            if (this.root == null) {
                this.root = new Node(key);
                return;
            }

            // 2) Otherwise, create a node with the key
            //    and traverse down the tree to find where to
            //    to insert the new node
            Node currentNode = this.root;
            Node newNode = new Node(key);

            while (currentNode != null) {
                if (key < currentNode.key) {
                    if (currentNode.left == null) {
                        currentNode.left = newNode;
                        newNode.parent = currentNode;
                        break;
                    } else {
                        currentNode = currentNode.left;
                    }
                } else {
                    if (currentNode.right == null) {
                        currentNode.right = newNode;
                        newNode.parent = currentNode;
                        break;
                    } else {
                        currentNode = currentNode.right;
                    }
                }
            }
        }
    }

    /*********************************************
     * Driver program to test above function     *
     *********************************************/

    public static void main(String[] args) {

        // Create a Binary Search Tree
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(20);
        bst.insert(9);
        bst.insert(25);
        bst.insert(5);
        bst.insert(12);
        bst.insert(11);
        bst.insert(14);

        int result = bst.findLargestSmallerKey(17);
        System.out.println("Largest smaller number is " + result);
    }
}
