package com.example.libraryapi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.libraryapi.models.Country;
import com.example.libraryapi.repositories.CountryRepository;
import com.example.libraryapi.services.CountryService;

@ExtendWith(MockitoExtension.class)
public class CountryServiceTest {
    CountryService countryService;

    @Mock
    CountryRepository countryRepository;

    // Initialize data for testing
    Country COUNTRY_1 = new Country(1, "Singapore", "SG");
    Country COUNTRY_2 = new Country(2, "United States of America", "US");
    Country COUNTRY_3 = new Country(3, "Malaysia", "MY");
    Country COUNTRY_4 = new Country(4, "Japan", "JPN");

    @BeforeEach
    void init() {
        countryService = new CountryService(this.countryRepository);
    }

    @Test
    void shouldGetRandomCountryCode() {
        List<Country> countries = new ArrayList<>() {
            {
                add(COUNTRY_1);
                add(COUNTRY_2);
                add(COUNTRY_3);
                add(COUNTRY_4);
            }
        };
        // Uses mocked repo with the data intialized as seen above
        Mockito.when(countryRepository.findAllCountries()).thenReturn(countries);

        // Create an actual and expected version of response to test and compare
        Map<String, Object> actual = countryService.getRandomCountry();
        Map<String, Object> expected = new HashMap<>() {
            {
                put("country", new HashMap<>());
            }
        };
        ((Map<String, Object>) expected.get("country")).put("country_code", "SG");
        ((Map<String, Object>) expected.get("country")).put("full_name", "Singapore");

        // Compare the keys of the json instead, since values might differ due to random selection
        assertEquals(actual.keySet(), expected.keySet());
    }
}
