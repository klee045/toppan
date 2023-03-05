package com.example.demo.models;

import java.time.OffsetDateTime;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "people")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(columnDefinition = "character varying(255) COLLATE pg_catalog.\"default\" NOT NULL")
    private String name;

    @OneToMany
    @JoinTable(name = "book_rents", joinColumns = @JoinColumn(name = "person_id"), inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<Book> rentedBooks;

    @Column(columnDefinition = "timestamp with time zone NOT NULL")
    private OffsetDateTime createdAt;

    @Column(columnDefinition = "timestamp with time zone NOT NULL")
    private OffsetDateTime updatedAt;

    @Column(name = "country_id")
    private Long countryId;

    public Person() {
    }

    public Person(int id, String name, Set<Book> rentedBooks,
            OffsetDateTime createdAt, OffsetDateTime updatedAt,
            Long countryId) {
        this.id = id;
        this.name = name;
        this.rentedBooks = rentedBooks;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.countryId = countryId;
    }
}
