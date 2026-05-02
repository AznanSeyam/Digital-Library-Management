package services;

import database.Database;

import java.sql.Connection
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IssueService {
    public static void issueBook(int bookId, String issuedTo) {
        String checkAvailability = "SELECT available FROM books WHERE id = ?";
        String issueSql = "INSERT INTO issued_books (book_id, issued_to) VALUES (?, ?)";
        String updateBook = "UPDATE books SET available = 0 WHERE id = ?";

        try (Connection conn = Database.connect();
             PreparedStatement checkStmt = conn.prepareStatement(checkAvailability)) {
            checkStmt.setInt(1, bookId);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getBoolean("available")) {
                try (PreparedStatement issueStmt = conn.prepareStatement(issueSql);
                     PreparedStatement updateStmt = conn.prepareStatement(updateBook)) {
                    issueStmt.setInt(1, bookId);
                    issueStmt.setString(2, issuedTo);
                    issueStmt.executeUpdate();

                    updateStmt.setInt(1, bookId);
                    updateStmt.executeUpdate();
                    System.out.println("Book issued successfully.");
                }
            } else {
                System.out.println("Book is not available.");
            }
        } catch (SQLException e) {
            System.out.println("Error issuing book: " + e.getMessage());
        }
    }

    public static void returnBook(int bookId) {
        String deleteIssued = "DELETE FROM issued_books WHERE book_id = ?";
        String updateBook = "UPDATE books SET available = 1 WHERE id = ?";

        try (Connection conn = Database.connect();
             PreparedStatement deleteStmt = conn.prepareStatement(deleteIssued);
             PreparedStatement updateStmt = conn.prepareStatement(updateBook)) {
            deleteStmt.setInt(1, bookId);
            int rowsAffected = deleteStmt.executeUpdate();
            if (rowsAffected > 0) {
                updateStmt.setInt(1, bookId);
                updateStmt.executeUpdate();
                System.out.println("Book returned successfully.");
            } else {
                System.out.println("Book ID not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error returning book: " + e.getMessage());
        }
    }
}
