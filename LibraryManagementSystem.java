package main;

import services.BookService;
import services.IssueService;
import utils.InputHelper;
import models.Book;

import java.util.List;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Delete Book");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = InputHelper.getInt();
            InputHelper.getString();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter book title: ");
                    String title = InputHelper.getString();
                    System.out.print("Enter book author: ");
                    String author = InputHelper.getString();
                    BookService.addBook(title, author);
                }
                case 2 -> {
                    List<Book> books = BookService.getAllBooks();
                    books.forEach(book -> System.out.printf("ID: %d | Title: %s | Author: %s | Available: %s%n",
                            book.getId(), book.getTitle(), book.getAuthor(), book.isAvailable() ? "Yes" : "No"));
                }
                case 3 -> {
                    System.out.print("Enter book ID to issue: ");
                    int bookId = InputHelper.getInt();
                    InputHelper.getString();
                    System.out.print("Enter your name: ");
                    String issuedTo = InputHelper.getString();
                    IssueService.issueBook(bookId, issuedTo);
                }
                case 4 -> {
                    System.out.print("Enter book ID to return: ");
                    int bookId = InputHelper.getInt();
                    IssueService.returnBook(bookId);
                }
                case 5 -> {
                    System.out.print("Enter book ID to delete: ");
                    int bookId = InputHelper.getInt();
                    BookService.deleteBook(bookId);
                }
                case 6 -> System.exit(0);
            }
        }
    }
}
