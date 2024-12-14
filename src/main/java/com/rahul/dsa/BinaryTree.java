package com.rahul.dsa;

public class BinaryTree {
    BinaryTreeNode root;

    public BinaryTree(BinaryTreeNode root) {
        this.root = root;
    }

    // Return the maximum depth of the binary tree
    static int findHeight(BinaryTreeNode root) {
        // Your code goes here.
        if(root == null) return 0;
        if(root.left == null && root.right == null) {
            return 1;
        }
        int height = Integer.max(findHeight(root.left), findHeight(root.right));
        return height + 1;
    }
}
