
package com.mycompany.main;


public class BookBST {
    Node root;

    public BookBST() {
        this.root = null;
    }

    public void insert(Book book) {
        root = insertRecursive(root, book);
    }

    private Node insertRecursive(Node root, Book book) {
        if (root == null) {
            root = new Node(book);
            return root;
        }

        if (book.title.compareTo(root.book.title) < 0) {
            root.left = insertRecursive(root.left, book);
        } else if (book.title.compareTo(root.book.title) > 0) {
            root.right = insertRecursive(root.right, book);
        }

        return root;
    }

    public void delete(String title) {
        root = deleteRecursive(root, title);
    }

    private Node deleteRecursive(Node root, String title) {
        if (root == null) {
            return null;
        }

        if (title.compareTo(root.book.title) < 0) {
            root.left = deleteRecursive(root.left, title);
        } else if (title.compareTo(root.book.title) > 0) {
            root.right = deleteRecursive(root.right, title);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.book = minValue(root.right);
            root.right = deleteRecursive(root.right, root.book.title);
        }

        return root;
    }

    private Book minValue(Node root) {
        Book minBook = root.book;
        while (root.left != null) {
            minBook = root.left.book;
            root = root.left;
        }
        return minBook;
    }

    public Node searchRecursive(Node root, String title) {
        if (root == null || root.book.title.equals(title)) {
            return root;
        }

        if (title.compareTo(root.book.title) < 0) {
            return searchRecursive(root.left, title);
        }

        return searchRecursive(root.right, title);
    }
}
