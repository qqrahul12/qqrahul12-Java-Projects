package com.rahul.dsa;

public class BinarySearchTree {
    BinaryTreeNode root;

    public BinarySearchTree(BinaryTreeNode root) {
        this.root = root;
    }

    public BinaryTreeNode insert(int val, BinaryTreeNode root) {
        if(root == null) {
            root = new BinaryTreeNode(val);
        }
        return root;
    }
}
