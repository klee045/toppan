package com.example.libraryapi.repositories;

import org.springframework.stereotype.Repository;

import com.example.libraryapi.models.Country;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
    @Query("SELECT c FROM Country c")
    List<Country> findAllCountries();
}
