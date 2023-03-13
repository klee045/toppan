package com.example.libraryapi.models;

import java.util.ArrayList;
import java.util.List;

public class Top3ReadBooksResponse {

    private String author;
    private String name;
    private List<String> borrower = new ArrayList<>();

    public Top3ReadBooksResponse(String author, String name, List<String> borrowers) {
        this.author = author;
        this.name = name;
        this.borrower = borrowers;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getBorrower() {
        return this.borrower;
    }

    public void setBorrower(List<String> borrowers) {
        this.borrower = new ArrayList<>(borrowers);
    }

    public boolean listOfStringEquals(List<String> l1, List<String> l2) {
        if (l1.size() != l2.size())
            return false;

        for (int i = 0; i < l1.size(); i++) {
            if (!(l1.get(i).equals(l2.get(i)))) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Top3ReadBooksResponse)) {
            System.out.println("not an instanceof");
            return false;
        }

        Top3ReadBooksResponse response = (Top3ReadBooksResponse) o;

        return response.author.equals(author) && response.name.equals(name)
                && listOfStringEquals(response.borrower, borrower);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (name == null ? 0 : name.hashCode());
        hash = 31 * hash + (author == null ? 0 : author.hashCode());
        return hash;
    }

    @Override
    public String toString() {
        return "Book: '" + this.name + "', Author: '" + this.author + "', Borrower: '" + this.borrower + "'";
    }
}
