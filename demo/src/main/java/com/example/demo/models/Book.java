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
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(columnDefinition = "character varying(255) COLLATE pg_catalog.\"default\" NOT NULL")
    private String name;

    @ManyToMany(mappedBy = "books")
    private Set<Author> authors;

    @Column(name = "createdAt", columnDefinition = "timestamp with time zone not null")
    private OffsetDateTime createdAt;

    @Column(name = "updatedAt", columnDefinition = "timestamp with time zone not null")
    private OffsetDateTime updatedAt;

    public Book() {
    }

    public Book(int id, String name, Set<Author> authors,
            OffsetDateTime createdAt, OffsetDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.authors = authors;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
