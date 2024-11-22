package com.example.chapter6;

public class Tree {
    
    private Node root;

    public Tree() {

    }

    public Tree(int number) {
        root = new Node(number);
    }

    public void insert(int number) {

        if (root == null) {
            root = new Node(number);            
        } else {
            root.insert(number);
        }
    }

    public void traverseFromRoot() {
        traverse(root);
    }

    private void traverse(Node node) {

        if (node == null) {
            return;
        }

        traverse(node.getLeftNode());
        System.out.println(node.getValue());
        traverse(node.getRightNode());
    }

    public void reverseFromRoot() {
        reverse(root);
    }

    private void reverse(Node node) {
        if (node == null) {
            return;
        }

        reverse(node.getRightNode());
        System.out.println(node.getValue());
        reverse(node.getLeftNode());
    }
}
