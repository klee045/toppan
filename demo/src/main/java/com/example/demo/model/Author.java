package com.example.demo.model;

import java.time.OffsetDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @SequenceGenerator(name = "authors_id_seq", sequenceName = "authors_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authors_id_seq")
    @Column(name = "id", columnDefinition = "integer NOT NULL DEFAULT nextval('authors_id_seq'::regclass)")
    private int id;

    @Column(columnDefinition = "character varying(255) COLLATE pg_catalog.\"default\" NOT NULL")
    private String name;

    @OneToMany(mappedBy = "author")
    private List<AuthorBooks> booksAuthored;

    @Column(name = "createdAt", columnDefinition = "timestamp with time zone not null")
    private OffsetDateTime createdAt;

    @Column(name = "updatedAt", columnDefinition = "timestamp with time zone not null")
    private OffsetDateTime updatedAt;

    public Author() {
    }

    public Author(int id, String name, List<AuthorBooks> booksAuthored, OffsetDateTime createdAt,
            OffsetDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.booksAuthored = booksAuthored;
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

    public List<AuthorBooks> getBookLoanHistory() {
        return this.booksAuthored;
    }

    public void setBooksAuthored(List<AuthorBooks> booksAuthored) {
        this.booksAuthored = booksAuthored;
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
