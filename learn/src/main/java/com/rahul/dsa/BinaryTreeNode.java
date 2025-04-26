package com.rahul.dsa;

public class BinaryTreeNode {
    int val;
    BinaryTreeNode left;
    BinaryTreeNode right;

    public BinaryTreeNode(int val, BinaryTreeNode left, BinaryTreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public BinaryTreeNode(int val) {
        new BinaryTreeNode(val, null, null);
    }

    public BinaryTreeNode() {
        new BinaryTreeNode(0, null, null);
    }
}
