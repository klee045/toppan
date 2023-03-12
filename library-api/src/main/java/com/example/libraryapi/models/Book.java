package com.example.libraryapi.models;

import java.time.OffsetDateTime;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.SequenceGenerator;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @SequenceGenerator(name = "books_id_seq", sequenceName = "books_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "books_id_seq")
    @Column(name = "id", columnDefinition = "integer NOT NULL DEFAULT nextval('books_id_seq'::regclass)")
    private int id;

    @Column(columnDefinition = "character varying(255) COLLATE pg_catalog.\"default\" NOT NULL")
    private String name;

    @ElementCollection
    @CollectionTable(name = "book_rents", joinColumns = @JoinColumn(name = "book_id", columnDefinition = "bigint"), foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private List<BookLoanHistory> bookLoanHistory;

    @Column(name = "createdAt", columnDefinition = "timestamp with time zone not null")
    private OffsetDateTime createdAt;

    @Column(name = "updatedAt", columnDefinition = "timestamp with time zone not null")
    private OffsetDateTime updatedAt;

    public Book() {
    }

    public Book(int id, String name, List<BookLoanHistory> bookLoanHistory, OffsetDateTime createdAt,
            OffsetDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.bookLoanHistory = bookLoanHistory;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BookLoanHistory> getBookLoanHistory() {
        return this.bookLoanHistory;
    }

    public void setPersonalLoanHistory(List<BookLoanHistory> bookLoanHistory) {
        this.bookLoanHistory = bookLoanHistory;
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
