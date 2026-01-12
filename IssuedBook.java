package models;

import java.sql.Timestamp;

public class IssuedBook {
    private int id;
    private int bookId;
    private String issuedTo;
    private Timestamp issueDate;

    public IssuedBook(int id, int bookId, String issuedTo, Timestamp issueDate) {
        this.id = id;
        this.bookId = bookId;
        this.issuedTo = issuedTo;
        this.issueDate = issueDate;
    }

    public int getId() {
        return id;
    }

    public int getBookId() {
        return bookId;
    }

    public String getIssuedTo() {
        return issuedTo;
    }

    public Timestamp getIssueDate() {
        return issueDate;
    }
}
