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
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.SqlResultSetMapping;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.ColumnResult;

// NamedNativeQuery & SqlResultSetMapping to get top 3 read books mapped onto a DTO
@NamedNativeQuery(name = "getTop3BookQuery", query = """
        WITH loan_count_by_country AS (
            SELECT ppl.country_id, b.id, b.name, COUNT(b.id) AS \"number_of_loans_in_country\"
            FROM books b
            JOIN book_rents br ON br.book_id = b.id
            JOIN people ppl ON ppl.id = br.person_id
            GROUP BY ppl.country_id, b.id
            ORDER BY ppl.country_id, number_of_loans_in_country DESC
        ), top_3_books_by_country AS (
            SELECT *
            FROM (
                SELECT
                    *,
                    ROW_NUMBER() OVER (
                        PARTITION BY loan_count_by_country.country_id
                        ORDER BY loan_count_by_country.number_of_loans_in_country DESC
                    ) AS row_id FROM loan_count_by_country
            ) AS A
            WHERE row_id < 4
        ), loan_count_by_book_and_country AS (
            SELECT
                b.id AS \"book_id\", b.name,
                ppl.country_id,
                ppl.id AS \"ppl_id\",
                ppl.name AS \"ppl_name\",
                COUNT(ppl.id) AS \"number_of_loans_by_person_in_country\"
            FROM books b
            JOIN book_rents br ON br.book_id = b.id
            JOIN people ppl ON ppl.id = br.person_id
            GROUP BY b.id, ppl.country_id, ppl.id
            ORDER BY b.id, ppl.country_id, ppl.id
        ), top_3_ppl_by_book_and_country AS (
            SELECT *
            FROM (
                SELECT
                    *,
                    ROW_NUMBER() OVER (
                        PARTITION BY loan_count_by_book_and_country.book_id, loan_count_by_book_and_country.country_id
                        ORDER BY loan_count_by_book_and_country.number_of_loans_by_person_in_country DESC
                    ) AS row_id FROM loan_count_by_book_and_country
            ) AS A
            WHERE row_id < 4
        ), finalised_results AS (
            SELECT
                bbc.country_id,
                pbbc.book_id,
                bbc.name AS \"book_name\",
                bbc.number_of_loans_in_country,
                pbbc.ppl_name AS \"top_3_borrower_in_country\",
                pbbc.number_of_loans_by_person_in_country
            FROM top_3_books_by_country bbc
            JOIN top_3_ppl_by_book_and_country pbbc
            ON bbc.country_id = pbbc.country_id AND bbc.id = pbbc.book_id
        ), final_results_with_author AS (
            SELECT
                country_id,
                fr.book_id,
                book_name AS \"book\",
                authors.name AS \"author\",
                top_3_borrower_in_country AS \"borrower\",
                number_of_loans_by_person_in_country
            FROM finalised_results fr
            JOIN author_books ab ON ab.book_id = fr.book_id
            JOIN authors ON authors.\"id\" = ab.author_id
        )
        SELECT *
        FROM final_results_with_author fr
        WHERE :countryId IS NULL OR fr.country_id = :countryId
        """, resultSetMapping = "stock_akhir_dto")
@SqlResultSetMapping(name = "stock_akhir_dto", classes = @ConstructorResult(targetClass = Top3ReadBooksQueryResult.class, columns = {
        @ColumnResult(name = "country_id", type = Long.class), @ColumnResult(name = "book_id", type = Integer.class),
        @ColumnResult(name = "book", type = String.class), @ColumnResult(name = "author", type = String.class),
        @ColumnResult(name = "borrower", type = String.class),
        @ColumnResult(name = "number_of_loans_by_person_in_country", type = Integer.class) }))
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
