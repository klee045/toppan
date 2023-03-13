package com.example.libraryapi.common.pojos;

public class AuthorNameValue implements DynamicTypeValue {
    private String author;

    public AuthorNameValue(String author) {
        this.author = author;
    }

    public String getAuthorName() {
        return this.author;
    }
}
