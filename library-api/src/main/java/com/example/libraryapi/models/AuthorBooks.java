package com.example.libraryapi.models;

import java.time.OffsetDateTime;

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

    @ManyToOne
    @MapsId("authorId")
    @JoinColumn(name = "author_id", foreignKey = @ForeignKey(name = "author_books_author_id_fkey", foreignKeyDefinition = "FOREIGN KEY (author_id) REFERENCES public.authors (id) MATCH SIMPLE ON UPDATE CASCADE ON DELETE CASCADE"))
    private Author author;

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "book_id", foreignKey = @ForeignKey(name = "author_books_book_id_fkey", foreignKeyDefinition = "FOREIGN KEY (book_id) REFERENCES public.books (id) MATCH SIMPLE ON UPDATE CASCADE ON DELETE CASCADE"))
    private Book book;

    @Column(name = "createdAt", columnDefinition = "timestamp with time zone not null")
    private OffsetDateTime createdAt;

    @Column(name = "updatedAt", columnDefinition = "timestamp with time zone not null")
    private OffsetDateTime updatedAt;

    public AuthorBooks() {
    }

    public AuthorBooks(AuthorBooksKey id, Author author, Book book, OffsetDateTime createdAt,
            OffsetDateTime updatedAt) {
        this.id = id;
        this.author = author;
        this.book = book;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public AuthorBooksKey getId() {
        return this.id;
    }

    public void setId(AuthorBooksKey id) {
        this.id = id;
    }

    public Author getAuthor() {
        return this.author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Book getBook() {
        return this.book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public OffsetDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
