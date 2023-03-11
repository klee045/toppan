package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Country;
import com.example.demo.repository.CountryRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/country")
public class CountryController {

    @Autowired
    CountryRepository countryRepository;

    @GetMapping("/getRandomCountry")
    public ResponseEntity<Map<String, Object>> getRandomCountry() {
        // Get all countries in db
        List<Country> countries = countryRepository.findAllCountries();

        // Get a random number between 0 and length of countries array
        Random rand = new Random();
        int n = rand.nextInt(countries.size());

        // Format into response requirement
        Map<String, Object> result = new HashMap<>();
        result.put("country", new HashMap<String, String>());
        ((Map<String, Object>) result.get("country")).put("full_name", countries.get(n).getCountryFullName());
        ((Map<String, Object>) result.get("country")).put("country_code", countries.get(n).getCountryCode());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
