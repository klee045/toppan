package com.example.libraryapi.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.libraryapi.services.CountryService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CountryController {

    @Autowired
    CountryService countryService;

    @GetMapping("/getRandomCountry")
    public ResponseEntity<Map<String, Map<String, String>>> getRandomCountry() {
        Map<String, Map<String, String>> result = countryService.getRandomCountry();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
