package java.java_solutions;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
public class Solution00235 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Base case: if root is null or matches either p or q return it
        if (root == null || root == p || root == q) return root;
        // Search in left and right subtrees
        var left = lowestCommonAncestor(root.left, p, q);
        var right = lowestCommonAncestor(root.right, p, q);

        // If both left and right are non-null, we found p and q in different subtrees
        if (left != null && right != null) return root;

        // If one subtree returned null, return the non-null result
        return left != null ? left : right;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic binary tree
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(5);
        root1.right = new TreeNode(1);
        root1.left.left = new TreeNode(6);
        root1.left.right = new TreeNode(2);
        root1.right.left = new TreeNode(0);
        root1.right.right = new TreeNode(8);
        root1.left.right.left = new TreeNode(7);
        root1.left.right.right = new TreeNode(4);

        var s = new Solution00235();

        // LCA of 5 and 1 should be 3
        TreeNode result1 = s.lowestCommonAncestor(root1, root1.left, root1.right);
        System.out.println("Test Case 1: LCA of 5 and 1 = " + result1.val + " (Expected: 3)");

        // LCA of 5 and 4 should be 5
        TreeNode result2 = s.lowestCommonAncestor(root1, root1.left, root1.left.right.right);
        System.out.println("Test Case 2: LCA of 5 and 4 = " + result2.val + " (Expected: 5)");

        // Test Case 2: Simple tree with one path
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.left.left = new TreeNode(3);
        root2.left.left.left = new TreeNode(4);

        // LCA of 2 and 4 should be 2
        TreeNode result3 = s.lowestCommonAncestor(root2, root2.left, root2.left.left.left);
        System.out.println("Test Case 3: LCA of 2 and 4 = " + result3.val + " (Expected: 2)");

        // Test Case 3: Binary Search Tree
        TreeNode root3 = new TreeNode(6);
        root3.left = new TreeNode(2);
        root3.right = new TreeNode(8);
        root3.left.left = new TreeNode(0);
        root3.left.right = new TreeNode(4);
        root3.right.left = new TreeNode(7);
        root3.right.right = new TreeNode(9);
        root3.left.right.left = new TreeNode(3);
        root3.left.right.right = new TreeNode(5);

        // LCA of 2 and 8 should be 6
        TreeNode result4 = s.lowestCommonAncestor(root3, root3.left, root3.right);
        System.out.println("Test Case 4: LCA of 2 and 8 = " + result4.val + " (Expected: 6)");

        // LCA of 2 and 4 should be 2
        TreeNode result5 = s.lowestCommonAncestor(root3, root3.left, root3.left.right);
        System.out.println("Test Case 5: LCA of 2 and 4 = " + result5.val + " (Expected: 2)");

        // Test Case 6: Tree from array [5,3,6,2,4,null,null,1]
        TreeNode root4 = new TreeNode(5);
        root4.left = new TreeNode(3);
        root4.right = new TreeNode(6);
        root4.left.left = new TreeNode(2);
        root4.left.right = new TreeNode(4);
        root4.left.left.left = new TreeNode(1);

        // LCA of 1 and 4 should be 3
        TreeNode p = root4.left.left.left; // Node with value 1
        TreeNode q = root4.left.right;     // Node with value 4
        TreeNode result6 = s.lowestCommonAncestor(root4, p, q);
        System.out.println("Test Case 6: LCA of 1 and 4 = " + result6.val + " (Expected: 3)");
    }
}
