package com.example.libraryapi.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.example.libraryapi.models.Country;
import com.example.libraryapi.repositories.CountryRepository;

@Service
public class CountryService {
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public Map<String, Map<String, String>> getRandomCountry() {
        // Get all countries in db
        List<Country> countries = countryRepository.findAllCountries();

        // Get a random number between 0 and length of countries array
        Random rand = new Random();
        int n = rand.nextInt(countries.size());

        // Format into response requirement
        Map<String, Map<String, String>> result = new HashMap<>();
        result.put("country", new HashMap<String, String>());
        result.get("country").put("full_name", countries.get(n).getCountryFullName());
        result.get("country").put("country_code", countries.get(n).getCountryCode());

        return result;
    }
}
