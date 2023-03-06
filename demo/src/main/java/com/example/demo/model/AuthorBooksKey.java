package com.example.demo.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class AuthorBooksKey implements Serializable {

    @Column(name = "author_id")
    private int authorId;

    @Column(name = "book_id")
    private int bookId;

    public AuthorBooksKey() {
    }

    public AuthorBooksKey(int authorId, int bookId) {
        this.authorId = authorId;
        this.bookId = bookId;
    }

    public int getAuthorId() {
        return this.authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getBookId() {
        return this.bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        AuthorBooksKey authorBooksId = (AuthorBooksKey) o;

        return authorId == authorBooksId.authorId
                && bookId == authorBooksId.bookId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId, bookId);
    }
}
