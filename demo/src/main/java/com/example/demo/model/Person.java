package com.example.demo.model;

import java.time.OffsetDateTime;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.ConstraintMode;

@Entity
@Table(name = "people")
public class Person {

    @Id
    @SequenceGenerator(name = "people_id_seq", sequenceName = "people_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "people_id_seq")
    @Column(name = "id", columnDefinition = "integer NOT NULL DEFAULT nextval('people_id_seq'::regclass)")
    private int id;

    @Column(columnDefinition = "character varying(255) COLLATE pg_catalog.\"default\" NOT NULL")
    private String name;

    @ElementCollection
    @CollectionTable(name = "book_rents", joinColumns = @JoinColumn(name = "person_id", columnDefinition = "bigint"), foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private List<BookLoanHistory> personalLoanHistory;

    @Column(columnDefinition = "timestamp with time zone NOT NULL")
    private OffsetDateTime createdAt;

    @Column(columnDefinition = "timestamp with time zone NOT NULL")
    private OffsetDateTime updatedAt;

    @Column(name = "country_id")
    private Long countryId;

    public Person() {
    }

    public Person(int id, String name,
            List<BookLoanHistory> personalLoanHistory,
            OffsetDateTime createdAt, OffsetDateTime updatedAt,
            Long countryId) {
        this.id = id;
        this.name = name;
        this.personalLoanHistory = personalLoanHistory;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.countryId = countryId;
    }
}