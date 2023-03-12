package com.example.libraryapi.models;

public class Top3ReadBookQueryType {
    private Long country_id;
    private int book_id;
    private String book;
    private String author;
    private String borrower;
    private int number_of_loans_by_person_in_country;

    public Top3ReadBookQueryType(Long countryId, int bookId, String author, String name, String borrower,
            int numLoans) {
        this.country_id = countryId;
        this.book_id = bookId;
        this.author = author;
        this.book = name;
        this.borrower = borrower;
        this.number_of_loans_by_person_in_country = numLoans;
    }

    public int getBook_id() {
        return this.book_id;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBook() {
        return this.book;
    }

    public void setBook(String name) {
        this.book = name;
    }

    public String getBorrower() {
        return this.borrower;
    }

    public void setBorrowers(String borrower) {
        this.borrower = borrower;
    }
}
