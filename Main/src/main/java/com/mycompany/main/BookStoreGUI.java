
package com.mycompany.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookStoreGUI {
    private JFrame frame;
    private JTextField titleField;
    private JTextField authorField;
    private JTextField yearField;
    private JTextField priceField;
    private JTextArea displayArea;
    private BookBST bookStore;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                BookStoreGUI window = new BookStoreGUI();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public BookStoreGUI() {
        bookStore = new BookBST();
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Book Store");
        frame.setBounds(100, 100, 600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel inputPanel = new JPanel();
        frame.getContentPane().add(inputPanel, BorderLayout.NORTH);
        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);  // Add padding

        JLabel titleLabel = new JLabel("Title:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(titleLabel, gbc);

        titleField = new JTextField();
        titleField.setPreferredSize(new Dimension(200, 25));
        gbc.gridx = 1;
        gbc.gridy = 0;
        inputPanel.add(titleField, gbc);

        JLabel authorLabel = new JLabel("Author:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(authorLabel, gbc);

        authorField = new JTextField();
        authorField.setPreferredSize(new Dimension(200, 25));
        gbc.gridx = 1;
        gbc.gridy = 1;
        inputPanel.add(authorField, gbc);

        JLabel yearLabel = new JLabel("Year:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(yearLabel, gbc);

        yearField = new JTextField();
        yearField.setPreferredSize(new Dimension(200, 25));
        gbc.gridx = 1;
        gbc.gridy = 2;
        inputPanel.add(yearField, gbc);

        JLabel priceLabel = new JLabel("Price:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(priceLabel, gbc);

        priceField = new JTextField();
        priceField.setPreferredSize(new Dimension(200, 25));
        gbc.gridx = 1;
        gbc.gridy = 3;
        inputPanel.add(priceField, gbc);

        JPanel buttonPanel = new JPanel();
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        JButton addButton = new JButton("Add Book");
        addButton.setPreferredSize(new Dimension(120, 25));
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });
        buttonPanel.add(addButton);

        JButton searchButton = new JButton("Search Book");
        searchButton.setPreferredSize(new Dimension(120, 25));
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchBook();
            }
        });
        buttonPanel.add(searchButton);

        JButton deleteButton = new JButton("Delete Book");
        deleteButton.setPreferredSize(new Dimension(120, 25));
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteBook();
            }
        });
        buttonPanel.add(deleteButton);

        displayArea = new JTextArea();
        displayArea.setPreferredSize(new Dimension(550, 300));
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    private void addBook() {
        String title = titleField.getText();
        String author = authorField.getText();
        int year = Integer.parseInt(yearField.getText());
        double price = Double.parseDouble(priceField.getText());

        bookStore.insert(new Book(title, author, year, price));
        displayArea.setText("Book added: " + title);
        clearFields();
    }

    private void searchBook() {
        String title = titleField.getText();
        Node result = bookStore.searchRecursive(bookStore.root, title);
        if (result == null) {
            displayArea.setText("Book not found.");
        } else {
            displayArea.setText("Book found: " + result.book.title + " by " + result.book.author);
        }
        clearFields();
    }

    private void deleteBook() {
        String title = titleField.getText();
        bookStore.delete(title);
        displayArea.setText("Book deleted: " + title);
        clearFields();
    }

    private void clearFields() {
        titleField.setText("");
        authorField.setText("");
        yearField.setText("");
        priceField.setText("");
    }
}
