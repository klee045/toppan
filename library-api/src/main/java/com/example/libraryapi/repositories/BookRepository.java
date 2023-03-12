package com.example.libraryapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.libraryapi.models.Book;
import com.example.libraryapi.models.Top3ReadBookQueryType;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    /*
     * Assumptions regarding the query:
     * 1. it will return an array
     * 2. array consists of books, with the top 3 people who rented that book
     * 3. if country code not passed in, return top 3 books in each country, with the top 3 borrowers of each of those books within each country
     * 4, if country code is passed in, return top 3 books in THAT country, with the top 3 borrowers of each of those books within that specific country
     */
    @Query(name = "getTop3BookQuery", nativeQuery = true)
    List<Top3ReadBookQueryType> getTop3BorrowedBooksInCountryAndTop3BorrowersWithinCountry(
            @Param("countryId") Long countryId);
}
