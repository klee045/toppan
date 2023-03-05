package com.example.demo.models;

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

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public Book(int id, String name, List<BookLoanHistory> bookLoanHistory,
            OffsetDateTime createdAt, OffsetDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.bookLoanHistory = bookLoanHistory;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
