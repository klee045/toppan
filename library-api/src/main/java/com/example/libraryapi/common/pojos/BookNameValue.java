package com.example.libraryapi.common.pojos;

public class BookNameValue implements DynamicTypeValue {
    private String bookName;

    public BookNameValue(String bookName) {
        this.bookName = bookName;
    }

    public String getBookName() {
        return this.bookName;
    }
}
