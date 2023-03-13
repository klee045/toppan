package com.example.libraryapi.models;

public class Top3ReadBooksQueryResult {
    private Long country_id;
    private int book_id;
    private String book;
    private String author;
    private String borrower;
    private int number_of_loans_by_person_in_country;

    public Top3ReadBooksQueryResult(Long countryId, int bookId, String author, String name, String borrower,
            int numLoans) {
        this.country_id = countryId;
        this.book_id = bookId;
        this.author = author;
        this.book = name;
        this.borrower = borrower;
        this.number_of_loans_by_person_in_country = numLoans;
    }

    public Long getCountry_id() {
        return this.country_id;
    }

    public void setCountry_id(Long country_id) {
        this.country_id = country_id;
    }

    public int getBook_id() {
        return this.book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
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

    public int getNumberOfLoans() {
        return this.number_of_loans_by_person_in_country;
    }

    public void setNumberOfLoans(int num) {
        this.number_of_loans_by_person_in_country = num;
    }
}
