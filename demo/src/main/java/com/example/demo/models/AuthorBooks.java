package com.example.demo.models;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class AuthorBooks {

    @EmbeddedId
    private AuthorBooksKey id;

    @ManyToOne
    @MapsId("authorId")
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "book_id")
    private Book book;
}
