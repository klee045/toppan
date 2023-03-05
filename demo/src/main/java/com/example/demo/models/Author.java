package com.example.demo.models;

import java.time.OffsetDateTime;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(columnDefinition = "character varying(255) COLLATE pg_catalog.\"default\" NOT NULL")
    private String name;

    @ManyToMany
    private Set<Book> books;

    @Column(name = "createdAt", columnDefinition = "timestamp with time zone not null")
    private OffsetDateTime createdAt;

    @Column(name = "updatedAt", columnDefinition = "timestamp with time zone not null")
    private OffsetDateTime updatedAt;

    public Author() {
    }

    public Author(int id, String name, Set<Book> books,
            OffsetDateTime createdAt, OffsetDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.books = books;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
