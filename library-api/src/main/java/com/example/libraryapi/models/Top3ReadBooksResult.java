package com.example.libraryapi.models;

import java.util.ArrayList;
import java.util.List;

public class Top3ReadBooksResult {

    private String author;
    private String name;
    private List<String> borrowers = new ArrayList<>();

    public Top3ReadBooksResult(String author, String name, List<String> borrowers) {
        this.author = author;
        this.name = name;
        this.borrowers = borrowers;
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

    public List<String> getBorrowers() {
        return this.borrowers;
    }

    public void setBorrowers(List<String> borrowers) {
        this.borrowers = new ArrayList<>(borrowers);
    }
}
