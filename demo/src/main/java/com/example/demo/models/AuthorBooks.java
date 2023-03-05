package com.example.demo.models;

import java.time.OffsetDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "author_books")
public class AuthorBooks {

    @EmbeddedId
    private AuthorBooksKey id;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("authorId")
    @JoinColumn(name = "author_id", foreignKey = @ForeignKey(name = "author_books_author_id_fkey", foreignKeyDefinition = "FOREIGN KEY (author_id) REFERENCES public.authors (id) MATCH SIMPLE ON UPDATE CASCADE ON DELETE CASCADE"))
    private Author author;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("bookId")
    @JoinColumn(name = "book_id", foreignKey = @ForeignKey(name = "author_books_book_id_fkey", foreignKeyDefinition = "FOREIGN KEY (book_id) REFERENCES public.books (id) MATCH SIMPLE ON UPDATE CASCADE ON DELETE CASCADE"))
    private Book book;

    @Column(name = "createdAt", columnDefinition = "timestamp with time zone not null")
    private OffsetDateTime createdAt;

    @Column(name = "updatedAt", columnDefinition = "timestamp with time zone not null")
    private OffsetDateTime updatedAt;
}
