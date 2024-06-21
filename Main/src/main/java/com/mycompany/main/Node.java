
package com.mycompany.main;


public class Node {
    Book book;
    Node left;
    Node right;

    public Node(Book book) {
        this.book = book;
        this.left = null;
        this.right = null;
    }
}
